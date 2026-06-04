package prismatic.shards.stellarity.registry.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatternLayers.Layer;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import prismatic.shards.stellarity.key.StellarityDamageTypes;
import prismatic.shards.stellarity.networking.ClientboundElectricDashPayload;
import prismatic.shards.stellarity.registry.StellarityDataComponents;
import prismatic.shards.stellarity.registry.StellarityItems;
import prismatic.shards.stellarity.util.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CopperElektraShield extends ShieldItem {

	public static final int DASH_CHARGE_TIME = 7 * 20;

	public CopperElektraShield(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1).durability(336).rarity(Rarity.UNCOMMON)
		.delayedComponent(DataComponents.BANNER_PATTERNS, context -> new BannerPatternLayers(
			Stream.of(
				new Tuple2<>(BannerPatterns.BASE, DyeColor.ORANGE),
				new Tuple2<>(BannerPatterns.CROSS, DyeColor.YELLOW),
				new Tuple2<>(BannerPatterns.GRADIENT, DyeColor.ORANGE),
				new Tuple2<>(BannerPatterns.GRADIENT_UP, DyeColor.ORANGE),
				new Tuple2<>(BannerPatterns.BORDER, DyeColor.YELLOW),
				new Tuple2<>(BannerPatterns.BORDER, DyeColor.ORANGE),
				new Tuple2<>(BannerPatterns.FLOWER, DyeColor.YELLOW),
				new Tuple2<>(BannerPatterns.FLOWER, DyeColor.ORANGE),
				new Tuple2<>(BannerPatterns.CURLY_BORDER, DyeColor.YELLOW),
				new Tuple2<>(BannerPatterns.CURLY_BORDER, DyeColor.ORANGE)
			).map(tuple -> new Layer(context.getOrThrow(tuple._1()), tuple._2())).toList()
		))
		.repairable(ItemTags.WOODEN_TOOL_MATERIALS)
		.equippableUnswappable(EquipmentSlot.OFFHAND)
		.delayedComponent(
			DataComponents.BLOCKS_ATTACKS,
			/* lambda$static$150 */ context -> new BlocksAttacks(
				0.25F,
				1.0F,
				List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
				new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
				Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
				Optional.of(SoundEvents.SHIELD_BLOCK),
				Optional.of(SoundEvents.SHIELD_BREAK)
			)
		)
		.component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK);

	@Override
	public void inventoryTick(@NonNull ItemStack itemStack, @NonNull ServerLevel level, @NonNull Entity owner, @Nullable EquipmentSlot slot) {
		super.inventoryTick(itemStack, level, owner, slot);

		if (!(owner instanceof Player player && player.isShiftKeyDown() && player.isUsingItem() && player.getUseItem().is(StellarityItems.COPPER_ELEKTRA_SHIELD)))
			return;


		var time = level.getGameTime();
		var rechargesAt = itemStack.get(StellarityDataComponents.RECHARGES_AT);
		if (rechargesAt == null) rechargesAt = time;
		else if (rechargesAt - 2 * DASH_CHARGE_TIME > time) return;
		rechargesAt = Math.max(rechargesAt, time - 2 * DASH_CHARGE_TIME);

		var ownerPosition = player.getEyePosition();
		var proposedPosition = ownerPosition.add(player.getLookAngle().normalize().scale(7));
		var raycast = level.clip(new ClipContext(ownerPosition, proposedPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
		var endLocation = raycast.getLocation();
		var entitiesHit = ProjectileUtil.getManyEntityHitResult(level, player, ownerPosition, endLocation, new AABB(ownerPosition, endLocation).inflate(2), (entity) -> entity instanceof LivingEntity target && player.canAttack(target), false);

		var electric = level.damageSources().source(StellarityDamageTypes.ELECTRIC, owner, owner);
		List<Vec3> creeperLocations = new ArrayList<>();
		for (var hitEntity : entitiesHit) {
			var hit = hitEntity.getEntity();
			hit.hurtServer(level, electric, 4);
			if (hit instanceof Creeper creeper && creeper.getRandom().nextFloat() < 0.25F) {
				creeper.getEntityData().set(Creeper.DATA_IS_POWERED, true);
				creeperLocations.add(creeper.position());
			}
		}


		var simulationDistance = (level.getServer().getPlayerList().getSimulationDistance() + 1) * 16;
		for (var networkPlayer : level.getPlayers(networkPlayer -> networkPlayer.position().distanceTo(endLocation) < simulationDistance)) {
			ServerPlayNetworking.send(networkPlayer, new ClientboundElectricDashPayload(ownerPosition, endLocation, creeperLocations));
		}

		owner.teleport(new TeleportTransition(level, endLocation, player.getDeltaMovement(), player.getYRot(), player.getXRot(), TeleportTransition.DO_NOTHING));

		itemStack.set(StellarityDataComponents.RECHARGES_AT, rechargesAt + DASH_CHARGE_TIME);


	}
}
