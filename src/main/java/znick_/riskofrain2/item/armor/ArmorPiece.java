package znick_.riskofrain2.item.armor;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.util.creativetabs.RiskOfRain2CreativeTabs;

public class ArmorPiece extends ItemArmor {

	private final ArmorType type;
	private final Survivor character;
	private final String folder;
	
	public ArmorPiece(Survivor character, ArmorType armorType, String folder) {
		super(character.getArmorMaterial(), 0, armorType.getID());
		String uln = character.getName().toLowerCase() + "_" + armorType.getName().toLowerCase();
		
		this.setTextureName(RiskOfRain2Mod.MODID + ":character/" + folder + "/" + uln);
		this.setUnlocalizedName(uln);
		this.setCreativeTab(RiskOfRain2CreativeTabs.ARMOR);
		
		this.type = armorType;
		this.folder = folder;
		this.character = character;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(this.armorType == 2) return RiskOfRain2Mod.MODID + ":textures/models/armor/" + this.folder + "/" + this.character.getName().toLowerCase() + "_layer_2.png";
		return RiskOfRain2Mod.MODID + ":textures/models/armor/" + this.folder + "/" + this.character.getName().toLowerCase() + "_layer_1.png";
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someParam) {
		info.clear();
		info.add(this.character.getColor() + this.character.getName() + " " + this.type.getName());
	}
	
}
