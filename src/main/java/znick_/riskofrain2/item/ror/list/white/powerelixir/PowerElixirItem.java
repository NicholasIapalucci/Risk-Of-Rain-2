package znick_.riskofrain2.item.ror.list.white.powerelixir;

import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.ConsumableItem;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.dlc.DLC;
import znick_.riskofrain2.item.ror.proc.type.OnHurtItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class PowerElixirItem extends RiskOfRain2Item implements ConsumableItem, OnHurtItem {

	public PowerElixirItem() {
		super("power_elixir");
	}

	@Override
	public DLC getDLC() {
		return DLC.SURVIVORS_OF_THE_VOID;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.HEALING;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	@Override
	public String getDescription() {
		return "Receive an instant heal at low health. Consumed on use.";
	}

	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return player.getHealth() < player.getMaxHealth() * 0.25;
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		player.heal(player.getMaxHealth() * 0.75f);
	}

	@Override
	public void consume(PlayerData player) {
		player.replaceItem(this, this.getBrokenItem());
	}

	@Override
	public RiskOfRain2Item getBrokenItem() {
		return RiskOfRain2Items.EMPTY_BOTTLE;
	}

}
