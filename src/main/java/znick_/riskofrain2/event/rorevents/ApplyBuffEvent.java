package znick_.riskofrain2.event.rorevents;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import znick_.riskofrain2.api.ror.buff.Buff;

public class ApplyBuffEvent extends EntityEvent {

	private final Buff buff;
	
	public ApplyBuffEvent(Entity entity, Buff buff) {
		super(entity);
		this.buff = buff;
	}
	
	public Buff getBuff() {
		return this.buff;
	}

}
