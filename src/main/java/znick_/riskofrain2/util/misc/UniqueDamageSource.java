package znick_.riskofrain2.util.misc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class UniqueDamageSource extends DamageSource {

	private final EntityLivingBase player;
	
	public UniqueDamageSource(EntityLivingBase player) {
		super("unique");
		this.player = player;
	}
	
	public EntityLivingBase getPlayer() {
		return player;
	}

}
