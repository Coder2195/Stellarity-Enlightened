package dev.coder2195.stellarity.util;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;


public interface CodecExtender {

	static <A> Codec<A> recursive(final String name, final Function<Codec<A>, Codec<A>> wrapped) {
		return new RecursiveCodec<>(name, wrapped);
	}

	static <A> Codec<A> lazyInitialized(final Supplier<Codec<A>> delegate) {
		return new RecursiveCodec<>(delegate.toString(), self -> delegate.get());
	}

	class RecursiveCodec<T> implements Codec<T> {
		private final String name;
		private final Supplier<Codec<T>> wrapped;

		private RecursiveCodec(final String name, final Function<Codec<T>, Codec<T>> wrapped) {
			this.name = name;
			this.wrapped = Suppliers.memoize(() -> wrapped.apply(this));
		}

		@Override
		public <S> DataResult<Pair<T, S>> decode(final DynamicOps<S> ops, final S input) {
			return wrapped.get().decode(ops, input);
		}

		@Override
		public <S> DataResult<S> encode(final T input, final DynamicOps<S> ops, final S prefix) {
			return wrapped.get().encode(input, ops, prefix);
		}

		@Override
		public String toString() {
			return "RecursiveCodec[" + name + ']';
		}
	}

	static <T> Codec<T> withAlternative(final Codec<T> primary, final Codec<? extends T> alternative) {
		return Codec.either(
			primary,
			alternative
		).xmap(
			CodecExtender::eitherUnwrap,
			Either::left
		);
	}

	static <U> U eitherUnwrap(final Either<? extends U, ? extends U> either) {
		return either.map(Function.identity(), Function.identity());
	}

	static <A> MapCodec<A> assumeMapUnsafe(final Codec<A> codec) {
		return new MapCodec<>() {
			private static final String COMPRESSED_VALUE_KEY = "value";

			@Override
			public <T> Stream<T> keys(final DynamicOps<T> ops) {
				return Stream.of(ops.createString(COMPRESSED_VALUE_KEY));
			}

			@Override
			public <T> DataResult<A> decode(final DynamicOps<T> ops, final MapLike<T> input) {
				if (ops.compressMaps()) {
					final T value = input.get(COMPRESSED_VALUE_KEY);
					if (value == null) {
						return DataResult.error(() -> "Missing value");
					}
					return codec.parse(ops, value);
				}
				return codec.parse(ops, ops.createMap(input.entries()));
			}

			@Override
			public <T> RecordBuilder<T> encode(final A input, final DynamicOps<T> ops, final RecordBuilder<T> prefix) {
				final DataResult<T> encoded = codec.encodeStart(ops, input);
				if (ops.compressMaps()) {
					return prefix.add(COMPRESSED_VALUE_KEY, encoded);
				}
				final DataResult<MapLike<T>> encodedMapResult = encoded.flatMap(ops::getMap);
				return encodedMapResult.map(encodedMap -> {
					encodedMap.entries().forEach(pair -> prefix.add(pair.getFirst(), pair.getSecond()));
					return prefix;
				}).result().orElseGet(() -> prefix.withErrorsFrom(encodedMapResult));
			}
		};
	}


	static <T> Codec<T> buildExtensionCodec(Codec<T> original, BiFunction<RecordCodecBuilder.Instance<T>, RecordCodecBuilder<T, T>, ? extends App<RecordCodecBuilder.Mu<T>, T>> builder, Function<T, T> defaultValueApplicator) {
		return deferUntilUsed(
			() -> withAlternative(
				RecordCodecBuilder.create(
					instance -> builder.apply(
						instance,
						assumeMapUnsafe(original)
							.forGetter(Function.identity()))
				),
				original.xmap(defaultValueApplicator, Function.identity())
			)
		);
	}


	static <T> Codec<T> buildExtensionCodec(Codec<T> original, BiFunction<RecordCodecBuilder.Instance<T>, RecordCodecBuilder<T, T>, ? extends App<RecordCodecBuilder.Mu<T>, T>> builder) {
		return buildExtensionCodec(original, builder, Function.identity());
	}


	static <T> Codec<T> deferUntilUsed(Supplier<Codec<T>> delegate) {
		return lazyInitialized(delegate);
	}
}