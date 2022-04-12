package znick_.riskofrain2.block.itemgen.smallchest;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.block.itemgen.TileEntityItemGenerator;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.util.helper.ArrayHelper;

public class SmallChestTileEntity extends TileEntityItemGenerator {

	@Override
	public RiskOfRain2Item generateItem(EntityPlayer player) {
		double rand = Math.random();
		if (rand < 0.099) return ArrayHelper.randomElement(RiskOfRain2Items.itemSubset(item -> !item.isExcludedFromChests() && item.getRarity() == ItemRarity.RED));
		if (rand < 0.198) return ArrayHelper.randomElement(RiskOfRain2Items.itemSubset(item -> !item.isExcludedFromChests() && item.getRarity() == ItemRarity.GREEN));
		return ArrayHelper.randomElement(RiskOfRain2Items.itemSubset(item -> !item.isExcludedFromChests() && item.getRarity() == ItemRarity.WHITE));
	}

}
