package dev.coder2195.stellarity.item;

import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityEntityTypes;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import dev.coder2195.stellarity.util.RaycastUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class BookOfLight extends Item {
	public static final Properties PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON);
	private static final int RECHARGE_TIME = 48 * 20;

	public BookOfLight(Properties properties) {
		super(properties);
	}

	@Override
	public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
		var itemStack = player.getItemInHand(hand);

		player.getCooldowns().addCooldown(itemStack, RECHARGE_TIME);

		if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;

		var eyePos = player.getEyePosition();
		var targetPosition = eyePos.add(player.getHeadLookAngle().normalize().scale(player.getAttributes().getValue(Attributes.BLOCK_INTERACTION_RANGE)));
		var raycast = RaycastUtil.raycastLine(level, eyePos, targetPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, Entity::isPickable);
		if (!raycast.getType().equals(HitResult.Type.MISS)) targetPosition = raycast.getLocation();

		serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(), StellaritySoundEvents.SPELLBOOK_CAST, SoundSource.PLAYERS, 1, 0.5f);

		var aura = StellarityEntityTypes.LIGHT_AURA.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
		if (aura == null) {
			Stellarity.LOGGER.error("Failed to create Light aura");
			return InteractionResult.FAIL;
		}
		aura.setPos(targetPosition);
		level.addFreshEntity(aura);

		return InteractionResult.SUCCESS_SERVER;
	}
}
