package znick_.riskofrain2.api.ror.survivor.huntress.ability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.event.handler.TickHandler;

public class BlinkAbility extends Ability {

	public BlinkAbility() {
		super(Survivor.HUNTRESS, AbilityType.UTILITY, "blink", TickHandler.fromSeconds(7));
		this.addPhase(new BlinkAbilityPhase1());
		this.addPhase(new BlinkAbilityPhase2());
	}
	
	private static class BlinkAbilityPhase1 implements AbilityPhase {

		@Override
		public void activatePhase(EntityPlayer player) {
			if (RiskOfRain2.DEBUG) System.out.println("Player is beginning to blink...");
			Vec3 lookVec = player.getLookVec();
			player.addVelocity(10 * lookVec.xCoord, 5 * lookVec.yCoord, 10 * lookVec.zCoord);
			player.playSound("ror2:huntress_start_blink", 50, 1);
		}
		
	}
	
	private static class BlinkAbilityPhase2 implements AbilityPhase, DelayedAbilityPhase {

		@Override
		public void activatePhase(EntityPlayer player) {
			if (RiskOfRain2.DEBUG) System.out.println("Ending player blink");
			player.motionX = 0;
			player.motionY = 0;
			player.motionZ = 0;
			player.playSound("ror2:huntress_end_blink", 1, 1);
		}

		@Override
		public int getTickDelay() {
			return 7;
		}
		
	}

}
