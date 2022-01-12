package znick_.riskofrain2.event.handler;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import znick_.riskofrain2.api.ror.items.white.CautiousSlug;
import znick_.riskofrain2.event.Tick;

public class CombatHandler extends EventHandler {

	private static final Map<EntityPlayer, Integer> TICKS_SINCE_ATTACK = new HashMap<>();
	private static final Map<EntityPlayer, Integer> TICKS_SINCE_HURT = new HashMap<>();
	
	@SubscribeEvent
	public void onAttack(LivingAttackEvent event) {
		if (event.source != null && event.source.getEntity() instanceof EntityPlayer) {
			TICKS_SINCE_ATTACK.put((EntityPlayer) event.source.getEntity(), Tick.server());
		}
	}
	
	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			TICKS_SINCE_HURT.put(player, Tick.server());
			if (CautiousSlug.isActiveOnPlayer(player)) CautiousSlug.disable(player);
		}
	}
	
	public static int ticksSinceLastAttack(EntityPlayer player) {
		if (TICKS_SINCE_ATTACK.get(player) == null) TICKS_SINCE_ATTACK.put(player, 0);
		return Tick.server() - TICKS_SINCE_ATTACK.get(player);
	}
	
	public static int ticksSinceLastHurt(EntityPlayer player) {
		if (TICKS_SINCE_HURT.get(player) == null) TICKS_SINCE_HURT.put(player, 0);
		return Tick.server() - TICKS_SINCE_HURT.get(player);
	}
}
