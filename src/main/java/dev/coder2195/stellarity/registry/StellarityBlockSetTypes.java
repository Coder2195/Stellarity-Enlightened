package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public interface StellarityBlockSetTypes {
	BlockSetType AMETHYII = new BlockSetTypeBuilder().build();
	BlockSetType HALLOWED = new BlockSetTypeBuilder().build();
	BlockSetType SHRUBBED = new BlockSetTypeBuilder().build();
	BlockSetType PRISMATIC = new BlockSetTypeBuilder().build();
	BlockSetType ASHEN = new BlockSetTypeBuilder().build();
	BlockSetType INFERNO = new BlockSetTypeBuilder().build();

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Block Set Types");
	}
}
