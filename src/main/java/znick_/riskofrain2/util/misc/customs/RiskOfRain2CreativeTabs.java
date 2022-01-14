package znick_.riskofrain2.util.misc.customs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.api.ror.items.list.white.SoldierSyringe;
import znick_.riskofrain2.block.RiskOfRain2Blocks;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class RiskOfRain2CreativeTabs {

	public static final CreativeTabs ITEMS = new CreativeTabs("tabItems") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(RiskOfRain2Items.SOLDIER_SYRINGE).getItem();
		}
	};
	
	public static final CreativeTabs BLOCKS = new CreativeTabs("tabBlocks") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(RiskOfRain2Blocks.huntriteOre).getItem();
		}
	};
	
	public static final CreativeTabs ARMOR = new CreativeTabs("tabArmor") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(RiskOfRain2Items.HUNTRESS_CHESTPLATE).getItem();
		}
	};
	
	public static final CreativeTabs MISC = new CreativeTabs("tabMisc") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(RiskOfRain2Blocks.riskChest).getItem();
		}
	}; 
	
	public static void registerCreativeTabs() {}
}
