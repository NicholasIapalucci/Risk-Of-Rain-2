package znick_.riskofrain2.block.itemgen.lunarpod;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.ArrayHelper;

/**
 * The tile entity class for Lunar Pods.
 * 
 * @author zNick_
 */
public class TileEntityLunarPod extends TileEntityItemGenerator {

	@Override
	public RiskOfRain2Item generateItem(EntityPlayer player) {
		return ArrayHelper.randomElement(RiskOfRain2Items.itemSubset(item -> item.getRarity() == ItemRarity.LUNAR));
	}

}
