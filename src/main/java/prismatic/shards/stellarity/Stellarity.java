package prismatic.shards.stellarity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;
import prismatic.shards.stellarity.event.*;
import prismatic.shards.stellarity.registry.*;

public class Stellarity implements ModInitializer {
	public static final String VERSION = /*$ minecraft*/ "26.2-snapshot-5";
	public static final String MOD_ID = "stellarity";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static Identifier id(String namespace, String path) {
		return Identifier.fromNamespaceAndPath(namespace, path);
	}

	public static Identifier mcId(String path) {
		return Identifier.withDefaultNamespace(path);
	}

	public static <T extends Registry<U>, U> ResourceKey<U> key(ResourceKey<T> registry, String path) {
		return ResourceKey.create(registry, id(path));
	}

	public static <T extends Registry<U>, U> ResourceKey<U> mcKey(ResourceKey<T> registry, String path) {
		return ResourceKey.create(registry, mcId(path));
	}

	public static boolean hasModonomicon() {
		return FabricLoader.getInstance().isModLoaded("modonomicon");
	}

	public static boolean hasBiolith() {
		return FabricLoader.getInstance().isModLoaded("biolith");
	}

	@Override
	public void onInitialize() {
		StellarityNumberProviders.init();
		StellarityRegistries.init();
		StellarityRegistryEntryModifications.init();
		StellarityFeatures.init();
		StellarityVanillaWorldgenModifications.init();
		StellarityTreeDecorators.init();
		if (hasBiolith()) StellarityBiolithBiomes.init();
		StellarityDataAttachments.init();
		StellarityDataComponents.init();
		StellarityItems.init();
		StellarityTooltips.init();
		StellarityParticleTypes.init();
		StellarityNetworking.init();
		StellarityPotions.init();
		StellarityBlocks.init();
		StellarityCreativeModeTabs.init();
		StellarityEntityTypes.init();
		StellarityBlockEntityTypes.init();
		StellarityLootTableModifications.init();
		StellarityCriteriaTriggers.init();
		StellarityRecipeTypes.init();
		StellarityRecipeSerializers.init();
		StellarityMobEffects.init();
		StellaritySoundEvents.init();
		StellarityVillagerTypes.init();
		StellarityVillagerProfessions.init();
		StellarityCommands.init();
		StellarityPlayerPickItemModifications.init();

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) MixinEnvironment.getCurrentEnvironment().audit();
	}
}