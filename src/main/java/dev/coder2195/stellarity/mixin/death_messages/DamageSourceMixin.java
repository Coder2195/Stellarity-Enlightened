package dev.coder2195.stellarity.mixin.death_messages;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;
import java.util.Objects;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
	@Unique
	private final RandomSource random = RandomSource.create();

	@Unique
	private final Map<String, Integer> deathMessageLines = ImmutableMap.<String, Integer>builder()
		.put("stellarity.frostburn", 2)
		.put("stellarity.prismatic_inferno", 2)
		.put("stellarity.natures_wrath", 2)
		.put("stellarity.dragonblade_punch", 3)
		.put("stellarity.kaleidoscope", 3)
		.put("stellarity.spirit_dagger", 3)
		.put("stellarity.tamaris_execute", 3)
		.put("stellarity.spirit_slash", 3)
		.build();

	@Shadow
	public abstract String getMsgId();

	@WrapOperation(method = "getLocalizedDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"))
	private MutableComponent specialStellarityDeathMessages(String key, Object[] args, Operation<MutableComponent> original) {
		String id = getMsgId();
		// java complain about null stuff
		int max = Objects.requireNonNullElse(deathMessageLines.getOrDefault(id, 0), 0);
		if (max != 0) key += "." + random.nextInt(1, max + 1);
		return original.call(key, args);
	}

}
