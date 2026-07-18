package dev.coder2195.stellarity.registry;

import com.google.common.collect.ImmutableSet;
import dev.coder2195.stellarity.Stellarity;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

import static dev.coder2195.stellarity.registry.StellarityVillagerTradeSets.*;

public interface StellarityVillagerProfessions {
	Holder.Reference<VillagerProfession> ARMORER = register(
		"armorer",
		true,
		PoiTypes.ARMORER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, ARMORER_LEVEL_1), Int2ObjectMap.entry(2, ARMORER_LEVEL_2), Int2ObjectMap.entry(3, ARMORER_LEVEL_3), Int2ObjectMap.entry(4, ARMORER_LEVEL_4), Int2ObjectMap.entry(5, ARMORER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> BUTCHER = register(
		"butcher",
		true,
		PoiTypes.BUTCHER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, BUTCHER_LEVEL_1), Int2ObjectMap.entry(2, BUTCHER_LEVEL_2), Int2ObjectMap.entry(3, BUTCHER_LEVEL_3), Int2ObjectMap.entry(4, BUTCHER_LEVEL_4), Int2ObjectMap.entry(5, BUTCHER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> CARTOGRAPHER = register(
		"cartographer",
		true,
		PoiTypes.CARTOGRAPHER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, CARTOGRAPHER_LEVEL_1), Int2ObjectMap.entry(2, CARTOGRAPHER_LEVEL_2), Int2ObjectMap.entry(3, CARTOGRAPHER_LEVEL_3), Int2ObjectMap.entry(4, CARTOGRAPHER_LEVEL_4), Int2ObjectMap.entry(5, CARTOGRAPHER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> CLERIC = register(
		"cleric",
		true,
		PoiTypes.CLERIC,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, CLERIC_LEVEL_1), Int2ObjectMap.entry(2, CLERIC_LEVEL_2), Int2ObjectMap.entry(3, CLERIC_LEVEL_3), Int2ObjectMap.entry(4, CLERIC_LEVEL_4), Int2ObjectMap.entry(5, CLERIC_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> FARMER = register(
		"farmer",
		true,
		PoiTypes.FARMER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, FARMER_LEVEL_1), Int2ObjectMap.entry(2, FARMER_LEVEL_2), Int2ObjectMap.entry(3, FARMER_LEVEL_3), Int2ObjectMap.entry(4, FARMER_LEVEL_4), Int2ObjectMap.entry(5, FARMER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> FISHERMAN = register(
		"fisherman",
		true,
		PoiTypes.FISHERMAN,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, FISHERMAN_LEVEL_1), Int2ObjectMap.entry(2, FISHERMAN_LEVEL_2), Int2ObjectMap.entry(3, FISHERMAN_LEVEL_3), Int2ObjectMap.entry(4, FISHERMAN_LEVEL_4), Int2ObjectMap.entry(5, FISHERMAN_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> FLETCHER = register(
		"fletcher",
		true,
		PoiTypes.FLETCHER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, FLETCHER_LEVEL_1), Int2ObjectMap.entry(2, FLETCHER_LEVEL_2), Int2ObjectMap.entry(3, FLETCHER_LEVEL_3), Int2ObjectMap.entry(4, FLETCHER_LEVEL_4), Int2ObjectMap.entry(5, FLETCHER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> LEATHERWORKER = register(
		"leatherworker",
		true,
		PoiTypes.LEATHERWORKER,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, LEATHERWORKER_LEVEL_1), Int2ObjectMap.entry(2, LEATHERWORKER_LEVEL_2), Int2ObjectMap.entry(3, LEATHERWORKER_LEVEL_3), Int2ObjectMap.entry(4, LEATHERWORKER_LEVEL_4), Int2ObjectMap.entry(5, LEATHERWORKER_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> LIBRARIAN = register(
		"librarian",
		true,
		PoiTypes.LIBRARIAN,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, LIBRARIAN_LEVEL_1), Int2ObjectMap.entry(2, LIBRARIAN_LEVEL_2), Int2ObjectMap.entry(3, LIBRARIAN_LEVEL_3), Int2ObjectMap.entry(4, LIBRARIAN_LEVEL_4), Int2ObjectMap.entry(5, LIBRARIAN_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> MASON = register(
		"mason",
		true,
		PoiTypes.MASON,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, MASON_LEVEL_1), Int2ObjectMap.entry(2, MASON_LEVEL_2), Int2ObjectMap.entry(3, MASON_LEVEL_3), Int2ObjectMap.entry(4, MASON_LEVEL_4), Int2ObjectMap.entry(5, MASON_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> SHEPHERD = register(
		"shepherd",
		true,
		PoiTypes.SHEPHERD,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, SHEPHERD_LEVEL_1), Int2ObjectMap.entry(2, SHEPHERD_LEVEL_2), Int2ObjectMap.entry(3, SHEPHERD_LEVEL_3), Int2ObjectMap.entry(4, SHEPHERD_LEVEL_4), Int2ObjectMap.entry(5, SHEPHERD_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> TOOLSMITH = register(
		"toolsmith",
		true,
		PoiTypes.TOOLSMITH,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TOOLSMITH_LEVEL_1), Int2ObjectMap.entry(2, TOOLSMITH_LEVEL_2), Int2ObjectMap.entry(3, TOOLSMITH_LEVEL_3), Int2ObjectMap.entry(4, TOOLSMITH_LEVEL_4), Int2ObjectMap.entry(5, TOOLSMITH_LEVEL_5))
	);

	Holder.Reference<VillagerProfession> WEAPONSMITH = register(
		"weaponsmith",
		true,
		PoiTypes.WEAPONSMITH,
		null,
		Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, WEAPONSMITH_LEVEL_1), Int2ObjectMap.entry(2, WEAPONSMITH_LEVEL_2), Int2ObjectMap.entry(3, WEAPONSMITH_LEVEL_3), Int2ObjectMap.entry(4, WEAPONSMITH_LEVEL_4), Int2ObjectMap.entry(5, WEAPONSMITH_LEVEL_5))
	);

	HashMap<String, Holder.Reference<VillagerProfession>> PROFESSION_MAP = new HashMap<>() {{
		put("armorer", ARMORER);
		put("butcher", BUTCHER);
		put("cartographer", CARTOGRAPHER);
		put("cleric", CLERIC);
		put("farmer", FARMER);
		put("fisherman", FISHERMAN);
		put("fletcher", FLETCHER);
		put("leatherworker", LEATHERWORKER);
		put("librarian", LIBRARIAN);
		put("mason", MASON);
		put("shepherd", SHEPHERD);
		put("toolsmith", TOOLSMITH);
		put("weaponsmith", WEAPONSMITH);
	}};

	static Holder.Reference<VillagerProfession> mapVanilla(Holder.Reference<VillagerProfession> original) {
		var id = original.key().identifier();
		if (!id.getNamespace().equals("minecraft")) {
			return original;
		}
		return PROFESSION_MAP.getOrDefault(id.getPath(), original);
	}

	static @Nullable List<ResourceKey<TradeSet>> getExtraTradeSets(VillagerData data) {
		var profession = data.profession();
		var level = data.level();
		if (profession.is(ARMORER.key())) {
			if (level == 1) return List.of(ARMORER_LEVEL_1_2);
			if (level == 2) return List.of(ARMORER_LEVEL_2_2);
			return null;
		}
		if (profession.is(BUTCHER.key())) {
			if (level == 2) return List.of(BUTCHER_LEVEL_2_2);
			if (level == 3) return List.of(BUTCHER_LEVEL_3_2);
			if (level == 4) return List.of(BUTCHER_LEVEL_4_2);
			return null;
		}
		if (profession.is(CARTOGRAPHER.key())) {
			return null;
		}
		if (profession.is(CLERIC.key())) {
			if (level == 1) return List.of(CLERIC_LEVEL_1_2);
			if (level == 2) return List.of(CLERIC_LEVEL_2_2);
			return null;
		}
		if (profession.is(FARMER.key())) {
			if (level == 1) return List.of(FARMER_LEVEL_1_2);
			if (level == 2) return List.of(FARMER_LEVEL_2_2);
			if (level == 3) return List.of(FARMER_LEVEL_3_2);
			if (level == 4) return List.of(FARMER_LEVEL_4_2);
			return null;
		}
		if (profession.is(FISHERMAN.key())) {
			if (level == 1) return List.of(FISHERMAN_LEVEL_1_2);
			if (level == 2) return List.of(FISHERMAN_LEVEL_2_2);
			if (level == 3) return List.of(FISHERMAN_LEVEL_3_2);
			if (level == 4) return List.of(FISHERMAN_LEVEL_4_2);
			return null;
		}
		if (profession.is(FLETCHER.key())) {
			if (level == 1) return List.of(FLETCHER_LEVEL_1_2);
			return null;
		}
		if (profession.is(LEATHERWORKER.key())) {
			if (level == 4) return List.of(LEATHERWORKER_LEVEL_4_2);
			return null;
		}
		if (profession.is(LIBRARIAN.key())) {
			if (level == 1) return List.of(LIBRARIAN_LEVEL_1_2);
			if (level == 3) return List.of(LIBRARIAN_LEVEL_3_2);
			if (level == 4) return List.of(LIBRARIAN_LEVEL_4_2);
			return null;
		}
		if (profession.is(MASON.key())) {
			if (level == 2) return List.of(MASON_LEVEL_2_2);
			if (level == 3) return List.of(MASON_LEVEL_3_2);
			if (level == 4) return List.of(MASON_LEVEL_4_2);
			if (level == 5) return List.of(MASON_LEVEL_5_2);
			return null;
		}
		if (profession.is(SHEPHERD.key())) {
			if (level == 2) return List.of(SHEPHERD_LEVEL_2_2);
			if (level == 3) return List.of(SHEPHERD_LEVEL_3_2, SHEPHERD_LEVEL_3_3);
			if (level == 4) return List.of(SHEPHERD_LEVEL_4_2, SHEPHERD_LEVEL_4_3);
			if (level == 5) return List.of(SHEPHERD_LEVEL_5_2);
			return null;
		}
		if (profession.is(TOOLSMITH.key())) {
			if (level == 1) return List.of(TOOLSMITH_LEVEL_1_2);
			if (level == 2) return List.of(TOOLSMITH_LEVEL_2_2);
			if (level == 4) return List.of(TOOLSMITH_LEVEL_4_2);
			return null;
		}
		if (profession.is(WEAPONSMITH.key())) {
			if (level == 1) return List.of(WEAPONSMITH_LEVEL_1_2);
			if (level == 2) return List.of(WEAPONSMITH_LEVEL_2_2);
			return null;
		}

		return null;
	}


	static Holder.Reference<VillagerProfession> register(String id, boolean vanilla, final ResourceKey<PoiType> jobSite, final @Nullable SoundEvent workSound, final Int2ObjectMap<ResourceKey<TradeSet>> trades) {
		var key = Stellarity.key(Registries.VILLAGER_PROFESSION, id);

		return Registry.registerForHolder(BuiltInRegistries.VILLAGER_PROFESSION, key, new VillagerProfession(
			Component.translatable("entity." + (vanilla ? "minecraft" : Stellarity.MOD_ID) + ".villager." + id),
			(site) -> site.is(jobSite),
			(site) -> site.is(jobSite),
			ImmutableSet.of(), ImmutableSet.of(),
			workSound,
			trades
		));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Villager Professions");
	}
}
