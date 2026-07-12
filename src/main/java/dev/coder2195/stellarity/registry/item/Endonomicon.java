package dev.coder2195.stellarity.registry.item;

import com.klikli_dev.modonomicon.client.gui.BookGuiManager;
import com.klikli_dev.modonomicon.client.gui.book.BookAddress;
import com.klikli_dev.modonomicon.data.BookDataManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import dev.coder2195.stellarity.Stellarity;


public class Endonomicon extends Item {
	public Endonomicon(Properties properties) {
		super(properties);
	}

	public static final Properties PROPERTIES = new Properties().stacksTo(1);


	@Override
	public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand interactionHand) {
		var result = super.use(level, player, interactionHand);

		if (!Stellarity.hasModonomicon()) {
			player.sendOverlayMessage(Component.translatable("message.stellarity.missing_modonomicon"));
			return result;
		}

		if (level.isClientSide()) {
			var book = BookDataManager.get().getBook(Stellarity.id("endonomicon"));
			BookGuiManager.get().openBook(BookAddress.defaultFor(book));
		}
		
		return InteractionResult.SUCCESS;
	}
}
