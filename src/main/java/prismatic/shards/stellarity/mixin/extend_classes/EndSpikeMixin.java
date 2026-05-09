package prismatic.shards.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.interface_injection.ExtEndSpike;
import prismatic.shards.stellarity.util.CodecExtender;

@Mixin(EndSpikeFeature.EndSpike.class)
public class EndSpikeMixin implements ExtEndSpike {
	@Shadow
	@Final
	public static Codec<EndSpikeFeature.EndSpike> CODEC;
	@Unique
	private boolean altar;

	@Unique
	private boolean cryingObsidianTops;

	@Unique
	private boolean hasCrystal;

	@Override
	public boolean stellarity$hasAltar() {
		return altar;
	}

	@Override
	public void stellarity$setAltar(boolean altar) {
		this.altar = altar;
	}

	@Override
	public boolean stellarity$hasCryingObsidianTops() {
		return cryingObsidianTops;
	}

	@Override
	public boolean stellarity$hasCrystal() {
		return hasCrystal;
	}

	@Override
	public void stellarity$setHasCrystal(boolean hasCrystal) {
		this.hasCrystal = hasCrystal;
	}

	@Override
	public void stellarity$setCryingObsidianTops(boolean cryingObsidianTops) {
		this.cryingObsidianTops = cryingObsidianTops;
	}

	@ModifyExpressionValue(
		method = "<clinit>",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;create(Ljava/util/function/Function;)Lcom/mojang/serialization/Codec;"
		)
	)
	private static Codec<EndSpikeFeature.EndSpike> more(Codec<EndSpikeFeature.EndSpike> original) {
		return CodecExtender.buildExtensionCodec(original, (instance, wrapper) -> instance.group(wrapper,
				Codec.BOOL.optionalFieldOf("stellarity:altar", false).forGetter(ExtEndSpike::stellarity$hasAltar),
				Codec.BOOL.optionalFieldOf("stellarity:crying_obsidian_tops", false).forGetter(ExtEndSpike::stellarity$hasCryingObsidianTops),
				Codec.BOOL.optionalFieldOf("stellarity:has_crystal", true).forGetter(ExtEndSpike::stellarity$hasCrystal)
			).apply(instance, ExtEndSpike::apply),
			ExtEndSpike::applyDefaults
		);
	}
}
