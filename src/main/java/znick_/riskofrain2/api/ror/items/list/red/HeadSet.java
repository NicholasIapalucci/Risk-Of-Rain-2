package znick_.riskofrain2.api.ror.items.list.red;

import java.util.List;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.items.RiskOfRain2Item;
import znick_.riskofrain2.api.ror.items.proc.type.OnHurtItem;
import znick_.riskofrain2.api.ror.items.proc.type.OnJumpItem;
import znick_.riskofrain2.api.ror.items.proc.type.OnKeyPressItem;
import znick_.riskofrain2.api.ror.items.property.ItemCategory;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;

public class HeadSet extends RiskOfRain2Item implements OnJumpItem, OnHurtItem, OnKeyPressItem {

	public HeadSet() {
		super("head_set");
	}
	
	@Override
	public boolean shouldProcOnKeypress(KeyInputEvent event, PlayerData player, int itemCount) {
		return Minecraft.getMinecraft().gameSettings.keyBindSneak.isPressed();
	}
	
	@Override
	public void procOnKeyPress(KeyInputEvent event, PlayerData player, int itemCount) {
		player.getPlayer().addVelocity(0, -3, 0);
		List<EntityLivingBase> entities = player.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, player.radialBox(4));
		for (EntityLivingBase entity : entities) {
			if (entity == player.getPlayer()) continue;
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player.getPlayer()), 10);
		}
	}
	
	@Override
	public boolean shouldProcOnHurt(LivingHurtEvent event, PlayerData player, int itemCount) {
		return event.source == DamageSource.fall;
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
