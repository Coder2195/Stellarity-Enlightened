package prismatic.shards.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.MobEffectsPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.storage.loot.LootContext;
import prismatic.shards.stellarity.key.StellarityBiomes;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.Items.*;
import static prismatic.shards.stellarity.registry.StellarityEntityTypes.*;
import static prismatic.shards.stellarity.registry.StellarityItems.STARLIGHT_SOOT;
import static prismatic.shards.stellarity.util.LootUtil.*;

public class EntityLootTableProvider extends FabricEntityLootSubProvider {
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	public EntityLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
		this.registryLookup = registryLookup;
	}

	@Override
	public void generate() {
		var lookup = registryLookup.join();

		add(VOIDED_ZOMBIE, lootTable()
			.withPool(pool().setRolls(num(0, 2))
				.add(item(ROTTEN_FLESH).apply(count(num(0, 1))).apply(enchantCount(lookup, num(0, 1))))
				.add(item(LEATHER).apply(count(num(-1, 2))))
			).withPool(pool().add(item(COPPER_INGOT).when(playerKill()).when(chanceEnchanted(lookup, 0.07f, 0.02f))))
			.withPool(pool().add(item(SAND).apply(count(num(1, 4))).apply(enchantCount(registries, num(0, 1))).when(biome(lookup.getOrThrow(StellarityBiomes.ENDLESS_DUNES)))))
		);
		add(VOIDED_SILVERFISH, lootTable().withPool(
			pool().setRolls(num(0, 1)).add(item(ENDER_PEARL).apply(count(num(0, 1))).apply(enchantCount(lookup, num(0, 1))))
		));
		add(VOIDED_SKELETON, lootTable()
			.withPool(pool().add(alternatives(
				item(SPECTRAL_ARROW).apply(count(num(0, 2))).apply(enchantCount(lookup, num(0, 1))).when(
					entityProperty(LootContext.EntityTarget.THIS, new EntityPredicate.Builder().effects(new MobEffectsPredicate.Builder().and(MobEffects.GLOWING)))
				), item(ARROW).apply(count(num(0, 2))).apply(enchantCount(lookup, num(0, 1)))
			)))
			.withPool(pool().add(item(BONE).apply(count(num(0, 2))).apply(enchantCount(lookup, num(0, 1)))))
		);
		add(VOIDED_SLIME, lootTable().withPool(pool().add(item(SLIME_BALL).apply(count(num(0, 4))).apply(enchantCount(lookup, num(0, 1))))));
		add(FLESH_PIGLIN, lootTable()
			.withPool(pool().add(item(ROTTEN_FLESH).apply(count(num(0, 1))).apply(enchantCount(lookup, num(0, 1)))))
			.withPool(pool()
				.add(item(NETHER_WART_BLOCK).apply(count(num(0, 1))))
				.add(item(NETHER_WART).setWeight(2).apply(count(num(0, 1)))).apply(enchantCount(lookup, num(0, 1)))
			).withPool(pool().setRolls(num(0, 2))
				.add(item(CRIMSON_FUNGUS).apply(count(num(0, 1))).apply(enchantCount(lookup, num(0, 1))))
				.add(item(CRIMSON_ROOTS).apply(count(num(0, 1))).apply(enchantCount(lookup, num(0, 1))))
			)
		);
		add(PIXIE, lootTable().withPool(pool().add(item(STARLIGHT_SOOT).apply(count(num(0, 2))).apply(enchantCount(lookup, num(0, 1))))));

	}
}
