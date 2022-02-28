package znick_.riskofrain2.item.ror.list.equipment.supermassiveleech;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.event.handler.TickHandler;
import znick_.riskofrain2.item.ror.list.equipment.RiskOfRain2Equipment;
import znick_.riskofrain2.item.ror.proc.type.OnHitItem;

public class SuperMassiveLeechItem extends RiskOfRain2Equipment implements OnHitItem {

	public SuperMassiveLeechItem() {
		super("super_massive_leech");
		this.setCooldown(TickHandler.fromSeconds(60));
	}

	@Override
	public void useEquipment(PlayerData player) {
		player.addBuff(new SuperMassiveLeechBuff());
	}
	
	@Override
	public String getDescription() {
		return "Heal for a percentage of the damage you deal for 8 seconds";
	}

	@Override
	public void procOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		player.heal(event.ammount * 0.2f);
	}

	@Override
	public boolean shouldProcOnHit(LivingAttackEvent event, PlayerData player, EntityLivingBase enemy, int itemCount) {
		return player.hasBuff(SuperMassiveLeechBuff.class);
	}
}
