package znick_.riskofrain2.item.itemblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockChest extends ItemBlock {

	public ItemBlockChest(Block block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.rare;
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someParam) {
		info.clear();
		info.add(EnumChatFormatting.BLUE + "Risk Of Rain 2 Chest");
	}

}
