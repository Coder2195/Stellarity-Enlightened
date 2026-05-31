package prismatic.shards.stellarity.client.registry.renderer.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.piglin.AdultZombifiedPiglinModel;
import net.minecraft.client.model.monster.piglin.BabyZombifiedPiglinModel;
import net.minecraft.client.model.monster.piglin.ZombifiedPiglinModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombifiedPiglinRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import prismatic.shards.stellarity.Stellarity;
import prismatic.shards.stellarity.registry.entity.FleshPiglin;

public class FleshPiglinRenderer extends HumanoidMobRenderer<FleshPiglin, ZombifiedPiglinRenderState, ZombifiedPiglinModel> {
	private static final Identifier FLESH_PIGLIN_LOCATION = Stellarity.id("textures/entity/flesh_piglin/flesh_piglin.png");
	private static final Identifier BABY_FLESH_PIGLIN_LOCATION = Stellarity.id("textures/entity/flesh_piglin/baby_flesh_piglin.png");

	public FleshPiglinRenderer(final EntityRendererProvider.Context context) {
		super(
			context,
			new AdultZombifiedPiglinModel(context.bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)),
			new BabyZombifiedPiglinModel(context.bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN_BABY)),
			0.5F,
			PiglinRenderer.PIGLIN_CUSTOM_HEAD_TRANSFORMS
		);
		this.addLayer(
			new HumanoidArmorLayer<>(
				this,
				ArmorModelSet.bake(ModelLayers.ZOMBIFIED_PIGLIN_ARMOR, context.getModelSet(), AdultZombifiedPiglinModel::new),
				ArmorModelSet.bake(ModelLayers.ZOMBIFIED_PIGLIN_BABY_ARMOR, context.getModelSet(), BabyZombifiedPiglinModel::new),
				context.getEquipmentRenderer()
			)
		);
	}


	@Override
	public @NonNull Identifier getTextureLocation(ZombifiedPiglinRenderState state) {
		return state.isBaby ? BABY_FLESH_PIGLIN_LOCATION : FLESH_PIGLIN_LOCATION;
	}

	@Override
	public @NonNull ZombifiedPiglinRenderState createRenderState() {
		return new ZombifiedPiglinRenderState();
	}
}
