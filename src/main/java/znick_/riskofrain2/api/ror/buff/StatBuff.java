package znick_.riskofrain2.api.ror.buff;

import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.stat.EntityStat;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public abstract class StatBuff extends Buff {

	private final EntityStat stat;
	
	public StatBuff(EntityStat stat, int itemCount) {
		super(itemCount);
		this.stat = stat;
	}

	public abstract double getStatAdditionAmount(AbstractEntityData entity);
	
	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.addToStat(this.stat, this.getStatAdditionAmount(entity));
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.removeFromStat(this.stat, this.getStatAdditionAmount(entity));
	}
	
	public EntityStat getAffectedStat() {
		return this.stat;
	}

}
