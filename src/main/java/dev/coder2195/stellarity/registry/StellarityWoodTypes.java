package dev.coder2195.stellarity.registry;

import dev.coder2195.stellarity.Stellarity;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.world.level.block.state.properties.WoodType;

public interface StellarityWoodTypes {
	// todo: give actual sounds
	WoodType AMETHYII = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("amethyii"), StellarityBlockSetTypes.AMETHYII);
	WoodType HALLOWED = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("hallowed"), StellarityBlockSetTypes.HALLOWED);
	WoodType SHRUBBED = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("shrubbed"), StellarityBlockSetTypes.SHRUBBED);
	WoodType PRISMATIC = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("prismatic"), StellarityBlockSetTypes.PRISMATIC);
	WoodType ASHEN = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("ashen"), StellarityBlockSetTypes.ASHEN);
	WoodType INFERNO = WoodTypeBuilder.copyOf(WoodType.OAK).build(Stellarity.id("inferno"), StellarityBlockSetTypes.INFERNO);
}
