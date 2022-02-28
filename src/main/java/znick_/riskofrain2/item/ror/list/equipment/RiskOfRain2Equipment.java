package znick_.riskofrain2.item.ror.list.equipment;

import java.util.List;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.client.keybind.RiskOfRain2KeyBinds;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnKeyPressItem;
import znick_.riskofrain2.item.ror.proc.type.OnUpdateItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public abstract class RiskOfRain2Equipment extends RiskOfRain2Item implements OnKeyPressItem, OnUpdateItem {

	private int cooldown;
	
	//TODO: Add equipment gui
	protected RiskOfRain2Equipment(String name) {
		super(name);
		this.maxStackSize = 1;
	}
	
	public abstract void useEquipment(PlayerData player);
	
	@Override
	public boolean shouldProcOnKeypress(KeyInputEvent event, PlayerData player, int itemCount) {
		return RiskOfRain2KeyBinds.ACTIVE.getKeyBinding().isPressed() && player.getEquipmentCooldown() == 0;
	}
	
	@Override
	public void procOnKeyPress(KeyInputEvent event, PlayerData player, int itemCount) {
		this.useEquipment(player);
		player.setEquipmentCooldown(this.getCooldown());
	}
	
	@Override
	public void procOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		player.tickEquipmentCooldown();
	}
	
	@Override
	public boolean shouldProcOnUpdate(LivingUpdateEvent event, PlayerData player, int itemCount) {
		return player.getEquipmentCooldown() > 0;
	}
	
	protected void setCooldown(int ticks) {
		this.cooldown = ticks;
	}
	
	public int getCooldown() {
		return this.cooldown;
	}

	@Override
	public ItemCategory getCategory() {
		return ItemCategory.ACTIVE;
	}

	@Override
	public ItemRarity getRarity() {
		return ItemRarity.ACTIVE;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean someParam) {
		super.addInformation(stack, player, info, someParam);
	}
}
