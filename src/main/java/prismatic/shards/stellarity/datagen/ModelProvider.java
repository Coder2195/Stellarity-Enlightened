package prismatic.shards.stellarity.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityBlocks;
import prismatic.shards.stellarity.key.StellarityEquipmentAssets;

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
		FROST_MINNOW,
		GOOSH,
		CHORUS_STEW,
		POTASSIFISH
	};

	public void generateBush(BlockModelGenerators generators, Block block) {


		generators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)

			.with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
				.select(0, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage0", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(1, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage1", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(2, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage2", ModelTemplates.CROSS, TextureMapping::cross)))
				.select(3, BlockModelGenerators.plainVariant(generators.createSuffixedVariant(block, "_stage3", ModelTemplates.CROSS, TextureMapping::cross)))
			)
		);


	}


	@Override
	public void generateBlockStateModels(BlockModelGenerators generators) {
		generators.createTrivialCube(StellarityBlocks.ENDER_DIRT);
		generators.createTrivialCube(StellarityBlocks.ROOTED_ENDER_DIRT);
		generators.createNonTemplateModelBlock(StellarityBlocks.ENDER_DIRT_PATH);
		generators.createNonTemplateModelBlock(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

		generateBush(generators, StellarityBlocks.DUSKBERRY_BUSH);

		generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
		generators.registerSimpleItemModel(StellarityBlocks.ASHEN_FROGLIGHT, Stellarity.id("block/ashen_froglight"));
		generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, new MultiVariant(WeightedList.<Variant>builder()
			.add(new Variant(Stellarity.id("block/ender_grass_block")))
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 90)
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 180)
			.add(new Variant(Stellarity.id("block/ender_grass_block")), 270)
			.build()), new MultiVariant(WeightedList.<Variant>builder().add(new Variant(Stellarity.id("block/ender_grass_block_snowy"))).build()));
		generators.registerSimpleTintedItemModel(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new GrassColorSource(1.0f, 0.5f));


	}

	@Override
	public void generateItemModels(ItemModelGenerators generators) {

		generators.generateBow(CALL_OF_THE_VOID);
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


	}
}