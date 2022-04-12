package znick_.riskofrain2.event.rorevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import znick_.riskofrain2.api.ror.buff.Buff;

/**
 * The {@code ApplyBuffEvent} is fired when a buff is applied to an entity. If the event
 * is canceled, then the buff will not be applied.
 *  
 * @author zNick_
 */
@Cancelable
public class ApplyBuffEvent extends LivingEvent {

	/**The buff that was applied*/
	private final Buff buff;
	
	/**
	 * Creates a new {@code ApplyBuffEvent}.
	 * 
	 * @param entity The entity that the buff is applying to
	 * @param buff The buff to apply
	 */
	public ApplyBuffEvent(EntityLivingBase entity, Buff buff) {
		super(entity);
		this.buff = buff;
	}
	
	/**Returns the buff that is to be applied to the entity.*/
	public Buff getBuff() {
		return this.buff;
	}

}
