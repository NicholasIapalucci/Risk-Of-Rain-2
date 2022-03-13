package znick_.riskofrain2.api.ror.buff;

import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public abstract class StatBuff extends Buff {

	private final PlayerStat stat;
	
	public StatBuff(RiskOfRain2Item item, PlayerStat stat, int itemCount) {
		super(item, itemCount);
		this.stat = stat;
	}

	public abstract double getStatAdditionAmount();
	
	@Override
	public void applyEffect(PlayerData player) {
		player.addToStat(this.stat, this.getStatAdditionAmount());
	}

	@Override
	public void removeEffect(PlayerData player) {
		player.addToStat(this.stat, -this.getStatAdditionAmount());
	}
	
	public PlayerStat getAffectedStat() {
		return this.stat;
	}

}
