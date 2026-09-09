package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Holder.Reference.class)
public interface HolderReferenceMixin {
	@Invoker("<init>")
	static <T> Holder.Reference<T> create(final Holder.Reference.Type type, final HolderOwner<T> owner, final @Nullable ResourceKey<T> key, final @Nullable T value) {
		throw new AssertionError("Not transformed!");
	}
}
