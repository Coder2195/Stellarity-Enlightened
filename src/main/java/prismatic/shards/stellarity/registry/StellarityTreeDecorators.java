package prismatic.shards.stellarity.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.tree_decorator.HangingColumnDecorator;

public interface StellarityTreeDecorators {
	static TreeDecoratorType<HangingColumnDecorator> HANGING_COLUMN = register("hanging_column", HangingColumnDecorator.CODEC);

	static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Stellarity.id(id), new TreeDecoratorType<>(codec));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Tree Decorators");
	}
}
