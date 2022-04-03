package znick_.riskofrain2.block.ore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import scala.actors.threadpool.Arrays;
import znick_.riskofrain2.api.mc.enums.VanillaDimension;

/**
 * The {@code OreData} class is used for storing properties of an ore block.
 * 
 * @author zNick_
 */
public class OreData {

	/**The maximum y-value that the ore can spawn at.*/
	private int upperY;
	/**The minimum y-value that thie ore can spawn at.*/
	private int lowerY;
	/**The maximum amount of ore blocks that can spawn in a single vein.*/
	private int maxVein;
	/**The minimum amount of ore blocks that can spawn in a single vein.*/
	private int minVein;
	/**The spawn weight; Determines how common the ore is.*/
	private float spawnWeight;
	/**The dimensions in which the ore can spawn.*/
	private Set<VanillaDimension> dimensions;
	/**The surrounding block for the ore; Typically stone.*/
	private Block spawnIn;
	/**The item the ore drops when broken.*/
	private Item toDrop;
	/**The maximum amount of items the ore can drop when broken.*/
	private int maxDrop;
	/**The minimum amount of items the ore can drop when broken.*/
	private int minDrop;
	
	public OreData() {
		this.upperY = 256;
		this.lowerY = 1;
		this.maxVein = 8;
		this.minVein = 1;
		this.spawnWeight = 0.5f;
		this.dimensions = new HashSet<>(Arrays.asList(new VanillaDimension[] {VanillaDimension.OVERWORLD}));
		this.spawnIn = Blocks.stone;
		this.toDrop = Item.getItemFromBlock(Blocks.stone);
		this.maxDrop = 1;
		this.minDrop = 1;
	}
	
	public OreData setUpperY(int upperY) {
		this.upperY = upperY;
		return this;
	}
	
	public int getUpperY() {
		return this.upperY;
	}
	
	public OreData setLowerY(int lowerY) {
		this.lowerY = lowerY;
		return this;
	}
	
	public int getLowerY() {
		return this.lowerY;
	}
	
	public OreData setMaxVeinSize(int maxVein) {
		this.maxVein = maxVein;
		return this;
	}
	
	public int getMaxVeinSize() {
		return this.maxVein;
	}
	
	public OreData setMinVeinSiz(int minVein) {
		this.minVein = minVein;
		return this;
	}
	
	public int getMinVeinSize() {
		return this.minVein;
	}
	
	public OreData setSpawnWeight(float spawnWeight) {
		this.spawnWeight = spawnWeight;
		return this;
	}
	
	public float getSpawnWeight() {
		return this.spawnWeight;
	}
	
	public OreData addDimension(VanillaDimension d) {
		this.dimensions.add(d);
		return this;
	}
	
	public boolean generatesInDimension(VanillaDimension d) {
		return this.dimensions.contains(d);
	}
	
	public Set<VanillaDimension> getDimensions() {
		return this.dimensions;
	}
	
	public OreData setSpawnBlock(Block block) {
		this.spawnIn = block;
		return this;
	}
	
	public Block getSpawnBlock() {
		return this.spawnIn;
	}
	
	public OreData setDropItem(Item item) {
		this.toDrop = item;
		return this;
	}
	
	public Item getDropItem() {
		return this.toDrop;
	}
	
	public OreData setMaxDropAmount(int amount) {
		this.maxDrop = amount;
		return this;
	}
	
	public int getMaxDropAmount() {
		return this.maxDrop;
	}
	
	public OreData setMinDropAmount(int amount) {
		this.minDrop = amount;
		return this;
	}
	
	public int getMinDropAmount() {
		return this.minDrop;
	}
	
}
