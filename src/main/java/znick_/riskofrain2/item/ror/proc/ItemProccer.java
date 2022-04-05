package znick_.riskofrain2.item.ror.proc;

import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.buff.stat.EntityStat;
import znick_.riskofrain2.event.handler.EventHandler;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.survivorsofthevoid.VoidItem;
import znick_.riskofrain2.item.ror.proc.type.onDeathListener;
import znick_.riskofrain2.item.ror.proc.type.OnHealItem;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.type.OnJumpItem;
import znick_.riskofrain2.item.ror.proc.type.OnKeyPressItem;
import znick_.riskofrain2.item.ror.proc.type.OnKillItem;
import znick_.riskofrain2.item.ror.proc.type.OnObjectInteractionItem;
import znick_.riskofrain2.item.ror.proc.type.OnPickupXPItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.util.misc.UniqueDamageSource;

public class ItemProccer extends EventHandler {
	
	private static int updateCounter = 0;
	
	/**
	 * Called when the player attacks an enemy. Procs all on-hit items if they should.
	 * 
	 * @param event {@code LivingAttackEvent}
	 */
	@SubscribeEvent
	public void procOnHitItems(LivingAttackEvent event) {
		if (event.source.getEntity() instanceof EntityPlayer && 
		    !(event.source instanceof UniqueDamageSource) && 
		    event.source.getEntity() != null) {
			
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			AbstractEntityData data = AbstractEntityData.get(player);
			Map<OnHitItem, Integer> itemMap = data.getRiskOfRain2Items(OnHitItem.class);
			double originalDamageMultiplier = data.getStat(EntityStat.DAMAGE_MULTIPLIER);
			
			//Loops through all RoR2 Items
			for (Map.Entry<OnHitItem, Integer> itemEntry : itemMap.entrySet()) {
				if (itemEntry.getKey().shouldProcOnHit(event, data, event.entityLiving, itemEntry.getValue())) {
					itemEntry.getKey().procOnHit(event, data, event.entityLiving, itemEntry.getValue());
				}
			}
			
			//Check for & handle critical strike
			boolean crit = data.rollStat(EntityStat.CRIT_CHANCE);
			
			if (crit && !player.worldObj.isRemote) {
				if (RiskOfRain2Mod.DEBUG) System.out.println("Player procced critical strike with chance " + data.getStat(EntityStat.CRIT_CHANCE) * 100 + "%");
				data.addToStat(EntityStat.DAMAGE_MULTIPLIER, 1);
				data.playSound("ror2:crit_glasses");
				
				// Proc Harvester's Scythe if the player has it
				int scytheAmount = data.itemCount(RiskOfRain2Items.HARVESTERS_SCYTHE);
				if (scytheAmount > 0) {
					((OnHitItem) RiskOfRain2Items.HARVESTERS_SCYTHE).procOnHit(event, data, event.entityLiving, scytheAmount);
				}
			} 
			
			else if (!player.worldObj.isRemote) {
				if (RiskOfRain2Mod.DEBUG) System.out.println("Failed to proc critical strike with chance " + data.getStat(EntityStat.CRIT_CHANCE) * 100 + "%");
			}
			
			/*
			 * The damage in LivingAttackEvent cannot be changed, so the event must be canceled
			 * and a new event must be fired with the new damage value. A UniqueDamageSource must
			 * be used to prevent a stack overflow of infinite recursion. I could change the final
			 * value with reflection, but I'm unsure about whether it would actually affect the
			 * damage or how it would behave, so this is safest.
			 */
			if (data.getStat(EntityStat.DAMAGE_MULTIPLIER) != 1) {
				event.setCanceled(true);
				event.entity.attackEntityFrom(new UniqueDamageSource(player), (float) (event.ammount * data.getStat(EntityStat.DAMAGE_MULTIPLIER)));
			}
			
			// Reset to original damage multiplier
			data.setStat(EntityStat.DAMAGE_MULTIPLIER, originalDamageMultiplier);
		}
	}
	
	/**
	 * Called when the player kills an entity. Procs any Risk of Rain 2 items that should proc.
	 * 
	 * @param event The {@code LivingDeathEvent event}
	 */
	@SubscribeEvent
	public void procOnKillItems(LivingDeathEvent event) {
		if (event.source.getEntity() instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) event.source.getEntity();
			AbstractEntityData data = AbstractEntityData.get(entity);
			Map<OnKillItem, Integer> itemMap = data.getRiskOfRain2Items(OnKillItem.class);
			
			// Loop through all Risk Of Rain 2 Items
			for (Map.Entry<OnKillItem, Integer> itemEntry : itemMap.entrySet()) {
				itemEntry.getKey().procOnKill(event, data, event.entityLiving, itemEntry.getValue());
			}
		}
	}
	
	/**
	 * Called every time the player is updated. Procs any items the player has that
	 * should be procced on update if the roll suceeds.
	 * 
	 * @param event The {@code LivingUpdateEvent} to use.
	 */
	@SubscribeEvent
	public void procOnUpdateItems(LivingUpdateEvent event) {
		EntityLivingBase entity = event.entityLiving;
		if (entity.worldObj.isRemote) return;
		AbstractEntityData data = AbstractEntityData.get(entity);
		Map<OnUpdateItem, Integer> itemMap = data.getRiskOfRain2Items(OnUpdateItem.class);
		
		data.updateBuffs();
		if (data.isPlayer()) ((PlayerData) data).updateItems();
		
		// Loop through all on-update items the player has
		for (Map.Entry<OnUpdateItem, Integer> itemEntry : itemMap.entrySet()) {
			if (itemEntry.getKey().shouldProcOnUpdate(event, data, itemEntry.getValue())) {
				itemEntry.getKey().procOnUpdate(event, data, itemEntry.getValue());
			}
						
			// Check if the item is a void item
			if (itemEntry.getKey() instanceof VoidItem) {
				VoidItem voidItem = (VoidItem) itemEntry.getKey();
				// If it is, corrupt all items that it should corrupt
				for (RiskOfRain2Item toCorrupt : voidItem.getCorruptedItems()) {
					data.replaceAllItems(toCorrupt, voidItem);
				}
			}
		}
			
		// Add the player's movement speed multiplier
		if (entity instanceof EntityPlayer) ((EntityPlayer) entity).capabilities.setPlayerWalkSpeed((float) (0.1 * data.getStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER)));
		else {
			((EntityData) data).setBaseMovementSpeed(entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue());
			entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(data.getStat(EntityStat.MOVEMENT_SPEED_MULTIPLIER) * ((EntityData) data).getBaseMovementSpeed());
		}
		updateCounter++;
	}
	
	public static int getUpdateCounter() {
		return updateCounter;
	}
	
	/**
	 * Called when an entity is healed. Procs all on-heal items if they should proc.
	 * 
	 * @param event The {@code LivingHealEvent} 
	 */
	@SubscribeEvent
	public void procOnHealItems(LivingHealEvent event) {
		EntityLivingBase entity = (EntityLivingBase) event.entityLiving;
		AbstractEntityData data = AbstractEntityData.get(entity);
		
		if (!data.canHeal()) {
			event.setCanceled(true);
			return;
		}
		
		Map<OnHealItem, Integer> itemMap = data.getRiskOfRain2Items(OnHealItem.class);
		
		for (Map.Entry<OnHealItem, Integer> itemEntry : itemMap.entrySet()) {
			itemEntry.getKey().procOnHeal(event, data, itemEntry.getValue());
		}
	}
	
	/**
	 * Called when the player jumps. Procs all on-jump items that should proc.
	 * 
	 * @param event The {@code LivingJumpEvent}.
	 */
	@SubscribeEvent
	public void procOnJumpItems(LivingJumpEvent event) {
		EntityLivingBase entity = (EntityLivingBase) event.entityLiving;
		AbstractEntityData data = AbstractEntityData.get(entity);	
		Map<OnJumpItem, Integer> itemMap = data.getRiskOfRain2Items(OnJumpItem.class);
		
		for (Map.Entry<OnJumpItem, Integer> itemEntry : itemMap.entrySet()) {
			if (itemEntry.getKey().shouldProcOnJump(event, data, itemEntry.getValue())) {
				itemEntry.getKey().procOnJump(event, data, itemEntry.getValue());
			}
		}
	}
	
	/**
	 * Called when an entity is hurt. If the entity has any Risk of Rain 2 items that should proc when
	 * the entity is hurt, they will proc.
	 * 
	 * @param event The {@code LivingHurtEvent}.
	 */
	@SubscribeEvent
	public void procOnHurtItems(LivingHurtEvent event) {
		AbstractEntityData data = AbstractEntityData.get(event.entityLiving instanceof EntityPlayer? (EntityPlayer) event.entityLiving : event.entityLiving);
		Map<OnHurtItem, Integer> itemMap = data.getRiskOfRain2Items(OnHurtItem.class);	
		
		// Proc all on-hurt items if able to
		for (Map.Entry<OnHurtItem, Integer> itemEntry : itemMap.entrySet()) {
			
			// Stop checking items if the player didn't actually get hurt
			if (event.isCanceled()) break;
			
			// Rolls if the event should proc and if so, procs it
			if (itemEntry.getKey().shouldProcOnHurt(event, data, itemEntry.getValue())) {
				itemEntry.getKey().procOnHurt(event, data, itemEntry.getValue());
			}
		}
			
		// Factor in armor
		double armor = data.getStat(EntityStat.ARMOR);
		event.ammount *= (1 - (armor/(100 + Math.abs(armor))));
	}
	
	/**
	 * Called when the player interacts with a Risk of Rain2 interactible block such as a
	 * chest or 3D printer. Fires any items that should fire upon such an event.
	 * 
	 * @param event The {@code ObjectInteractionEvent}.
	 */
	@SubscribeEvent
	public void procOnInteractionItems(ObjectInteractionEvent event) {
		EntityPlayer player = event.entityPlayer;
		PlayerData data = AbstractEntityData.get(player);
		Map<OnObjectInteractionItem, Integer> itemMap = data.getRiskOfRain2Items(OnObjectInteractionItem.class);	
		
		// Proc all on-interact items if able to
		for (Map.Entry<OnObjectInteractionItem, Integer> itemEntry : itemMap.entrySet()) {
			// If the event was canceled, run away
			if (event.isCanceled()) break;
			// Otherwise, fire any object interaction items
			itemEntry.getKey().procOnInteraction(event, data, itemEntry.getValue());
		}
	}
	
	/**
	 * Called when a key is pressed. Procs all Risk Of Rain 2 items that should proc on
	 * key press. 
	 * 
	 * @param event The {@code KeyInputEvent event}
	 */
	@SubscribeEvent
	public void procOnKeyPressItems(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		PlayerData data = AbstractEntityData.get(player);
		Map<OnKeyPressItem, Integer> itemMap = data.getRiskOfRain2Items(OnKeyPressItem.class);
		
		for (Map.Entry<OnKeyPressItem, Integer> itemEntry : itemMap.entrySet()) {
			if (itemEntry.getKey().shouldProcOnKeypress(event, data, itemEntry.getValue())) {
				itemEntry.getKey().procOnKeyPress(event, data, itemEntry.getValue());
			}
		}
	}
	
	/**
	 * Called when the player picks up XP. Procs any Risk Of Rain 2 items that should proc on
	 * XP pickup. 
	 * 
	 * @param event The {@code PlayerPickupXpEvent}.
	 */
	@SubscribeEvent
	public void procOnXPPickupItems(PlayerPickupXpEvent event) {
		PlayerData data = AbstractEntityData.get(event.entityPlayer);
		Map<OnPickupXPItem, Integer> itemMap = data.getRiskOfRain2Items(OnPickupXPItem.class);
		
		for (Map.Entry<OnPickupXPItem, Integer> itemEntry : itemMap.entrySet()) {
			itemEntry.getKey().procOnXPPickup(event, data, itemEntry.getValue());
		}
	}
	
	@SubscribeEvent
	public void procOnDeathItems(LivingDeathEvent event) {
		AbstractEntityData data = AbstractEntityData.get(event.entityLiving instanceof EntityPlayer? (EntityPlayer) event.entityLiving : event.entityLiving);
		Map<onDeathListener, Integer> itemMap = data.getRiskOfRain2Items(onDeathListener.class);	
		
		// Proc all on-hurt items if able to
		for (Map.Entry<onDeathListener, Integer> itemEntry : itemMap.entrySet()) {
			
			// Stop checking items if the player didn't actually get hurt
			if (event.isCanceled()) break;
			
			// Rolls if the event should proc and if so, procs it
			if (itemEntry.getKey().shouldProcOnDeath(event, data, itemEntry.getValue())) {
				itemEntry.getKey().procOnDeath(event, data, itemEntry.getValue());
			}
		}
	}
	
}
