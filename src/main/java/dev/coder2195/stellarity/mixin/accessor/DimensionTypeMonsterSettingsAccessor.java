package dev.coder2195.stellarity.mixin.accessor;

import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DimensionType.MonsterSettings.class)
public interface DimensionTypeMonsterSettingsAccessor {
	@Mutable
	@Accessor("monsterSpawnBlockLightLimit")
	void stellarity$setMonsterSpawnBlockLightLimit(int i);
}
