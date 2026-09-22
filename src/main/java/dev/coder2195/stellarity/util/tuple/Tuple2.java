package dev.coder2195.stellarity.util.tuple;

import net.minecraft.network.codec.StreamCodec;

public record Tuple2<A, B>(A _1, B _2) {
	public static <R, A, B> StreamCodec<R, Tuple2<A, B>> streamCodec(StreamCodec<? super R, A> aStreamCodec, StreamCodec<? super R, B> bStreamCodec) {
		return StreamCodec.composite(aStreamCodec, Tuple2::_1, bStreamCodec, Tuple2::_2, Tuple2::new);
	}
}
