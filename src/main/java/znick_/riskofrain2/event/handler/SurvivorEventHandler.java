package znick_.riskofrain2.event.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import znick_.riskofrain2.net.AbilityPacketHandler;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public class SurvivorEventHandler extends EventHandler {

	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		IMessage packet = new AbilityPacketHandler.AbilityPacket();
		RiskOfRain2Packets.NET.sendToServer(packet);
	}
	
//	@SubscribeEvent
//	public void arrowRain(LivingUpdateEvent event) {
//		if (event.entityLiving instanceof EntityPlayer) {
//			EntityPlayer player = (EntityPlayer) event.entityLiving;
//			if (tick < arrowRainStartTick + 120 && startedArrowRain) {
//				
//				if (true) {
//				startedArrowRain = false;
//				if (lastPos != null) {
//					if (player.worldObj.getBlock(lastPos.getIntX(), lastPos.getIntY() + 1, lastPos.getIntZ()) instanceof ArrowRainBorder) {
//						player.worldObj.setBlock(lastPos.getIntX(), lastPos.getIntY() + 1, lastPos.getIntZ(), Blocks.air);
//					}
//				}
//			}
//
//			if (tick < arrowRainTick + 140) {
//				if (startedArrows) {
//					
//					if (!player.worldObj.isRemote) {
//						for (int i = 0; i < 3; i++) {
//							HuntressRainingArrow arrow = new HuntressRainingArrow(player.worldObj, player, 2);
//							Random random = new Random();
//					
//							arrow.posX = arrowRainBlock.getIntX() + 0.5;
//							arrow.posY = arrowRainBlock.getIntY() + 8;
//							arrow.posZ = arrowRainBlock.getIntZ() + 0.5;
//					
//							arrow.posX += ((random.nextDouble() * 2) - 1) * 3;
//							arrow.posY += ((random.nextDouble() * 2) - 1);
//							arrow.posZ += ((random.nextDouble() * 2) - 1) * 3;
//					
//							arrow.motionX = 0;
//							arrow.motionY = -2;
//							arrow.motionZ = 0;
//					
//							arrow.rotationPitch = 0;
//							player.worldObj.spawnEntityInWorld(arrow);
//						}
//					}
//				}
//			} else {
//				startedArrows = false;
//			}
//			
//			if (specialTick + specialCooldown < tick || specialTick == 0) {
//				canArrowRain = true;
//			}
//			
//			if (shouldPlaySound && player.worldObj.isRemote) {
//				player.worldObj.playSound(arrowRainBlock.getX(), arrowRainBlock.getY(), arrowRainBlock.getZ(), "ror2:huntress_arrowrain_loop", 3, 1, true);
//				shouldPlaySound = false;
//			}
//			
//			utilityCharges = 1 + InventoryHelper.amountOfItems(player, RiskOfRain2Items.HARDLIGHT_AFTERBURNER);
//			if (utilityTick + utilityCooldown < tick || utilityTick == 0)  utilitiesLeft = utilityCharges;
//		}
//	}
}
