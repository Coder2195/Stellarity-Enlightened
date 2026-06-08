package prismatic.shards.stellarity.registry.item;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.AdvancementCommands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.StellarityItems;

import java.util.List;
import java.util.Objects;

public class CrystalHeartfish extends Item {
	public CrystalHeartfish(Properties properties) {
		super(properties);
	}


	public static final Properties PROPERTIES = StellarityItems.foodProperties(new Properties(), new FoodProperties.Builder(),

		Consumables.defaultFood().consumeSeconds(5f),
		0, 0.0f, true
	);

	@Override
	public @NonNull ItemStack finishUsingItem(@NonNull ItemStack itemStack, Level level, @NonNull LivingEntity livingEntity) {
		if (!level.isClientSide()) {
			this.addHealth(livingEntity);
		}
		return super.finishUsingItem(itemStack, level, livingEntity);

	}

	public void addHealth(LivingEntity entity) {
		AttributeInstance maxHPAttribute = entity.getAttributes().getInstance(Attributes.MAX_HEALTH);
		if (maxHPAttribute == null) return;

		AttributeModifier oldModifier = maxHPAttribute.getModifier(Stellarity.id("crystal_heartfish_health_bonus"));

		double amount = oldModifier == null ? 1 : oldModifier.amount() + 1;

		if (amount >= 10) {
			if (entity instanceof ServerPlayer player) {
				MinecraftServer server = Objects.requireNonNull(player.level().getServer());
				var location = Stellarity.id("void_fishing/topped_off");
				AdvancementHolder advancement = Objects.requireNonNull(server.getAdvancements().get(location));
				AdvancementCommands.Action.GRANT.perform(player, List.of(advancement), true);
			}
		}

		if (amount > 10) return;

		AttributeModifier newModifier = new AttributeModifier(
			Stellarity.id("crystal_heartfish_health_bonus"),
			amount,
			AttributeModifier.Operation.ADD_VALUE
		);

		if (oldModifier != null) {
			maxHPAttribute.removeModifier(oldModifier);
		}
		maxHPAttribute.addPermanentModifier(newModifier);
	}


}
