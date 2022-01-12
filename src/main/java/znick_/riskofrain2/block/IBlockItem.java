package znick_.riskofrain2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public interface IBlockItem {
	public abstract Class<? extends ItemBlock> getItemBlockClass();
}
