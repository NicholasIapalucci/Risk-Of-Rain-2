package znick_.riskofrain2.api.ror.items.white;

import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnPickupXPItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class MonsterTooth extends RiskOfRain2Item implements OnPickupXPItem {
	
	public MonsterTooth() {
		super("monster_tooth");
	}
	
	@Override
	public void procOnXPPickup(PlayerPickupXpEvent event, PlayerData player, int itemCount) {
		if (!player.getWorld().isRemote) player.heal(event.orb.getXpValue()/3 * itemCount);
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
		return "Heal when consuming XP.";
	}
}
