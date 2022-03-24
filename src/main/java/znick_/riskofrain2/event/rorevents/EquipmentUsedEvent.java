package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;
import znick_.riskofrain2.item.ror.list.equipment.RiskOfRain2Equipment;

@Cancelable
public class EquipmentUsedEvent extends EntityEvent {

	private final RiskOfRain2Equipment equipment;
	
	public EquipmentUsedEvent(EntityLivingBase entity, RiskOfRain2Equipment equipment) {
		super(entity);
		this.equipment = equipment;
	}
	
	public RiskOfRain2Equipment getUsedEquipment() {
		return this.equipment;
	}

}
