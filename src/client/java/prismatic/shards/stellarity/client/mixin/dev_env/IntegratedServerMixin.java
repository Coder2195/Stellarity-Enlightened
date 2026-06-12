package prismatic.shards.stellarity.client.mixin.dev_env;

import com.moulberry.mixinconstraints.annotations.IfDevEnvironment;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@IfDevEnvironment
@Mixin(IntegratedServer.class)
public class IntegratedServerMixin {
	@Inject(method = "publishServer(Lnet/minecraft/server/MinecraftServer$MultiplayerScope;I)Z", at = @At("HEAD"))
	private void onOpenToLan(MinecraftServer.MultiplayerScope scope, int port, CallbackInfoReturnable<Boolean> cir) {
		IntegratedServer integratedServer = (IntegratedServer) (Object) this;
		integratedServer.setUsesAuthentication(false);
	}
}
