package znick_.riskofrain2.block.itemblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.util.helper.StringHelper;

public class ItemBlockRed extends ItemBlock {

	private final Block block;
	
	public ItemBlockRed(Block block) {
		super(block);
		this.block = block;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		info.clear();
		info.add(EnumChatFormatting.RED + StringHelper.format(this.block.getUnlocalizedName().substring(5)));
	}

}
