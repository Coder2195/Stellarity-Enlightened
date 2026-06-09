package prismatic.shards.stellarity.mixin.villagers;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.behavior.AssignProfessionFromJobSite;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import prismatic.shards.stellarity.registry.StellarityVillagerProfessions;
import prismatic.shards.stellarity.registry.StellarityVillagerTypes;

@Mixin(AssignProfessionFromJobSite.class)
public class AssignProfessionFromJobSiteMixin {
	@Definition(id = "profession", local = @Local(type = Holder.Reference.class, name = "profession", argsOnly = true))
	@Expression("profession")
	@ModifyExpressionValue(method = "lambda$create$6", at = @At("MIXINEXTRAS:EXPRESSION"))
	private static Holder.Reference<VillagerProfession> mapToStellarity(Holder.Reference<VillagerProfession> original, @Local(name = "body", argsOnly = true) Villager body) {
		if (body.getVillagerData().type().is(StellarityVillagerTypes.END.key()))
			return StellarityVillagerProfessions.mapVanilla(original);
		return original;
	}
}
