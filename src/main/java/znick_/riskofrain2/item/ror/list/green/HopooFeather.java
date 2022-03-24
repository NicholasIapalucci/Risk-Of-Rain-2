package znick_.riskofrain2.item.ror.list.green;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnKeyPressItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class HopooFeather extends RiskOfRain2Item implements OnKeyPressItem {

	// TODO: Refactor with a stackable buff
	private static final Map<EntityPlayer, Integer> JUMPS_REMAINING = new HashMap<>();
	
	public HopooFeather() {
		super("hopoo_feather");
	}
	
	@Override
	public void procOnKeyPress(KeyInputEvent event, EntityData player, int itemCount) {
		if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
			if (player.getEntity().onGround) {
				JUMPS_REMAINING.put((EntityPlayer) player.getEntity(), itemCount);
			} else if (JUMPS_REMAINING.get(player.getEntity()) > 0) {
				player.getEntity().motionY = 0.75;
				player.playSound("ror2:hopoo_feather");
				JUMPS_REMAINING.put((EntityPlayer) player.getEntity(), JUMPS_REMAINING.get(player.getEntity()) - 1);
			}
		}
	}
	
	@Override
	public boolean shouldProcOnKeypress(KeyInputEvent event, EntityData player, int itemCount) {
		return true;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.GREEN;
	}

	@Override
	public String getDescription() {
		return "Gain an extra jump.";
	}
}
