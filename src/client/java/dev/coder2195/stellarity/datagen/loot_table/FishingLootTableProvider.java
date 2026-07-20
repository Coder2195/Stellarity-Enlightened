package dev.coder2195.stellarity.datagen.loot_table;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.StellarityBiomes;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.minecraft.world.item.Items.*;
import static dev.coder2195.stellarity.registry.StellarityLootTables.*;
import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static dev.coder2195.stellarity.util.LootUtil.*;

public class FishingLootTableProvider extends SimpleFabricLootTableSubProvider {


	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public FishingLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup, LootContextParamSets.FISHING);
		this.registryLookup = registryLookup;
	}

	@Override
	public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		var lookup = registryLookup.join();
		consumer.accept(VOID_FISHING_FISH, lootTable().withPool(pool()
			.add(item(ENDER_KOI).setWeight(15).apply(count(2, 4)))
			.add(item(CRYSTAL_HEARTFISH).setWeight(4))
			.add(tableLoot(VOID_FISHING_LOCATION_THE_HALLOW).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.THE_HALLOW))))
			.add(tableLoot(VOID_FISHING_LOCATION_FLESH_TUNDRA).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.FLESH_TUNDRA))))
			.add(tableLoot(VOID_FISHING_LOCATION_WARPED_MARSH).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.WARPED_MARSH))))
			.add(tableLoot(VOID_FISHING_LOCATION_ENDLESS_DUNES).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.ENDLESS_DUNES))))
			.add(tableLoot(VOID_FISHING_LOCATION_FROZEN_SPIKES).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.FROZEN_SPIKES))))
			.add(tableLoot(VOID_FISHING_LOCATION_VANILLA_BIOMES).when(any(
				biome(lookup.getOrThrow(Biomes.END_BARRENS)),
				biome(lookup.getOrThrow(Biomes.END_HIGHLANDS)),
				biome(lookup.getOrThrow(Biomes.END_MIDLANDS)),
				biome(lookup.getOrThrow(Biomes.SMALL_END_ISLANDS))
			)))
			.add(tableLoot(VOID_FISHING_LOCATION_FIERY_HILLS).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.FIERY_HILLS))))
			.add(tableLoot(VOID_FISHING_LOCATION_AMETHYST_BIOMES).when(any(
				biome(lookup.getOrThrow(StellarityBiomes.AMETHYST_FOREST)),
				biome(lookup.getOrThrow(StellarityBiomes.CRYSTAL_CRAGS))
			)))
			.add(tableLoot(VOID_FISHING_LOCATION_PRISMARINE_FOREST).setWeight(30).when(biome(lookup.getOrThrow(StellarityBiomes.PRISMARINE_FOREST))))
		));

		consumer.accept(VOID_FISHING_JUNK, lootTable().withPool(pool()
			.add(item(CRYING_OBSIDIAN).apply(count(1, 3)))
			.add(item(OBSIDIAN).apply(count(1, 4)))
			.add(item(END_STONE).apply(count(3, 9)))
			.add(item(CHORUS_FRUIT).apply(count(1, 5)))
			.add(item(POPPED_CHORUS_FRUIT).setWeight(2).apply(count(2, 5)))
			.add(item(PURPUR_BLOCK).apply(count(6, 12)))
			.add(item(PHANTOM_MEMBRANE).setWeight(3).apply(count(1, 3)))
			.add(item(ENDER_PEARL).setWeight(3).apply(count(1, 2)))
			.add(item(SHULKER_SHELL).setWeight(5).apply(count(1, 2)))
			.add(item(FIREWORK_STAR).apply(
				component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
					FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, false
				))
			))
			.add(item(FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, true
			))))
			.add(item(FIREWORK_STAR).apply(component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
				FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{12801229}), new IntArrayList(new int[]{8073150}), true, false
			))))
			.add(item(PAPER).setWeight(3).apply(count(1, 5)))
			.add(item(END_ROD).setWeight(3).apply(count(1, 5)))
			.add(item(DYE.magenta()))
			.add(item(DYE.purple()))
			.add(item(DYE.black()))
		));

		consumer.accept(VOID_FISHING_TREASURE, lootTable().withPool(pool()
			.add(item(EYE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(1, 2)))
			.add(item(SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(1, 2)))
			.add(item(END_CRYSTAL).setWeight(5).apply(count(1, 2)))
			.add(item(END_CRYSTAL).setWeight(3).apply(count(2, 3)))
			.add(item(ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(11))
			.add(item(WINGED_KEY).setWeight(11))
			.add(item(BOOK).setWeight(2).apply(enchant(lookup, 30, 40).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
			.add(item(BOOK).setWeight(5).apply(enchant(lookup, 17, 29).withOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
			))
		));

		consumer.accept(VOID_FISHING_FISHER_OF_VOIDS, lootTable().withPool(pool().add(item(FISHER_OF_VOIDS)
			.apply(damage(0.15f, 0.75f))
		)));


		consumer.accept(VOID_FISHING_EVENT, lootTable().withPool(pool()
			.add(lootTable(VOID_FISHING_JUNK).setWeight(15).setQuality(-2))
			.add(lootTable(VOID_FISHING_TREASURE).setWeight(4).setQuality(2))
			.add(lootTable(VOID_FISHING_FISH).setWeight(70).setQuality(-1))
		));

		consumer.accept(VOID_FISHING_LOCATION_AMETHYST_BIOMES, lootTable().withPool(pool()
			.add(item(AMETHYST_BUDFISH).setWeight(16))
			.add(item(AMETHYST_SHARD).setWeight(5).apply(count(2, 4)))
			.add(item(QUARTZ).setWeight(5).apply(count(3, 6)))
			.add(item(AMETHYST_BLOCK).setWeight(3).apply(count(2, 3)))
			.add(item(BUDDING_AMETHYST).setWeight(1))
		));

		consumer.accept(VOID_FISHING_LOCATION_FIERY_HILLS, lootTable().withPool(pool()
			.add(item(FLAREFIN_KOI).setWeight(20))
			.add(item(BLAZE_ROD).setWeight(3).apply(count(1, 2)))
			.add(item(MAGMA_BLOCK).setWeight(6).apply(count(2, 4)))
			.add(item(GLOWSTONE_DUST).setWeight(7).apply(count(3, 7)))
			.add(item(GHAST_TEAR))
		));

		consumer.accept(VOID_FISHING_LOCATION_FLESH_TUNDRA, lootTable().withPool(pool()
			.add(item(CRIMSON_TIGERFISH).setWeight(27))
			.add(item(FLESHY_PIRANHA).setWeight(19))
			.add(item(ROTTEN_FLESH).setWeight(16).apply(count(3, 6)))
			.add(item(NETHER_WART_BLOCK).setWeight(12).apply(count(2, 3)))
			.add(item(GUNPOWDER).setWeight(12).apply(count(1, 5)))
			.add(item(BONE).setWeight(14).apply(count(1, 4)))
		));

		consumer.accept(VOID_FISHING_LOCATION_FROZEN_SPIKES, lootTable().withPool(pool()
			.add(item(FROST_MINNOW).setWeight(7))
			.add(item(ICE).apply(count(2, 4)))
			.add(item(PACKED_ICE).apply(count(2, 4)))
			.add(item(BLUE_ICE).apply(count(2, 3)))
			.add(item(SNOW_BLOCK).apply(count(2, 4)))
			.add(item(SNOWBALL).apply(count(3, 8)))
		));

		consumer.accept(VOID_FISHING_LOCATION_VANILLA_BIOMES, lootTable().withPool(pool()
			.add(item(OVERGROWN_COD).setWeight(8))
			.add(item(CHORUS_FLOWER).setWeight(3).apply(count(1, 2)))
			.add(item(ENDER_EYE).setWeight(2).apply(count(1, 2)))
			.add(item(CHORUS_PLANT).setWeight(4).apply(count(3, 8)))
		));

		consumer.accept(VOID_FISHING_LOCATION_PRISMARINE_FOREST, lootTable().withPool(pool()
			.add(item(BUBBLEFISH).setWeight(12))
			.add(item(PRISMARINE).setWeight(6).apply(count(3, 5)))
			.add(item(PRISMARINE_CRYSTALS).setWeight(5).apply(count(2, 6)))
			.add(item(GLOW_INK_SAC).setWeight(5).apply(count(1, 2)))
		));

		consumer.accept(VOID_FISHING_LOCATION_THE_HALLOW, lootTable().withPool(pool()
			.add(item(PRISMITE).setWeight(25))
			.add(item(STARLIGHT_SOOT).setWeight(9).apply(count(2, 4)))
			.add(item(CHERRY_LEAVES).setWeight(6).apply(count(2, 5)))
			.add(item(PINK_PETALS).setWeight(6).apply(count(3, 6)))
		));

		consumer.accept(VOID_FISHING_LOCATION_WARPED_MARSH, lootTable().withPool(pool()
			.add(item(GOOSH).setWeight(20))
			.add(item(MOSS_BLOCK).setWeight(3).apply(count(3, 5)))
			.add(item(VERDANT_FROGLIGHT).setWeight(6).apply(count(1, 2)))
			// extra
			.add(item(ASHEN_FROGLIGHT).setWeight(6).apply(count(1, 2)))
		));

		consumer.accept(VOID_FISHING_LOCATION_ENDLESS_DUNES, lootTable().withPool(pool()
			.add(item(POTASSIFISH).setWeight(4))
			.add(item(CACTUS_FLOWER).setWeight(5).apply(count(1, 2)))
			.add(item(CACTUS).setWeight(5).apply(count(2, 3)))
			.add(item(SAND).setWeight(3).apply(count(2, 3)))
			.add(item(DEAD_BUSH).setWeight(2))
			.add(item(DRY_SHORT_GRASS).apply(count(2, 3)))
			.add(item(DRY_TALL_GRASS).apply(count(1, 2)))
		));


	}


}
