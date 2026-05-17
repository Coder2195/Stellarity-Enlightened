package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;

@Environment(EnvType.CLIENT)
public class VoidedZombieRenderer extends ZombieRenderer {


	public VoidedZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public static final Identifier TEXTURE = Stellarity.id("textures/entity/voided_zombie/voided_zombie.png");

	@Override
	public @NonNull Identifier getTextureLocation(@NonNull ZombieRenderState state) {
		return TEXTURE;
	}
}
