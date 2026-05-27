package prismatic.shards.stellarity.mixin.bow_use;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import prismatic.shards.stellarity.tags.StellarityItemTags;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
	protected MobMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@WrapMethod(method = "canUseNonMeleeWeapon")
	private boolean addOtherMobsForStellarityBows(ItemStack item, Operation<Boolean> original) {
		if (this instanceof RangedAttackMob) return item.is(StellarityItemTags.BOWS);

		return original.call(item);
	}
}
