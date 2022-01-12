package znick_.riskofrain2.api.ror.items.equipment;

import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import ibxm.Player;
import net.minecraft.entity.SharedMonsterAttributes;
import znick_.riskofrain2.api.mc.PlayerData;

public class ForeignFruit extends RiskOfRain2Equipment {
	
	public ForeignFruit() {
		super("foreign_fruit");
		this.setCooldown(900);
	}
	
	@Override
	public void useEquipment(PlayerData player) {
		player.heal((int) (player.getMaxHealth() * 0.5f));
	}
	
	@Override
	public String getDescription() {
		return "Heal on use.";
	}

}
