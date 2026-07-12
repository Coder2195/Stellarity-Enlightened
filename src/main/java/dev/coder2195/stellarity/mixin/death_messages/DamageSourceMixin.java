package dev.coder2195.stellarity.mixin.death_messages;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
	@Unique
	private final RandomSource random = RandomSource.create();

	@Shadow
	public abstract String getMsgId();

	@WrapOperation(method = "getLocalizedDeathMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"))
	private MutableComponent specialStellarityDeathMessages(String key, Object[] args, Operation<MutableComponent> original) {
		String id = getMsgId();
		int max = 0;
		if (id.equals("stellarity.frostburn") || id.equals("stellarity.prismatic_inferno") || id.equals("stellarity.natures_wrath"))
			max = 2;
		else if (id.equals("stellarity.dragonblade") || id.equals("stellarity.kaleidoscope") || id.equals("stellarity.spirit_dagger") || id.equals("stellarity.tamaris_execute"))
			max = 3;

		if (max != 0) key += "." + random.nextInt(1, max + 1);

		return original.call(key, args);
	}

}
