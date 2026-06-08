package prismatic.shards.stellarity.datagen;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.recipe.AltarDyeRecipe;
import prismatic.shards.stellarity.registry.recipe.AltarRecipe;
import prismatic.shards.stellarity.registry.recipe.AltarSimpleRecipe;
import prismatic.shards.stellarity.registry.recipe.AltarUpgradeRecipe;

import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.registry.StellarityItems.*;


public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void altarOfTheAccursed(RecipeOutput output, String id, AltarRecipe recipe) {
		output.accept(Stellarity.key(Registries.RECIPE, id), recipe, null);
	}


	@Override
	public net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
		return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
			@Override
			public void buildRecipes() {
				shapeless(RecipeCategory.BUILDING_BLOCKS, ENDERITE_BLOCK)
					.requires(ENDERITE_SHARD, 9)
					.unlockedBy(getHasName(ENDERITE_SHARD), has(ENDERITE_SHARD))
					.save(output);

				shapeless(RecipeCategory.BUILDING_BLOCKS, ENDERITE_SHARD, 9)
					.requires(ENDERITE_BLOCK)
					.unlockedBy(getHasName(ENDERITE_BLOCK), has(ENDERITE_BLOCK))
					.save(output);

				this.shaped(RecipeCategory.BUILDING_BLOCKS, COARSE_ENDER_DIRT, 4)
					.pattern("DG")
					.pattern("GD")
					.define('D', ENDER_DIRT)
					.define('G', GRAVEL)
					.unlockedBy(getHasName(GRAVEL), this.has(GRAVEL))
					.unlockedBy(getHasName(ENDER_DIRT), this.has(ENDER_DIRT))
					.save(this.output);

				provider.allRegistriesLifecycle().add(Lifecycle.stable());
				RecipeProvider.this.buildRecipes(provider, output);
			}
		};
	}


	public void buildRecipes(HolderLookup.Provider provider, RecipeOutput output) {
		altarOfTheAccursed(output, "altar_of_the_accursed/lapis_to_amethyst", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(DIAMOND), 1);
				put(Ingredient.of(LAPIS_LAZULI), 1);
			}},
			new ItemStackTemplate(AMETHYST_SHARD)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/chorus_plating", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(IRON_INGOT), 1);
				put(Ingredient.of(POPPED_CHORUS_FRUIT), 2);
			}},
			new ItemStackTemplate(CHORUS_PLATING)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/enderite_upgrade_smithing_template", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(ENDERITE_SHARD), 5);
				put(Ingredient.of(PURPUR_BLOCK), 9);
			}},
			new ItemStackTemplate(ENDERITE_UPGRADE_SMITHING_TEMPLATE, 2)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/endonomicon", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENCHANTED_BOOK), 1);
			}},
			new ItemStackTemplate(ENDONOMICON, 1)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/satchel_of_voids", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(BUNDLE), 1);
				put(Ingredient.of(NETHER_STAR), 2);
				put(Ingredient.of(NETHERITE_INGOT), 4);
				put(Ingredient.of(ENDERITE_SHARD), 64);
				put(Ingredient.of(STARLIGHT_SOOT), 64);
			}},
			new ItemStackTemplate(SATCHEL_OF_VOIDS, 1)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/satchel_of_voids_alternative", new AltarSimpleRecipe(
			new LinkedHashMap<>() {{
				put(Ingredient.of(LEATHER), 1);
				put(Ingredient.of(STRING), 1);
				put(Ingredient.of(NETHER_STAR), 2);
				put(Ingredient.of(NETHERITE_INGOT), 4);
				put(Ingredient.of(ENDERITE_SHARD), 64);
				put(Ingredient.of(STARLIGHT_SOOT), 64);
			}},
			new ItemStackTemplate(SATCHEL_OF_VOIDS, 1)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_helmet", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_HELMET),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_HELMET)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_chestplate", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_CHESTPLATE),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_CHESTPLATE)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_leggings", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_LEGGINGS),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_LEGGINGS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_boots", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_BOOTS),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(SHULKER_SHELL), 4);
			}},
			new ItemStackTemplate(SHULKER_BOOTS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/dye_elytra", new AltarDyeRecipe(Ingredient.of(ELYTRA)));

		altarOfTheAccursed(output, "altar_of_the_accursed/spectral_fury", new AltarUpgradeRecipe(
			Ingredient.of(SHARANGA),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(PHANTOM_MEMBRANE), 8);
				put(Ingredient.of(DIAMOND), 3);
			}},
			new ItemStackTemplate(SPECTRAL_FURY)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/tamaris", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_SWORD),
			new LinkedHashMap<>() {{
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
				put(Ingredient.of(ENDERITE_SHARD), 8);
				put(Ingredient.of(WITHER_SKELETON_SKULL), 1);
				put(Ingredient.of(ENDERITE_UPGRADE_SMITHING_TEMPLATE), 1);
			}},
			new ItemStackTemplate(TAMARIS)
		));
	}

	@Override
	public @NonNull String getName() {
		return Stellarity.MOD_ID;
	}

}
