package znick_.riskofrain2.item.ror.list.green;

import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.proc.type.OnJumpItem;
import znick_.riskofrain2.item.ror.property.ItemCategory;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class WaxQuail extends RiskOfRain2Item implements OnJumpItem {

	public WaxQuail() {
		super("wax_quail");
	}
	
	@Override
	public void procOnJump(LivingJumpEvent event, AbstractEntityData player, int itemCount) {
		Vec3 lookVector = player.getLookVector();
		player.getEntity().addVelocity(0.5 * lookVector.xCoord * itemCount, 0, 0.5 * lookVector.zCoord * itemCount);
		if (player.getWorld().isRemote) player.playSound("ror2:wax_quail");
	}
	
	@Override
	public boolean shouldProcOnJump(LivingJumpEvent event, AbstractEntityData player, int itemCount) {
		return player.isSprinting();
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
		return "Jumping while sprinting boosts you forward.";
	}
}
