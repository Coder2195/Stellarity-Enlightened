package dev.coder2195.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import dev.coder2195.stellarity.Stellarity;

public class JinxEffect extends MobEffect {
	public JinxEffect() {
		super(MobEffectCategory.HARMFUL, 0x3A0052);
		addAttributeModifier(Attributes.ARMOR, Stellarity.id("jinx_effect"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}


}
