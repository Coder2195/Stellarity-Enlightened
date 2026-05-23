package prismatic.shards.stellarity.registry.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.registry.entity.SatchelSigil;

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
		sigil.setPos(useOnContext.getClickedPos().relative(useOnContext.getClickedFace()).getBottomCenter());
		level.addFreshEntity(sigil);
		player.getCooldowns().addCooldown(useOnContext.getItemInHand(), 70 * 20);

		return InteractionResult.SUCCESS;
	}
}
