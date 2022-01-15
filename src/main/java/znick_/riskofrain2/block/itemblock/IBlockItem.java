package znick_.riskofrain2.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**An {@code IBlockItem} represents a block that must get initialized with an {@code ItemBlock}.*/
public interface IBlockItem {
	/**Returns the {@code ItemBlock} class to register this block with.*/
	public abstract Class<? extends ItemBlock> getItemBlockClass();
}
