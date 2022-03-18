package znick_.riskofrain2.api.ror.buff;

import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public abstract class StatBuff extends Buff {

	private final PlayerStat stat;
	
	public StatBuff(PlayerStat stat, int itemCount, RiskOfRain2Item... items) {
		super(itemCount, items);
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
	
	public PlayerStat getAffectedStat() {
		return this.stat;
	}

}
