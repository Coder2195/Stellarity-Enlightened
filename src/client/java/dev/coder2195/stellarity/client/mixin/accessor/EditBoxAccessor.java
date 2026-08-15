package dev.coder2195.stellarity.client.mixin.accessor;

import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EditBox.class)
public interface EditBoxAccessor {
	@Mutable
	@Accessor("value")
	void stellarity$setValue(String s);
}
