package znick_.riskofrain2.item.ror.list.green.regeneratingscrap;

import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.consume.ConsumedItem;

public class RegeneratingScrapConsumedItem extends ConsumedItem {

	public RegeneratingScrapConsumedItem() {
		super("regenerating_scrap_consumed", RiskOfRain2Items.REGENERATING_SCRAP);
	}

	@Override
	public String getDescription() {
		return "Transformes into Regenerating Scrap at the start of each stage.";
	}

}
