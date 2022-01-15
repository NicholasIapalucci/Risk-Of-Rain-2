package znick_.riskofrain2.event.handler.character;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.ability.AbilityWrapper;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.huntress.ArrowRainBorder;
import znick_.riskofrain2.block.RiskOfRain2Blocks;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.entity.character.huntress.HuntressRainingArrow;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.util.helper.InventoryHelper;
import znick_.riskofrain2.util.helper.MathHelper;

public class HuntressEventHandler extends EventHandler {
	
	public static int tick = 0;
	public static int utilityCharges = 1;
	public static int utilitiesLeft = 1;
	
	public static final AbilityWrapper wrappedUtility = new AbilityWrapper();
	public static final AbilityWrapper wrappedSpecial = new AbilityWrapper();
	
	//Blink
	public static boolean canBlink = true;
	public static boolean stoppedBlinking = true;
	public static int startedBlinkingTick = 0;
	public static int utilityTick = 0;
	public static int utilityCooldown = Survivor.HUNTRESS.BLINK.getBaseCooldown();
	EntityPlayer playerBlink = null;
	
	//Arrow Rain
	public static boolean canArrowRain = true;
	public static boolean startedArrowRain = false;
	public static boolean startedArrows = false;
	public static boolean shouldPlaySound = false;
	public static boolean canReplaceBlocks = false;
	public static int arrowRainStartTick = 0;
	public static int specialTick = 0;
	public static int specialCooldown = Survivor.HUNTRESS.ARROW_RAIN.getBaseCooldown();
	public static int arrowRainTick = 0;
	public static Position lastPos;
	public static Block lastBlock;
	public static Position arrowRainBlock;
	
	@SubscribeEvent
	public void useAbility(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (Survivor.HUNTRESS.isPlayer(player)) {
			//if (RiskOfRain2KeyBinds.UTILITY.getKeyBinding().isPressed() && canBlink) this.startBlink(player);
			if (RiskOfRain2KeyBinds.SPECIAL.getKeyBinding().isPressed() && !startedArrowRain && canArrowRain) this.startArrowRain(player);
		}
	}
	
//	private void startBlink(EntityPlayer player) {
//		Vec3 lookVec = player.getLookVec();
//		player.addVelocity(10 * lookVec.xCoord, 5 * lookVec.yCoord, 10 * lookVec.zCoord);
//		player.playSound("ror2:huntress_start_blink", 50, 1);
//		
//		utilityTick = tick;
//		startedBlinkingTick = tick;
//		stoppedBlinking = false;
//		playerBlink = player;
//		utilitiesLeft--;
//		if (utilitiesLeft < 1) canBlink = false; //TODO: Fix blinks,, u broke them :(
//		
//		Map<Item, Integer> proccedItems = ProcHelper.getProccedItemsOnUtility(player);
//		utilityCooldown = (int) ProcHelper.getUtilityAfterReduction(PlayableCharacter.HUNTRESS.BLINK.getBaseCooldown(), proccedItems);
//	}
	
	private void startArrowRain(EntityPlayer player) {
		player.playSound("ror2:huntress_arrowrain_start", 50, 1);
		arrowRainStartTick = tick;
		startedArrowRain = true;
		specialTick = tick;
		canArrowRain = false;
		player.addVelocity(0, 5, 0);
	}
	
	@SubscribeEvent
	public void endBlink(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {	
			tick++;	
			
			if (tick > startedBlinkingTick + 7 && !stoppedBlinking) this.endBlink();
			if ((utilityTick + utilityCooldown < tick || utilityTick == 0) && utilitiesLeft < utilityCharges) {
				utilitiesLeft++;
				if (utilitiesLeft + 1 < utilityCharges) {
					utilityTick = tick;
				}
			}
			if (utilitiesLeft > 0) canBlink = true;
		}
	}
	
	private void endBlink() {
		playerBlink.motionX = 0;
		playerBlink.motionY = 0;
		playerBlink.motionZ = 0;
		startedBlinkingTick = 0;
		playerBlink.playSound("ror2:huntress_end_blink", 50, 1);
		stoppedBlinking = true;
	}
	
	private void startRainingArrows(EntityPlayer player, Position pos) {
		arrowRainBlock = new Position(pos);
		startedArrows = true;
		arrowRainTick = tick;
		shouldPlaySound = true;
		player.worldObj.setBlock(pos.getIntX(), pos.getIntY() + 1, pos.getIntZ(), lastBlock);
		//StructureHelper.generateArrowRainCircle(player, pos);
		canReplaceBlocks = true;
	}
	
	@SubscribeEvent
	public void arrowRain(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (tick < arrowRainStartTick + 120 && startedArrowRain) {
				
				if (tick > arrowRainStartTick + 1 && !startedArrows) {
					player.motionX = 0;
					player.motionY = 0;
					player.motionZ = 0;

					if (!player.worldObj.isRemote) {
						for (int i = 1; i < 35; i++) {
							Vec3 scaledVec = MathHelper.multiplyVectorByScalar(player.getLookVec(), i);
							Vec3 translatedVec = Vec3.createVectorHelper(scaledVec.xCoord + player.posX - 0.5, scaledVec.yCoord + player.posY + 1, scaledVec.zCoord + player.posZ - 0.5);
							Position pos = new Position(translatedVec.xCoord, translatedVec.yCoord, translatedVec.zCoord);
							if (pos.getBlock(player.worldObj) != Blocks.air) {
								if (lastPos != null & lastBlock != null) player.worldObj.setBlock(lastPos.getIntX(), lastPos.getIntY() + 1, lastPos.getIntZ(), lastBlock);
								lastPos = new Position(pos);
								lastBlock = player.worldObj.getBlock(pos.getIntX(), pos.getIntY() + 1, pos.getIntZ());
								player.worldObj.setBlock(pos.getIntX(), pos.getIntY() + 1, pos.getIntZ(), RiskOfRain2Blocks.HUNTRESS_ARROW_RAIN_RETICLE);
								if (Minecraft.getMinecraft().gameSettings.keyBindAttack.getIsKeyPressed()) this.startRainingArrows(player, pos);
								break;
							}
						}
					}
				}
				
			} else {
				startedArrowRain = false;
				if (lastPos != null) {
					if (player.worldObj.getBlock(lastPos.getIntX(), lastPos.getIntY() + 1, lastPos.getIntZ()) instanceof ArrowRainBorder) {
						player.worldObj.setBlock(lastPos.getIntX(), lastPos.getIntY() + 1, lastPos.getIntZ(), Blocks.air);
					}
				}
			}

			if (tick < arrowRainTick + 140) {
				if (startedArrows) {
					
					if (!player.worldObj.isRemote) {
						for (int i = 0; i < 3; i++) {
							HuntressRainingArrow arrow = new HuntressRainingArrow(player.worldObj, player, 2);
							Random random = new Random();
					
							arrow.posX = arrowRainBlock.getIntX() + 0.5;
							arrow.posY = arrowRainBlock.getIntY() + 8;
							arrow.posZ = arrowRainBlock.getIntZ() + 0.5;
					
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
				}
			} else {
				startedArrows = false;
			}
			
			if (specialTick + specialCooldown < tick || specialTick == 0) {
				canArrowRain = true;
			}
			
			if (shouldPlaySound && player.worldObj.isRemote) {
				player.worldObj.playSound(arrowRainBlock.getX(), arrowRainBlock.getY(), arrowRainBlock.getZ(), "ror2:huntress_arrowrain_loop", 3, 1, true);
				shouldPlaySound = false;
			}
			
			utilityCharges = 1 + InventoryHelper.amountOfItems(player, RiskOfRain2Items.HARDLIGHT_AFTERBURNER);
			if (utilityTick + utilityCooldown < tick || utilityTick == 0)  utilitiesLeft = utilityCharges;
		}
	}
}
