package znick_.riskofrain2.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.item.ror.property.CustomRarity;

public class ItemBlockGray extends ItemBlock {

	public ItemBlockGray(Block block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return CustomRarity.GRAY;
	}

}
