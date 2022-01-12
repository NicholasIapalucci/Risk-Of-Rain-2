package znick_.riskofrain2.util.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;

public class EntityHelper {

	public static boolean isBoss(Entity entity) {
		return entity instanceof IEntityMultiPart || 
			   entity instanceof EntityDragonPart || 
			   entity instanceof EntityWither;
	}
}
