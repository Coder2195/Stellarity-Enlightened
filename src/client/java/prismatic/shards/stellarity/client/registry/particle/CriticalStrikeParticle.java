package prismatic.shards.stellarity.client.registry.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NonNull;


@Environment(EnvType.CLIENT)
public class CriticalStrikeParticle extends SingleQuadParticle {
	private float scale = 1f;

	public CriticalStrikeParticle(ClientLevel clientLevel, double d, double e, double f, TextureAtlasSprite textureAtlasSprite) {
		super(clientLevel, d, e, f, textureAtlasSprite);

		this.x = d;
		this.y = e;
		this.z = f;

		setLifetime(20);

		scale(1f);
	}

	@Override
	public void tick() {
		scale -= 0.05f;
		scale(scale);

		super.tick();

	}


	@Override
	public @NonNull Layer getLayer() {
		return Layer.OPAQUE;
	}

	@Override
	protected int getLightCoords(float a) {
		return super.getLightCoords(a);
	}


	@Environment(EnvType.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {


		private final SpriteSet sprite;

		public Provider(SpriteSet spriteSet) {
			this.sprite = spriteSet;
		}


		@Override
		public Particle createParticle(SimpleParticleType simpleParticleType, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, @NonNull RandomSource randomSource) {
			return new CriticalStrikeParticle(clientLevel, d, e, f, this.sprite.get(randomSource));
		}
	}
}