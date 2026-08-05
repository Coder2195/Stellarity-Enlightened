package dev.coder2195.stellarity.datagen;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.recipe.AltarDyeRecipe;
import dev.coder2195.stellarity.recipe.AltarRecipe;
import dev.coder2195.stellarity.recipe.AltarSimpleRecipe;
import dev.coder2195.stellarity.recipe.AltarUpgradeRecipe;

import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.Items.*;
import static dev.coder2195.stellarity.registry.StellarityItems.*;


public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void altarOfTheAccursed(RecipeOutput output, String id, AltarRecipe recipe) {
		output.accept(Stellarity.key(Registries.RECIPE, id), recipe, null);
	}

	public static class Ingredients extends LinkedHashMap<Ingredient, Integer> {
		public Ingredients put(ItemLike item, int count) {
			put(Ingredient.of(item), count);
			return this;
		}

		public Ingredients put(ItemLike item) {
			return put(item, 1);
		}

		public Ingredients putMany(int count, ItemLike... items) {
			put(Ingredient.of(items), count);
			return this;
		}
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
			new Ingredients().put(DIAMOND).put(LAPIS_LAZULI),
			new ItemStackTemplate(AMETHYST_SHARD)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/chorus_plating", new AltarSimpleRecipe(
			new Ingredients().put(IRON_INGOT).put(POPPED_CHORUS_FRUIT, 2),
			new ItemStackTemplate(CHORUS_PLATING)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/enderite_upgrade_smithing_template", new AltarSimpleRecipe(
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(ENDERITE_SHARD, 5).put(PURPUR_BLOCK, 9),
			new ItemStackTemplate(ENDERITE_UPGRADE_SMITHING_TEMPLATE, 2)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/endonomicon", new AltarSimpleRecipe(
			new Ingredients().put(ENCHANTED_BOOK),
			new ItemStackTemplate(ENDONOMICON)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/satchel_of_voids", new AltarSimpleRecipe(
			new Ingredients().put(BUNDLE).put(NETHER_STAR, 2).put(NETHERITE_INGOT, 4).put(ENDERITE_SHARD, 64).put(STARLIGHT_SOOT, 64),
			new ItemStackTemplate(SATCHEL_OF_VOIDS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/satchel_of_voids_alternative", new AltarSimpleRecipe(
			new Ingredients().put(LEATHER).put(STRING).put(NETHER_STAR, 2).put(NETHERITE_INGOT, 4).put(ENDERITE_SHARD, 64).put(STARLIGHT_SOOT, 64),
			new ItemStackTemplate(SATCHEL_OF_VOIDS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_helmet", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_HELMET),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(SHULKER_SHELL, 4),
			new ItemStackTemplate(SHULKER_HELMET)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_chestplate", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_CHESTPLATE),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(SHULKER_SHELL, 4),
			new ItemStackTemplate(SHULKER_CHESTPLATE)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_leggings", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_LEGGINGS),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(SHULKER_SHELL, 4),
			new ItemStackTemplate(SHULKER_LEGGINGS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/shulker_boots", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_BOOTS),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(SHULKER_SHELL, 4),
			new ItemStackTemplate(SHULKER_BOOTS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/dye_elytra", new AltarDyeRecipe(Ingredient.of(ELYTRA)));

		altarOfTheAccursed(output, "altar_of_the_accursed/spectral_fury", new AltarUpgradeRecipe(
			Ingredient.of(SHARANGA),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(PHANTOM_MEMBRANE, 8).put(DIAMOND, 3),
			new ItemStackTemplate(SPECTRAL_FURY)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/tamaris", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_SWORD),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(ENDERITE_SHARD, 8).put(WITHER_SKELETON_SKULL).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(TAMARIS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/champion_helmet", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_HELMET),
			new Ingredients().put(CHORUS_PLATING, 4).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(CHAMPION_HELMET)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/champion_chestplate", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_CHESTPLATE),
			new Ingredients().put(CHORUS_PLATING, 4).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(CHAMPION_CHESTPLATE)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/champion_leggings", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_LEGGINGS),
			new Ingredients().put(CHORUS_PLATING, 4).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(CHAMPION_LEGGINGS)
		));

		altarOfTheAccursed(output, "altar_of_the_accursed/champion_boots", new AltarUpgradeRecipe(
			Ingredient.of(NETHERITE_BOOTS),
			new Ingredients().put(CHORUS_PLATING, 4).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(CHAMPION_BOOTS)
		));
	}

	@Override
	public @NonNull String getName() {
		return Stellarity.MOD_ID;
	}

}
