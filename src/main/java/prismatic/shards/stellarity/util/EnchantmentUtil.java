package prismatic.shards.stellarity.util;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.*;

import java.util.List;

import static net.minecraft.core.HolderSet.direct;

public interface EnchantmentUtil {
	static SpawnParticlesEffect.VelocitySource particleVelocity(FloatProvider base) {
		return new SpawnParticlesEffect.VelocitySource(0, base);
	}

	static SpawnParticlesEffect.VelocitySource particleVelocity() {
		return new SpawnParticlesEffect.VelocitySource(0, ConstantFloat.ZERO);
	}

	static SpawnParticlesEffect.PositionSource inBoundingBox() {
		return SpawnParticlesEffect.inBoundingBox();
	}

	static EnchantmentEntityEffect allEffects(EnchantmentEntityEffect... effects) {
		return AllOf.entityEffects(effects);
	}

	static EnchantmentEntityEffect playSound(List<Holder<SoundEvent>> soundEvents, FloatProvider volume, FloatProvider pitch) {
		return new PlaySoundEffect(soundEvents, volume, pitch);
	}

	static EnchantmentEntityEffect playSound(SoundEvent event, FloatProvider volume, FloatProvider pitch) {
		return new PlaySoundEffect(List.of(Holder.direct(event)), volume, pitch);
	}

	static Enchantment.Cost cost(int base, int perLevel) {
		return new Enchantment.Cost(base, perLevel);
	}

	static EnchantmentLocationBasedEffect attribute(Identifier id, Holder<Attribute> attribute, LevelBasedValue levelBasedValue, AttributeModifier.Operation operation) {
		return new EnchantmentAttributeEffect(id, attribute, levelBasedValue, operation);
	}

	static ChangeItemDamage changeItemDamage(LevelBasedValue amount) {
		return new ChangeItemDamage(amount);
	}

	static ApplyMobEffect applyEffect(HolderSet<MobEffect> toApply, LevelBasedValue minDuration, LevelBasedValue maxDuration, LevelBasedValue minAmp, LevelBasedValue maxAmp) {
		return new ApplyMobEffect(toApply, minDuration, maxDuration, minAmp, maxAmp);
	}

}
