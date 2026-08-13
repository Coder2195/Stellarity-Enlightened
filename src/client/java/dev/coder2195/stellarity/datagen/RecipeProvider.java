package dev.coder2195.stellarity.datagen;

import com.mojang.serialization.Lifecycle;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.recipe.AltarDyeRecipe;
import dev.coder2195.stellarity.recipe.AltarRecipe;
import dev.coder2195.stellarity.recipe.AltarSimpleRecipe;
import dev.coder2195.stellarity.recipe.AltarUpgradeRecipe;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static net.minecraft.world.item.Items.*;


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
					.save(output, "crafting/enderite_block");

				shapeless(RecipeCategory.BUILDING_BLOCKS, ENDERITE_SHARD, 9)
					.requires(ENDERITE_BLOCK)
					.unlockedBy(getHasName(ENDERITE_BLOCK), has(ENDERITE_BLOCK))
					.save(output, "crafting/enderite_shard");

				this.shaped(RecipeCategory.BUILDING_BLOCKS, COARSE_ENDER_DIRT, 4)
					.pattern("DG")
					.pattern("GD")
					.define('D', ENDER_DIRT)
					.define('G', GRAVEL)
					.unlockedBy(getHasName(GRAVEL), this.has(GRAVEL))
					.unlockedBy(getHasName(ENDER_DIRT), this.has(ENDER_DIRT))
					.save(this.output, "crafting/coarse_ender_dirt");

				this.shaped(RecipeCategory.MISC, CHORUS_PLANT, 4)
					.pattern("CC")
					.pattern("CC")
					.define('C', CHORUS_FRUIT)
					.unlockedBy(getHasName(CHORUS_FRUIT), this.has(CHORUS_FRUIT))
					.save(this.output, "crafting/chorus_plant_from_chorus_fruit");

				this.shapeless(RecipeCategory.BUILDING_BLOCKS, GLASS, 3)
					.requires(END_STONE).requires(BLAZE_POWDER)
					.unlockedBy(getHasName(END_STONE), this.has(END_STONE))
					.unlockedBy(getHasName(BLAZE_POWDER), this.has(BLAZE_POWDER))
					.save(this.output, "crafting/glass_from_end_stone");


				SimpleCookingRecipeBuilder.generic(Ingredient.of(POPPED_CHORUS_FRUIT), RecipeCategory.MISC, CookingBookCategory.MISC, GUNPOWDER, 0.05f, 150, BlastingRecipe::new)
					.group("gunpowder")
					.unlockedBy(getHasName(GUNPOWDER), this.has(GUNPOWDER))
					.save(this.output, "blasting/gunpowder_from_popped_chorus_fruit");

				SimpleCookingRecipeBuilder.generic(Ingredient.of(POPPED_CHORUS_FRUIT), RecipeCategory.MISC, CookingBookCategory.MISC, GUNPOWDER, 0, 600, CampfireCookingRecipe::new)
					.group("gunpowder")
					.unlockedBy(getHasName(GUNPOWDER), this.has(GUNPOWDER))
					.save(this.output, "campfire/gunpowder_from_popped_chorus_fruit");

				this.shaped(RecipeCategory.MISC, LEATHER, 2)
					.pattern("##")
					.pattern("##")
					.define('#', PHANTOM_MEMBRANE)
					.unlockedBy(getHasName(PHANTOM_MEMBRANE), this.has(PHANTOM_MEMBRANE))
					.save(this.output, "crafting/leather_from_phantom_membrane");

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


		var pieces = List.of(
			new Tuple2<>(NETHERITE_HELMET, "helmet"),
			new Tuple2<>(NETHERITE_CHESTPLATE, "chestplate"),
			new Tuple2<>(NETHERITE_LEGGINGS, "leggings"),
			new Tuple2<>(NETHERITE_BOOTS, "boots")
		);

		for (int i = 0; i < pieces.size(); i++) {
			var piece = pieces.get(i);
			for (var armorType : List.<Tuple3<String, Item[], Supplier<Ingredients>>>of(
				new Tuple3<>("shulker", new Item[]{SHULKER_HELMET, SHULKER_CHESTPLATE, SHULKER_LEGGINGS, SHULKER_BOOTS}, () -> new Ingredients().put(SHULKER_SHELL, 4)),
				new Tuple3<>("champion", new Item[]{CHAMPION_HELMET, CHAMPION_CHESTPLATE, CHAMPION_LEGGINGS, CHAMPION_BOOTS}, () -> new Ingredients().put(CHORUS_PLATING, 4)),
				new Tuple3<>("hallowed", new Item[]{HALLOWED_HELMET, HALLOWED_CHESTPLATE, HALLOWED_LEGGINGS, HALLOWED_BOOTS}, () -> new Ingredients().put(HALLOWED_INGOT, 4)),
				new Tuple3<>("floral", new Item[]{FLORAL_HELMET, FLORAL_CHESTPLATE, FLORAL_LEGGINGS, FLORAL_BOOTS}, () -> new Ingredients().put(CHERRY_LEAVES, 8))
			))
				altarOfTheAccursed(output, "altar_of_the_accursed/" + armorType._1() + "_" + piece._2(), new AltarUpgradeRecipe(
					Ingredient.of(piece._1()),
					armorType._3().get().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
					new ItemStackTemplate(armorType._2()[i])
				));

		}
	}

	@Override
	public @NonNull String getName() {
		return Stellarity.MOD_ID;
	}

}
