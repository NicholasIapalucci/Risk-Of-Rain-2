package znick_.riskofrain2.api.ror.artifact.list;

import znick_.riskofrain2.api.ror.artifact.Artifact;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.property.ItemRarity;

public class ArtifactOfCommand extends Artifact {

	public ArtifactOfCommand() {
		super("command");
	}

	@Override
	public String getDescription() {
		return "Choose your items";
	}

	@Override
	public Shape[] getTabletConfiguration() {
		return new Shape[] {
			SQUARE,   SQUARE,   SQUARE,
			SQUARE,   SQUARE,   SQUARE,
			TRIANGLE, TRIANGLE, TRIANGLE
		};
	}

}
