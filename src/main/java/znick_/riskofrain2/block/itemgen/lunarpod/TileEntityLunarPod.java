package znick_.riskofrain2.block.itemgen.lunarpod;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class TileEntityLunarPod extends TileEntityItemGenerator {

	@Override
	public RiskOfRain2Item generateItem(EntityPlayer player) {
		return this.generateItem(ItemRarity.LUNAR, player);
	}

}
