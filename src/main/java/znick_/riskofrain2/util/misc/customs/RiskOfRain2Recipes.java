package znick_.riskofrain2.util.misc.customs;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.block.RiskOfRain2Blocks;
import znick_.riskofrain2.item.RiskOfRain2Items;

public class RiskOfRain2Recipes {
	
	public static void registerRecipes() {
		GameRegistry.addSmelting(RiskOfRain2Blocks.huntriteOre, new ItemStack(RiskOfRain2Items.RAW_HUNTRITE), 0);
		GameRegistry.addRecipe(new ItemStack(RiskOfRain2Items.LENS), new Object[] {"PPP", "P P", "PPP", 'P', Item.getItemFromBlock(Blocks.glass_pane)});
		GameRegistry.addShapelessRecipe(new ItemStack(RiskOfRain2Items.LIGHT_BLUE_LENS), new Object[] {RiskOfRain2Items.LENS, new ItemStack(Items.dye, 1, 12)});
		GameRegistry.addShapelessRecipe(new ItemStack(RiskOfRain2Items.LIGHT_BLUE_FRAMED_LENS), new Object[] {RiskOfRain2Items.LENS_FRAME, RiskOfRain2Items.LIGHT_BLUE_LENS});
		GameRegistry.addShapelessRecipe(new ItemStack(RiskOfRain2Items.LIGHT_BLUE_FRAMED_LENS_PAIR), new Object[] {RiskOfRain2Items.LIGHT_BLUE_FRAMED_LENS, RiskOfRain2Items.LIGHT_BLUE_FRAMED_LENS});
		GameRegistry.addRecipe(new ItemStack(RiskOfRain2Items.HUNTRESS_HELMET), new Object[] {"PPP", "PLP", "PPP", 'P', RiskOfRain2Items.HUNTRITE_PLATE, 'L', RiskOfRain2Items.LIGHT_BLUE_FRAMED_LENS_PAIR});
	}
}
