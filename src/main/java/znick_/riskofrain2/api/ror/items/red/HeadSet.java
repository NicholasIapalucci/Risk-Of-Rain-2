package znick_.riskofrain2.api.ror.items.red;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.item.OnHurtItem;
import znick_.riskofrain2.item.ror.proc.item.OnJumpItem;
import znick_.riskofrain2.item.ror.proc.item.OnKeyPressItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class HeadSet extends RiskOfRain2Item implements OnJumpItem, OnHurtItem, OnKeyPressItem {

	public HeadSet() {
		super("head_set");
	}
	
	@Override
	public void procOnKeyPress(KeyInputEvent event, PlayerData player, int itemCount) {
		player.getPlayer().addVelocity(0, -3, 0);
	}

	@Override
	public boolean shouldProcOnKeypress(KeyInputEvent event, PlayerData player, int itemCount) {
		return Minecraft.getMinecraft().gameSettings.keyBindSneak.isPressed();
	}
	
	@Override
	public void procOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		event.setCanceled(true);
	}

	@Override
	public boolean shouldProcOnJump(LivingJumpEvent event, PlayerData player, int itemCount) {
		return true;
	}
	
	@Override
	public void procOnJump(LivingJumpEvent event, PlayerData player, int itemCount) {
		player.getPlayer().addVelocity(0, itemCount, 0);
	}
	
	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return event.source == DamageSource.fall;
	}

	@Override
	public String getProperName() {
		return "H3AD-5T v2";
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.UTILITY;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.RED;
	}

	@Override
	public String getDescription() {
		return "Increase jump height. Crouch to slam down on the ground.";
	}
}
