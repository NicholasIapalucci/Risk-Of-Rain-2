package znick_.riskofrain2.util.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class UniqueDamageSource extends DamageSource {

	private final EntityPlayer player;
	
	public UniqueDamageSource(EntityPlayer player) {
		super("unique");
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}

}
