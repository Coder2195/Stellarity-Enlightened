package prismatic.shards.stellarity.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import prismatic.shards.stellarity.Stellarity;

import java.util.HashMap;

public interface StellarityPotions {
	HashMap<Holder<Potion>, Integer> COLORS = new HashMap<>();
	HashMap<Holder<Potion>, Integer> NAME_COLORS = new HashMap<>();

	Holder<Potion> BLIND_RAGE = register("blind_rage", "blind_rage",
		new MobEffectInstance(MobEffects.DARKNESS, 15 * 20, 0),
		new MobEffectInstance(MobEffects.STRENGTH, 15 * 20, 2));
	Holder<Potion> LONG_BLIND_RAGE = register("long_blind_rage", "blind_rage",
		new MobEffectInstance(MobEffects.DARKNESS, 22 * 20, 0),
		new MobEffectInstance(MobEffects.STRENGTH, 22 * 20, 2)
	);

	Holder<Potion> ENDURANCE = register("endurance", "endurance", new MobEffectInstance(MobEffects.RESISTANCE, 2 * 60 * 20));
	Holder<Potion> LONG_ENDURANCE = register("long_endurance", "endurance", new MobEffectInstance(MobEffects.RESISTANCE, 4 * 60 * 20));
	Holder<Potion> STRONG_ENDURANCE = register("strong_endurance", "endurance", new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 1));

	Holder<Potion> ENTANGLEMENT = register("entanglement", "entanglement", new MobEffectInstance(MobEffects.SLOWNESS, 11 * 20, 4));
	Holder<Potion> LONG_ENTANGLEMENT = register("long_entanglement", "entanglement", new MobEffectInstance(MobEffects.SLOWNESS, 14 * 20, 4));
	Holder<Potion> STRONG_ENTANGLEMENT = register("strong_entanglement", "entanglement", new MobEffectInstance(MobEffects.SLOWNESS, 8 * 20, 5));

	Holder<Potion> FROST_CLOUD = register("frost_cloud", "frost_cloud", new MobEffectInstance(MobEffects.SLOWNESS, 6, 98));

	Holder<Potion> HELLFIRE_TREADER = register("hellfire_treader", "hellfire_treader",
		new MobEffectInstance(MobEffects.SPEED, 4 * 60 * 20, 0),
		new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4 * 60 * 20, 0)
	);
	Holder<Potion> LONG_HELLFIRE_TREADER = register("long_hellfire_treader", "hellfire_treader",
		new MobEffectInstance(MobEffects.SPEED, 8 * 60 * 20, 0),
		new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 8 * 60 * 20, 0)
	);
	Holder<Potion> STRONG_HELLFIRE_TREADER = register("strong_hellfire_treader", "hellfire_treader",
		new MobEffectInstance(MobEffects.SPEED, 2 * 60 * 20, 1),
		new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2 * 60 * 20, 1)
	);

	Holder<Potion> AMARENE = register("amarene", "amarene",
		new MobEffectInstance(MobEffects.SPEED, 48 * 20, 1),
		new MobEffectInstance(MobEffects.REGENERATION, 48 * 20),
		new MobEffectInstance(MobEffects.WEAKNESS, 48 * 20),
		new MobEffectInstance(MobEffects.SATURATION, 3, 1),
		new MobEffectInstance(MobEffects.LUCK, 4800)
	);

	Holder<Potion> LIFEFORCE = register("lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 3 * 60 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 3 * 60 * 20)
	);
	Holder<Potion> LONG_LIFEFORCE = register("long_lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 6 * 60 * 20),
		new MobEffectInstance(MobEffects.REGENERATION, 6 * 60 * 20)
	);
	Holder<Potion> STRONG_LIFEFORCE = register("strong_lifeforce", "lifeforce",
		new MobEffectInstance(MobEffects.HEALTH_BOOST, 90 * 20, 1),
		new MobEffectInstance(MobEffects.REGENERATION, 90 * 20, 1)
	);

	Holder<Potion> SPELUNKER = register("spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 3 * 60 * 20),
		new MobEffectInstance(MobEffects.HASTE, 3 * 60 * 20)
	);
	Holder<Potion> LONG_SPELUNKER = register("long_spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 6 * 60 * 20),
		new MobEffectInstance(MobEffects.HASTE, 6 * 60 * 20)
	);
	Holder<Potion> STRONG_SPELUNKER = register("strong_spelunker", "spelunker",
		new MobEffectInstance(MobEffects.NIGHT_VISION, 90 * 20),
		new MobEffectInstance(MobEffects.HASTE, 90 * 20, 1)
	);

	Holder<Potion> POSEIDONS_NECTAR = register("poseidons_nectar", "poseidons_nectar",
		new MobEffectInstance(MobEffects.CONDUIT_POWER, 390 * 20),
		new MobEffectInstance(MobEffects.ABSORPTION, 390 * 20),
		new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 390 * 20)
	);

	Holder<Potion> REGENERAGA = register("regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 140, 2),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);
	Holder<Potion> LONG_REGENERAGA = register("long_regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 210, 2),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);
	Holder<Potion> STRONG_REGENERAGA = register("strong_regeneraga", "regeneraga",
		new MobEffectInstance(MobEffects.REGENERATION, 70, 4),
		new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 1)
	);

	Holder<Potion> RED = register("red", "red",
		new MobEffectInstance(MobEffects.DARKNESS, -1, 9),
		new MobEffectInstance(MobEffects.SLOWNESS, -1, 9),
		new MobEffectInstance(MobEffects.MINING_FATIGUE, -1, 9),
		new MobEffectInstance(MobEffects.NAUSEA, -1),
		new MobEffectInstance(MobEffects.BLINDNESS, -1),
		new MobEffectInstance(MobEffects.HUNGER, -1, 9),
		new MobEffectInstance(MobEffects.WEAKNESS, -1, 9),
		new MobEffectInstance(MobEffects.WITHER, -1, 9),
		new MobEffectInstance(MobEffects.POISON, -1, 9),
		new MobEffectInstance(MobEffects.BAD_OMEN, 1200, 9),
		new MobEffectInstance(MobEffects.UNLUCK, -1, 9),
		new MobEffectInstance(MobEffects.GLOWING, -1)
	);

	Holder<Potion> LUCK = register("luck", "luck",
		new MobEffectInstance(MobEffects.LUCK)
	);

	Holder<Potion> CHORUS_JUICE = register("chorus_juice", "chorus_juice",
		new MobEffectInstance(MobEffects.SPEED, 50 * 20),
		new MobEffectInstance(MobEffects.JUMP_BOOST, 50 * 20)
	);


	private static Holder<Potion> register(String id, String name, MobEffectInstance... effects) {
		return Registry.registerForHolder(BuiltInRegistries.POTION, Stellarity.id(id), new Potion("stellarity." + name, effects));
	}

	static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Potions");
		COLORS.put(BLIND_RAGE, 0x6e5600);
		COLORS.put(LONG_BLIND_RAGE, 0x6e5600);
		COLORS.put(ENDURANCE, 0x9146f0);
		COLORS.put(LONG_ENDURANCE, 0x9146f0);
		COLORS.put(STRONG_ENDURANCE, 0x9146f0);
		COLORS.put(ENTANGLEMENT, 0x812575);
		COLORS.put(LONG_ENTANGLEMENT, 0x812575);
		COLORS.put(STRONG_ENTANGLEMENT, 0x812575);
		COLORS.put(FROST_CLOUD, 0x7e9cb8);
		COLORS.put(HELLFIRE_TREADER, 0xf67a00);
		COLORS.put(LONG_HELLFIRE_TREADER, 0xf67a00);
		COLORS.put(STRONG_HELLFIRE_TREADER, 0xf67a00);
		COLORS.put(AMARENE, 0xc90d17);
		COLORS.put(LIFEFORCE, 0xff5353);
		COLORS.put(LONG_LIFEFORCE, 0xff5353);
		COLORS.put(STRONG_LIFEFORCE, 0xff5353);
		COLORS.put(SPELUNKER, 0xffd900);
		COLORS.put(LONG_SPELUNKER, 0xffd900);
		COLORS.put(STRONG_SPELUNKER, 0xffd900);
		COLORS.put(POSEIDONS_NECTAR, 0x47a6ff);
		COLORS.put(RED, 0xf50022);
		COLORS.put(REGENERAGA, 0xf60077);
		COLORS.put(LONG_REGENERAGA, 0xf60077);
		COLORS.put(STRONG_REGENERAGA, 0xf60077);
		COLORS.put(LUCK, 0x59c106);
		COLORS.put(CHORUS_JUICE, 0x631969);

		NAME_COLORS.put(SPELUNKER, TextColor.WHITE.getValue());
		NAME_COLORS.put(RED, 0xFF5454);
		NAME_COLORS.put(REGENERAGA, TextColor.YELLOW.getValue());
		NAME_COLORS.put(ENDURANCE, TextColor.WHITE.getValue());
		NAME_COLORS.put(ENTANGLEMENT, TextColor.WHITE.getValue());
		NAME_COLORS.put(LIFEFORCE, TextColor.WHITE.getValue());
		NAME_COLORS.put(AMARENE, 0xC90D17);
		NAME_COLORS.put(FROST_CLOUD, TextColor.WHITE.getValue());
		NAME_COLORS.put(POSEIDONS_NECTAR, TextColor.YELLOW.getValue());
	}
}
