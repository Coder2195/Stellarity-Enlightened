package dev.coder2195.stellarity.mixin.interface_injections;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import dev.coder2195.stellarity.interface_injection.ExtEnderDragonFight;
import dev.coder2195.stellarity.util.CodecExtender;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin implements ExtEnderDragonFight {
	@Unique
	private boolean portalChestGenerated;

	@Override
	public boolean stellarity$portalChestGenerated() {
		return portalChestGenerated;
	}

	@Override
	public void stellarity$setPortalChestGenerated(boolean generated) {
		this.portalChestGenerated = generated;
	}

	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"))
	private static Codec<EnderDragonFight> modifyCodec(Codec<EnderDragonFight> original) {
		return CodecExtender.buildExtensionCodec(original, (instance, wrapper) -> instance.group(wrapper,
				Codec.BOOL.fieldOf("stellarity:portal_chest_generated").forGetter(ExtEnderDragonFight::stellarity$portalChestGenerated)
			).apply(instance, ExtEnderDragonFight::apply),
			ExtEnderDragonFight::applyDefaults
		);
	}
}
