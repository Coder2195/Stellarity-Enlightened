package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public interface StellarityBlockSetTypes {
	BlockSetType AMETHYII = new BlockSetTypeBuilder().register(Stellarity.id("amethyii"));
	BlockSetType HALLOWED = new BlockSetTypeBuilder().register(Stellarity.id("hallowed"));
	BlockSetType SHRUBBED = new BlockSetTypeBuilder().register(Stellarity.id("shrubbed"));
	BlockSetType PRISMATIC = new BlockSetTypeBuilder().register(Stellarity.id("prismatic"));
	BlockSetType ASHEN = new BlockSetTypeBuilder().register(Stellarity.id("ashen"));
	BlockSetType INFERNO = new BlockSetTypeBuilder().register(Stellarity.id("inferno"));
}
