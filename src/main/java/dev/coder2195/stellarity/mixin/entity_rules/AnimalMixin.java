package dev.coder2195.stellarity.mixin.entity_rules;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob {
	protected AnimalMixin(EntityType<? extends AgeableMob> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "isBrightEnoughToSpawn")
	private static boolean changeEndLightRequirements(BlockAndLightGetter level, BlockPos pos, Operation<Boolean> original) {
		if (original.call(level, pos)) return true;

		var actualLevel = level instanceof Level level1 ? level1 : level instanceof WorldGenLevel worldGenLevel ? worldGenLevel.getLevel() : null;
		if (actualLevel != null) return actualLevel.dimensionTypeRegistration().is(BuiltinDimensionTypes.END);

		return false;
	}

}
