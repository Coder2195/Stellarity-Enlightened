package dev.coder2195.stellarity.registry.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.registry.entity.SatchelSigil;

public class SatchelOfVoids extends Item {
	public SatchelOfVoids(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1
	);


	@Override
	public @NonNull InteractionResult useOn(@NonNull UseOnContext useOnContext) {
		var prev = super.useOn(useOnContext);
		var player = useOnContext.getPlayer();
		var level = useOnContext.getLevel();
		if (player == null || level.isClientSide()) return prev;

		var sigil = new SatchelSigil(level);
		sigil.setPos(Vec3.atBottomCenterOf(useOnContext.getClickedPos().relative(useOnContext.getClickedFace())));
		level.addFreshEntity(sigil);
		player.getCooldowns().addCooldown(useOnContext.getItemInHand(), SatchelSigil.DEFAULT_ACTIVE_TIME + 10 * 20);

		return InteractionResult.SUCCESS;
	}
}
