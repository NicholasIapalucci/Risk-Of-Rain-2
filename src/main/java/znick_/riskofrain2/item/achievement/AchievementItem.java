package znick_.riskofrain2.item.achievement;

import net.minecraft.item.Item;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;

public class AchievementItem extends Item {

	private final RiskOfRain2Item item;
	
	public AchievementItem(RiskOfRain2Item item, boolean locked) {
		this.item = item;
		String isLocked = locked ? "locked" : "unlocked";
		this.setTextureName(RiskOfRain2.MODID + ":achievements/" + isLocked + "/" + item.getUnlocalizedName().substring(5) + "_" + isLocked);
		this.setUnlocalizedName(item.getUnlocalizedName().substring(5) + "_" + isLocked);
	}
	
	public RiskOfRain2Item getItem() {
		return this.item;
	}
}
