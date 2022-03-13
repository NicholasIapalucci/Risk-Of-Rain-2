package znick_.riskofrain2.block.itemgen.smallchest;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class SmallChestTileEntity extends TileEntityItemGenerator {

	@Override
	public RiskOfRain2Item generateItem(EntityPlayer player) {
		double rand = Math.random();
		if (rand < 0.099) return this.generateItem(ItemRarity.RED, player);
		if (rand < 0.198) return this.generateItem(ItemRarity.GREEN, player);
		return this.generateItem(ItemRarity.WHITE, player);
	}

}
