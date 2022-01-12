package znick_.riskofrain2.api.ror.items.green;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnKeyPressItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class HopooFeather extends RiskOfRain2Item implements OnKeyPressItem {

	private static final Map<EntityPlayer, Integer> JUMPS_REMAINING = new HashMap<>();
	
	public HopooFeather() {
		super("hopoo_feather");
	}
	
	@Override
	public void procOnKeyPress(KeyInputEvent event, PlayerData player, int itemCount) {
		if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
			if (player.getPlayer().onGround) {
				JUMPS_REMAINING.put(player.getPlayer(), itemCount);
			} else if (JUMPS_REMAINING.get(player.getPlayer()) > 0) {
				player.getPlayer().motionY = 0.75;
				player.playSound("ror2:hopoo_feather", 50, 1);
				JUMPS_REMAINING.put(player.getPlayer(), JUMPS_REMAINING.get(player.getPlayer()) - 1);
			}
		}
	}
	
	@Override
	public boolean shouldProcOnKeypress(KeyInputEvent event, PlayerData player, int itemCount) {
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
