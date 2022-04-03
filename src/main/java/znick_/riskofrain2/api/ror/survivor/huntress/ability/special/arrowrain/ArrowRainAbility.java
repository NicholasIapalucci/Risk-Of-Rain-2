package znick_.riskofrain2.api.ror.survivor.huntress.ability.special.arrowrain;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.api.ror.survivor.SurvivorEventHandler;
import znick_.riskofrain2.api.ror.survivor.ability.Ability;
import znick_.riskofrain2.api.ror.survivor.ability.AbilityType;
import znick_.riskofrain2.api.ror.survivor.ability.phase.AbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.ActivatedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.DelayedAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.ability.phase.RepeatingAbilityPhase;
import znick_.riskofrain2.api.ror.survivor.huntress.ability.utility.BlinkAbility;
import znick_.riskofrain2.client.render.RenderHelper;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.net.RiskOfRain2Packets;
import znick_.riskofrain2.util.helper.MathHelper;

public class ArrowRainAbility extends Ability<ArrowRainAbility> {

	public static final ArrowRainAbility MAIN_INSTANCE = new ArrowRainAbility(true);
	
	private final ArrowRainPhase1 phase1;
	private final ArrowRainPhase2 phase2;
	private final ArrowRainPhase3 phase3;
	private EntityPlayer player;
	
	public ArrowRainAbility() {
		this(false);
	}
	
	private ArrowRainAbility(boolean isMainInstance) {
		super(Survivor.HUNTRESS, AbilityType.SPECIAL, "arrow_rain", TickHandler.fromSeconds(12), isMainInstance);
		
		this.phase1 = this.new ArrowRainPhase1();
		this.phase2 = this.new ArrowRainPhase2();
		this.phase3 = this.new ArrowRainPhase3();
		
		this.addPhase(phase1);
		this.addPhase(phase2);
		this.addPhase(phase3);
	}
	
	@Override
	public ArrowRainAbility newInstance() {
		return new ArrowRainAbility();
	}
	
	private class ArrowRainPhase1 implements AbilityPhase {

		private ArrowRainPhase1() {
			
		}
		
		@Override
		public void activatePhase(EntityPlayer player) {
			player.playSound("ror2:huntress_arrowrain_start", 1, 1);
			player.addVelocity(0, 5, 0);
		}	
	}
	
	private class ArrowRainPhase2 implements AbilityPhase, DelayedAbilityPhase, RepeatingAbilityPhase {

		private ArrowRainPhase2() {
			
		}
		
		private Position blockToRainOn;
		private boolean isActive;
		
		@Override
		public void activatePhase(EntityPlayer player) {
			this.isActive = true;
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
					RenderHelper.renderCube(pos.getIntX(), pos.getIntY(), pos.getIntZ(), 1, new Color(0, 255, 255));
					this.blockToRainOn = pos;
					break;
				}
			}
		}

		@Override
		public int getTickDelay() {
			return 2;
		}
		
		private void deactivatePhase() {
			this.isActive = false;
			SurvivorEventHandler.deactivateRepeatingAbility();
		}

		@Override
		public boolean isActive() {
			return this.isActive;
		}
		
		@Override
		public void activateFirst(EntityPlayer player) {
			player.playSound("ror2:huntress_arrowrain_loop", 1, 1);
		}

		@Override
		public int getDuration() {
			return TickHandler.fromSeconds(5);
		}
	}
	
	public class ArrowRainPhase3 implements AbilityPhase, ActivatedAbilityPhase<TickEvent.ClientTickEvent>, RepeatingAbilityPhase {
		
		public Position arrowRainBlock;
		private boolean isActive;
		
		public ArrowRainPhase3() {
			this.register();
		}

		@Override
		public void activatePhase(EntityPlayer player) {
			
			// If we're on the client, send a packet to the server.
			if (player.worldObj.isRemote) {
				IMessage packet = new ArrowRainPacketHandler.ArrowRainPacket(
					ArrowRainAbility.this.phase2.blockToRainOn.getIntX(), 
					ArrowRainAbility.this.phase2.blockToRainOn.getIntY(), 
					ArrowRainAbility.this.phase2.blockToRainOn.getIntZ()
				);
				RiskOfRain2Packets.NET.sendToServer(packet);
				return;
			}
			
			// If we're on the server, do the stuff
			for (int i = 0; i < 3; i++) {
				HuntressRainingArrow arrow = new HuntressRainingArrow(player.worldObj, player);
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

		@Override
		@SubscribeEvent
		public void listenForActivation(TickEvent.ClientTickEvent event) {
			
			// Activate if the phase 2 is active and left click is pressed
			if (Mouse.isButtonDown(0) && ArrowRainAbility.this.phase2.isActive()) {
				ArrowRainAbility.this.phase2.deactivatePhase();
				this.activatePhase(Minecraft.getMinecraft().thePlayer);
			}
		}

		@Override
		public boolean isActive() {
			return this.isActive;
		}
		
		@Override
		public void activateFirst(EntityPlayer player) {
			player.playSound("ror2:huntress_arrowrain_loop", 1, 1);
		}

		@Override
		public int getDuration() {
			return TickHandler.fromSeconds(5);
		}
	}

}
