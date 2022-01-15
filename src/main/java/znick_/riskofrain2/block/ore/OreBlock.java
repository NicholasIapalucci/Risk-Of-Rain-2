package znick_.riskofrain2.block.ore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import znick_.riskofrain2.block.BasicBlock;

public class OreBlock extends BasicBlock {
	
	private final OreData oreData;
	
	public OreBlock(String name, OreData data) {
		super(name, "character/huntress", 3.0f, Material.rock, Block.soundTypeStone, 0, true, false);
		this.oreData = data;
	}
	
	@Override
	public int quantityDropped(Random rand) {
        return rand.nextInt(this.oreData.getMaxDropAmount() - this.oreData.getMinDropAmount() + 1) + this.oreData.getMinDropAmount();
    }
	
	@Override
    public Item getItemDropped(int a, Random rand, int b) {
        return this.oreData.getDropItem();
    }
	
	public OreData getOreData() {
		return this.oreData;
	}
} 
