package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.client.registry.item_tint_source.ColorTintSource;
import prismatic.shards.stellarity.key.StellarityEquipmentAssets;
import prismatic.shards.stellarity.registry.StellarityBlocks;

import java.util.List;
import java.util.Optional;

import static net.minecraft.client.data.models.BlockModelGenerators.*;
import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;
import static prismatic.shards.stellarity.registry.StellarityItems.*;


public class ModelProvider extends FabricModelProvider {
	public ModelProvider(FabricPackOutput output) {
		super(output);
	}

	public final static Item[] FLAT_ITEMS = new Item[]{
		SUSHI,
		GOLDEN_CHORUS_FRUIT,
		FRIED_CHORUS_FRUIT,
		FROZEN_CARPACCIO,
		ENDERMAN_FLESH,
		CRYSTAL_HEARTFISH,
		GRILLED_ENDERMAN_FLESH,
		FLAREFIN_KOI,
		AMETHYST_BUDFISH,
		CRIMSON_TIGERFISH,
		ENDER_KOI,
		FLESHY_PIRANHA,
		BUBBLEFISH,
		PRISMITE,
		OVERGROWN_COD,
		PRISMATIC_SUSHI,
		SHEPHERDS_PIE,
		CHORUS_PIE,
		PHO,
		PHANTOM_ITEM_FRAME,
		CHORUS_PLATING,
		ENDERITE_SHARD,
		ENDERITE_UPGRADE_SMITHING_TEMPLATE,
		HALLOWED_INGOT,
		SAND_RUNE,
		STARLIGHT_SOOT,
		GILDED_PURPUR_KEY,
		PURPUR_KEY,
		WINGED_KEY,
		PRISMATIC_PEARL,
		ENDONOMICON,
		MUSIC_DISC_DEVIANTS_LIGHT_MUSIC_BOX,
		MUSIC_DISC_FIRES_OF_HOKKAI,
		MUSIC_DISC_PRECIPICE_STEREO,
		ROYAL_JELLY,
		ROYAL_JELLY_II,
		SATCHEL_OF_VOIDS,
		DUSKBERRY,
		ENDER_EGG,
		VOIDED_SILVERFISH_SPAWN_EGG,
		VOIDED_ZOMBIE_SPAWN_EGG,
		VOIDED_SKELETON_SPAWN_EGG,
		VOIDED_SLIME_SPAWN_EGG,
		FLESH_PIGLIN_SPAWN_EGG,
		FROST_MINNOW,
		GOOSH,
		CHORUS_STEW,
		POTASSIFISH,
		ENDERMANS_HAND,
		DRAGONS_EYE,
	};

	public final static Block[] SIMPLE_BLOCKS = new Block[]{
		StellarityBlocks.ENDER_DIRT,
		StellarityBlocks.ROOTED_ENDER_DIRT,
		StellarityBlocks.ENDERITE_BLOCK,
		StellarityBlocks.COARSE_ENDER_DIRT
	};

	public void generateBush(BlockModelGenerators generators, Block block) {


		generators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
			.with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
				.select(0, plainVariant(generators.createSuffixedVariant(block, "_stage0", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(1, plainVariant(generators.createSuffixedVariant(block, "_stage1", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(2, plainVariant(generators.createSuffixedVariant(block, "_stage2", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(3, plainVariant(generators.createSuffixedVariant(block, "_stage3", ModelTemplates.CROSS, TextureMapping::cross)))
			)
		);
	}


	@Override
	public void generateBlockStateModels(@NonNull BlockModelGenerators generators) {
		for (var block : SIMPLE_BLOCKS)
			generators.createTrivialCube(block);

		generators.createNonTemplateModelBlock(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

		var grass = plainModel(GRASS_BLOCK_TEMPLATE.create(StellarityBlocks.ENDER_GRASS_BLOCK, new TextureMapping()
				.put(TextureSlot.BOTTOM, getBlockTexture(StellarityBlocks.ENDER_DIRT))
				.put(TextureSlot.PARTICLE, getBlockTexture(StellarityBlocks.ENDER_DIRT))
				.put(TextureSlot.TOP, getBlockTexture(StellarityBlocks.ENDER_GRASS_BLOCK, "_top"))
				.put(TextureSlot.SIDE, getBlockTexture(StellarityBlocks.ENDER_GRASS_BLOCK, "_side"))
				.put(TEXTURE_SLOT_OVERLAY, getBlockTexture(StellarityBlocks.ENDER_GRASS_BLOCK, "_side_overlay")),
			generators.modelOutput
		));
		var grassSnow = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(StellarityBlocks.ENDER_GRASS_BLOCK, "_snow", new TextureMapping()
				.put(TextureSlot.BOTTOM, getBlockTexture(StellarityBlocks.ENDER_DIRT))
				.copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
				.put(TextureSlot.TOP, getBlockTexture(StellarityBlocks.ENDER_GRASS_BLOCK, "_top"))
				.put(TextureSlot.SIDE, getBlockTexture(StellarityBlocks.ENDER_GRASS_BLOCK, "_side"))
			, generators.modelOutput);
		generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK,
			createRotatedVariants(grass),
			plainVariant(grassSnow)
		);
		generators.registerSimpleTintedItemModel(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new GrassColorSource(1.0f, 0.5f));


		Variant model = plainModel(DIRT_PATH_TEMPLATE.create(StellarityBlocks.ENDER_DIRT_PATH, new TextureMapping()
				.put(TextureSlot.BOTTOM, getBlockTexture(StellarityBlocks.ENDER_DIRT))
				.copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE)
				.put(TextureSlot.TOP, getBlockTexture(StellarityBlocks.ENDER_DIRT_PATH, "_top"))
				.put(TextureSlot.SIDE, getBlockTexture(StellarityBlocks.ENDER_DIRT_PATH, "_side")),
			generators.modelOutput
		));
		generators.blockStateOutput.accept(MultiVariantGenerator.dispatch(StellarityBlocks.ENDER_DIRT_PATH, createRotatedVariants(model)));

		ModelTemplates.CUBE_ALL.create(Stellarity.id("block/purpur_block_alt1"), new TextureMapping()
				.put(TextureSlot.ALL, new Material(Stellarity.id("block/purpur_block_alt1"))),
			generators.modelOutput
		);
		ModelTemplates.CUBE_ALL.create(Stellarity.id("block/purpur_block_alt2"), new TextureMapping()
				.put(TextureSlot.ALL, new Material(Stellarity.id("block/purpur_block_alt2"))),
			generators.modelOutput
		);
		ITEM_FRAME_TEMPLATE.create(Stellarity.id("block/phantom_item_frame"), new TextureMapping()
				.put(TEXTURE_SLOT_WOOD, getBlockTexture(Blocks.PURPUR_PILLAR, "_top"))
				.copyForced(TEXTURE_SLOT_WOOD, TextureSlot.PARTICLE)
				.put(TextureSlot.BACK, getBlockTexture(Blocks.QUARTZ_PILLAR, "_top")),
			generators.modelOutput
		);

		generateBush(generators, StellarityBlocks.DUSKBERRY_BUSH);

		generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);

		generators.blockStateOutput.accept(createSimpleBlock(StellarityBlocks.COLORED_LEAVES, plainVariant(TexturedModel.LEAVES.create(StellarityBlocks.COLORED_LEAVES, generators.modelOutput))));
		generators.registerSimpleTintedItemModel(StellarityBlocks.COLORED_LEAVES, Stellarity.id("block/colored_leaves"), new ColorTintSource());
	}

	@Override
	public void generateItemModels(ItemModelGenerators generators) {
		for (var bow : List.of(CALL_OF_THE_VOID, SHARANGA, SPECTRAL_FURY)) {
			generators.generateBow(bow);
			generators.createFlatItemModel(bow, ModelTemplates.BOW);
		}

		generators.declareCustomModelItem(SHULKER_BODY);
		generators.generateFishingRod(FISHER_OF_VOIDS);

		generators.generateFlatItem(TAMARIS, ModelTemplates.FLAT_HANDHELD_ITEM);

		for (Item item : FLAT_ITEMS) {
			generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
		}


		generators.generateTrimmableItem(SHULKER_HELMET, StellarityEquipmentAssets.SHULKER, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
		generators.generateTrimmableItem(SHULKER_CHESTPLATE, StellarityEquipmentAssets.SHULKER, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
		generators.generateTrimmableItem(SHULKER_LEGGINGS, StellarityEquipmentAssets.SHULKER, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
		generators.generateTrimmableItem(SHULKER_BOOTS, StellarityEquipmentAssets.SHULKER, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

		generators.generateShield(COPPER_ELEKTRA_SHIELD);
		SHIELD_TEMPLATE.create(Stellarity.id("item/copper_elektra_shield"), new TextureMapping()
				.put(TextureSlot.PARTICLE, getBlockTexture(Blocks.DARK_OAK_PLANKS)),
			generators.modelOutput
		);
		SHIELD_BLOCKING_TEMPLATE.create(Stellarity.id("item/copper_elektra_shield_blocking"), new TextureMapping()
				.put(TextureSlot.PARTICLE, getBlockTexture(Blocks.DARK_OAK_PLANKS)),
			generators.modelOutput
		);


	}

	public static final TextureSlot TEXTURE_SLOT_OVERLAY = TextureSlot.create("overlay");
	public static final TextureSlot TEXTURE_SLOT_WOOD = TextureSlot.create("wood");
	public static final ModelTemplate GRASS_BLOCK_TEMPLATE = create("grass_block", TEXTURE_SLOT_OVERLAY, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);
	public static final ModelTemplate DIRT_PATH_TEMPLATE = create("dirt_path", TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.TOP);
	public static final ModelTemplate ITEM_FRAME_TEMPLATE = create("template_item_frame", TEXTURE_SLOT_WOOD, TextureSlot.BACK);
	public static final ModelTemplate SHIELD_TEMPLATE = createItem("shield");
	public static final ModelTemplate SHIELD_BLOCKING_TEMPLATE = createItem("shield_blocking");


	private static ModelTemplate create(final TextureSlot... slots) {
		return new ModelTemplate(Optional.empty(), Optional.empty(), slots);
	}

	private static ModelTemplate create(final String id, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("block/" + id)), Optional.empty(), slots);
	}

	private static ModelTemplate createItem(final String id, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("item/" + id)), Optional.empty(), slots);
	}

	private static ModelTemplate createItem(final String id, final String suffix, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("item/" + id)), Optional.of(suffix), slots);
	}

	private static ModelTemplate create(final String id, final String suffix, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("block/" + id)), Optional.of(suffix), slots);
	}
}