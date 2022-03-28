package znick_.riskofrain2.item.itemblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.item.ror.property.CustomRarity;

public class ItemBlockChest extends ItemBlock {

	public ItemBlockChest(Block block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
        return CustomRarity.BLUE;
    }

}
