package znick_.riskofrain2.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import scala.reflect.internal.Trees.This;
import znick_.riskofrain2.RiskOfRain2;

public class BasicItem extends Item {

	private EnumChatFormatting color = EnumChatFormatting.WHITE;
	private EnumRarity rarity = EnumRarity.common;
	private final String name;
	
	public BasicItem(String name, String folder) {
		this.name = name;
		this.setUnlocalizedName(name.toLowerCase().replace(' ', '_'));
		this.setTextureName(RiskOfRain2.MODID + ":" + folder + "/" + name.toLowerCase().replace(' ', '_'));
	}
	
	public BasicItem(String name, String folder, CreativeTabs tab) {
		this(name, folder);
		this.setCreativeTab(tab);
	}
	
	public BasicItem(String name, String folder, CreativeTabs tab, EnumRarity color) {
		this(name, folder, tab);
		this.rarity = color;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return this.rarity;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
		if (this.color == EnumChatFormatting.WHITE) return;
		info.clear();
		info.add(this.color + this.name);
	}
}
