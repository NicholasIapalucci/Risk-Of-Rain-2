package znick_.riskofrain2.block.itemblock;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import znick_.riskofrain2.block.BasicBlock;

/**The default implementation of {@code IBlockItem}.*/
public class BlockItem extends BasicBlock implements IBlockItem {

	public BlockItem(String name, String folder, float hardness, Material material, SoundType sound) {
		super(name, folder, hardness, material, sound);
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemBlockRed.class;
	}
	
}
