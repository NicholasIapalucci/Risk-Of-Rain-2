package znick_.riskofrain2.util.achievement;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.MinecraftForge;

public class RiskOfRain2Achievement extends Achievement {
	
	private static int x = 0;
	private static int y = 0;
	
	public RiskOfRain2Achievement(Item item) {
		super(item.getUnlocalizedName().substring(5), item.getUnlocalizedName().substring(5), x++, y++, item, null);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}

}
