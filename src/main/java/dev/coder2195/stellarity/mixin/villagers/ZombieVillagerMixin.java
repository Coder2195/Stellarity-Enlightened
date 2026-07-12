package dev.coder2195.stellarity.mixin.villagers;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import dev.coder2195.stellarity.registry.StellarityVillagerProfessions;
import dev.coder2195.stellarity.tags.StellarityVillagerProfessionTags;

import java.util.function.Supplier;

@Mixin(ZombieVillager.class)
public abstract class ZombieVillagerMixin extends Zombie {
	public ZombieVillagerMixin(EntityType<? extends Zombie> type, Level level) {
		super(type, level);
	}

	@ModifyArg(method = "readAdditionalSaveData", at = @At(value = "INVOKE", target = "Ljava/util/Optional;orElseGet(Ljava/util/function/Supplier;)Ljava/lang/Object;"), index = 0)
	private Supplier<VillagerData> preventStellarityVillager(Supplier<VillagerData> original) {

		return () -> preventStellarityVillager(original.get());
	}


	@Unique
	@WrapOperation(method = "defineSynchedData", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/ZombieVillager;initializeZombieVillagerData(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/entity/npc/villager/VillagerData;"))
	private VillagerData preventStellarityVillager(RandomSource random, Operation<VillagerData> original) {
		return preventStellarityVillager(original.call(random));
	}

	@Unique
	private VillagerData preventStellarityVillager(VillagerData original) {
		if (level().dimensionTypeRegistration().is(BuiltinDimensionTypes.END)) {
			if (!(original.profession() instanceof Holder.Reference<VillagerProfession> professionReference)) return original;
			return original.withProfession(StellarityVillagerProfessions.mapVanilla(professionReference));
		}


		var old = original;
		for (int i = 0; i < 50; i++) {
			if (!old.profession().is(StellarityVillagerProfessionTags.END_PROFESSION)) return old;
			var profession = BuiltInRegistries.VILLAGER_PROFESSION.getRandom(random);
			if (profession.isEmpty()) return old;
			old = old.withProfession(profession.get());
		}

		return Villager.createDefaultVillagerData();
	}
}
