package dev.coder2195.stellarity.mixin.accessor;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;

@Mixin(SinglePoolElement.class)
public interface SinglePoolElementAccessor {
	@Invoker("<init>")
	static SinglePoolElement create(final Either<Identifier, StructureTemplate> template, final Holder<StructureProcessorList> processors, final StructureTemplatePool.Projection projection, final Optional<LiquidSettings> overrideLiquidSettings) {
		throw new AssertionError("Not transformed!");
	}
}
