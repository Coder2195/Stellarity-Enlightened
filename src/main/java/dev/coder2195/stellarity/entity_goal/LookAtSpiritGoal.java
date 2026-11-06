package dev.coder2195.stellarity.entity_goal;

import dev.coder2195.stellarity.entity.Spirit;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

public class LookAtSpiritGoal extends Goal {
	private final Mob mob;
	private final Level level;
	private @Nullable Spirit spirit;
	private final RandomSource random;


	public LookAtSpiritGoal(final Mob mob) {
		this.mob = mob;
		this.level = mob.level();
		this.random = mob.getRandom();
		this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		if (!mob.getMainHandItem().is(StellarityItemTags.SPIRIT_DAGGERS) || !mob.getOffhandItem().is(StellarityItemTags.SPIRIT_DAGGERS)) return false;

		if (spirit != null && spirit.isOwnerCanSee(mob)) return true;

		spirit = null;

		for (var spiritCandidate : level.getEntitiesOfClass(
			Spirit.class, mob.getBoundingBox().inflate(50, 20, 50),
			e -> e.isAlive() && e.isOwnerCanSee(mob)
		)) {
			spirit = spiritCandidate;

			return true;
		}

		return false;
	}

	@Override
	public void tick() {
		if (spirit == null) return;
		mob.getLookControl().setLookAt(spirit.getEyePosition());
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public boolean canContinueToUse() {
		if (spirit != null && spirit.isAlive() && spirit.isOwnerCanSee(mob)) return true;
		spirit = null;
		return false;
	}
}
