package znick_.riskofrain2.item.ror.list.white.fireworks;

import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.event.rorevents.ObjectInteractionEvent;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.ItemProccer;
import znick_.riskofrain2.item.ror.proc.type.OnObjectInteractionItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class FireworksItem extends RiskOfRain2Item implements OnObjectInteractionItem, OnUpdateItem {

	private int fireworksShot = 0;
	private TileEntity tile = null;
	
	public FireworksItem() {
		super("bundle_of_fireworks");
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.DAMAGE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Activating an interactable launches fireworks at nearby enemies.";
	}

	@Override
	public void procOnInteraction(ObjectInteractionEvent event, AbstractEntityData player, int itemCount) {
		this.tile = event.getInteractedObject();
		this.shootFirework(player, tile, itemCount);
	}
	
	/**
	 * Shoots a firework and increments the {@link #fireworksShot} counter.
	 * 
	 * @param player The player who's shooting the fireworks
	 * @param tile The chest tile entity that the fireworks are shooting out of
	 * @param itemCount The amount of bundles of fireworks that the player has
	 */
	private void shootFirework(AbstractEntityData player, TileEntity tile, int itemCount) {
		player.getWorld().spawnEntityInWorld(new FireworkEntity(player.getWorld(), tile.xCoord + Math.random(), tile.yCoord + 1, tile.zCoord + Math.random()));
		if (player.getWorld().isRemote) {
			player.playSound("ror2:fireworks_launch");
			if (++this.fireworksShot > 4 + 4 * itemCount) {
				this.fireworksShot = 0;
				this.tile = null;
			}
		}
	}

	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		return this.fireworksShot != 0 && ItemProccer.getUpdateCounter() % 3 == 0;
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, AbstractEntityData player, int itemCount) {
		this.shootFirework(player, this.tile, itemCount);
	}

}
