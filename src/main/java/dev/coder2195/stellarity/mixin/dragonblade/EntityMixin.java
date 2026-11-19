package dev.coder2195.stellarity.mixin.dragonblade;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.registry.StellarityItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public class EntityMixin {
	@Shadow
	private Level level;

	@WrapMethod(method = "remove")
	private void queueDragonbladeForResummon(Entity.RemovalReason reason, Operation<Void> original) {
		// FIXME: in the future, this may dupe dragonblade if mc changes item pickup impl
		if ((Entity) (Object) this instanceof ItemEntity itemEntity && level instanceof ServerLevel serverLevel && itemEntity.getItem().is(StellarityItems.DRAGONBLADE)) {
			serverLevel.globalAttachments().removeAttached(StellarityDataAttachments.DRAGONBLADE_EXISTANCE);
		}
		original.call(reason);
	}
}
