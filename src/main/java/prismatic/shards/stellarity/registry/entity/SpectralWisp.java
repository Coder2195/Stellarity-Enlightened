package prismatic.shards.stellarity.registry.entity;

import com.mojang.serialization.Codec;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.StellarityEntityTypes;

import java.util.List;

public class SpectralWisp extends Projectile {
	private List<LivingEntity> targets;
	private float damage;
	private int mobsRemaining = 3;

	public SpectralWisp(EntityType<? extends Projectile> type, Level level) {
		super(type, level);
	}

	public SpectralWisp(@NonNull Level level, @NonNull LivingEntity shooter, ItemStack itemStack, @NonNull ItemStack weapon) {
		super(StellarityEntityTypes.SPECTRAL_WISP, level);
		this.setPos(shooter.getX(), shooter.getEyeY() - 0.1f, shooter.getZ());
		var power = EnchantmentHelper.getItemEnchantmentLevel(registryAccess().getOrThrow(Enchantments.POWER), weapon);
		damage = 5 + (1.5f * (2 + (power > 0 ? 0.5f : 0) + power * 0.5f));
		setDeltaMovement(shooter.getLookAngle().normalize());
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		super.onHitEntity(hitResult);
		if (!hitResult.getEntity().canBeHitByProjectile()) return;
		mobsRemaining--;


		if (mobsRemaining == 0) this.discard();
	}

	@Override
	protected void onHitBlock(BlockHitResult hitResult) {
		super.onHitBlock(hitResult);
		this.discard();
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		super.readAdditionalSaveData(input);
		input.read("damage", Codec.FLOAT).ifPresent(this::setDamage);
		input.read("mobs_remaining", Codec.INT).ifPresent(this::setMobsRemaining);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		super.addAdditionalSaveData(output);
		output.store("damage", Codec.FLOAT, damage);
		output.store("mobs_remaining", Codec.INT, mobsRemaining);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.@NonNull Builder entityData) {

	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public int getMobsRemaining() {
		return mobsRemaining;
	}

	public void setMobsRemaining(int mobsRemaining) {
		this.mobsRemaining = mobsRemaining;
	}
}
