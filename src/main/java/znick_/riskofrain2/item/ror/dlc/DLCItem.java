package znick_.riskofrain2.item.ror.dlc;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public abstract class DLCItem extends RiskOfRain2Item {

	public DLCItem(String name) {
		super(name);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean isAdvanced) {
		super.addInformation(stack, player, info, isAdvanced);
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) info.add("DLC: " + (this.getDLC().getColor() + this.getDLC().toString()));
	}
	
	/**
	 * Returns the {@code DLC} pack that the item is from. 
	 */
	public abstract DLC getDLC();

}
