package znick_.riskofrain2.api.ror.survivor.huntress;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.ActivatedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.entity.character.huntress.HuntressRainingArrow;
import znick_.riskofrain2.event.TickHandler;
import znick_.riskofrain2.util.helper.MathHelper;

public class ArrowRainAbility extends Ability {

	public ArrowRainAbility() {
		super(Survivor.HUNTRESS, AbilityType.SPECIAL, "Arrow Rain", TickHandler.fromSeconds(12));
	}
	
	private static class ArrowRainPhase1 implements AbilityPhase {

		@Override
		public void activatePhase(EntityPlayer player) {
			player.playSound("ror2:huntress_arrowrain_start", 1, 1);
			player.addVelocity(0, 5, 0);
		}	
	}
	
	private static class ArrowRainPhase2 implements AbilityPhase, DelayedAbilityPhase {

		private Position lastPos;
		private Block lastBlock;

		@Override
		public void activatePhase(EntityPlayer player) {
			player.motionX = 0;
			player.motionY = 0;
			player.motionZ = 0;
			
			//Loop through all blocks along the players look vector
			for (int i = 1; i < 35; i++) {
				Vec3 scaledVec = MathHelper.multiplyVectorByScalar(player.getLookVec(), i);
				Vec3 translatedVec = Vec3.createVectorHelper(scaledVec.xCoord + player.posX - 0.5, scaledVec.yCoord + player.posY + 1, scaledVec.zCoord + player.posZ - 0.5);
				Position pos = new Position(translatedVec.xCoord, translatedVec.yCoord, translatedVec.zCoord);
				//Check if the block exists
				if (pos.getBlock(player.worldObj) != Blocks.air) {
					//TODO: Add block highlighting
					break;
				}
			}
		}

		@Override
		public int getTickDelay() {
			return 2;
		}
	}
	
	private static class ArrowRainPhase3 implements AbilityPhase, ActivatedAbilityPhase<KeyInputEvent> {

		public ArrowRainPhase3() {
			this.register();
		}

		@Override
		public void activatePhase(EntityPlayer player) {
			for (int i = 0; i < 3; i++) {
				HuntressRainingArrow arrow = new HuntressRainingArrow(player.worldObj, player, 2);
				Random random = new Random();
		
				//arrow.posX = arrowRainBlock.getIntX() + 0.5;
				//arrow.posY = arrowRainBlock.getIntY() + 8;
				//arrow.posZ = arrowRainBlock.getIntZ() + 0.5;
		
				arrow.posX += ((random.nextDouble() * 2) - 1) * 3;
				arrow.posY += ((random.nextDouble() * 2) - 1);
				arrow.posZ += ((random.nextDouble() * 2) - 1) * 3;
		
				arrow.motionX = 0;
				arrow.motionY = -2;
				arrow.motionZ = 0;
		
				arrow.rotationPitch = 0;
				player.worldObj.spawnEntityInWorld(arrow);
			}
		}

		@Override
		@SubscribeEvent
		public void listenForActivation(KeyInputEvent event) {

		}
	}

}
