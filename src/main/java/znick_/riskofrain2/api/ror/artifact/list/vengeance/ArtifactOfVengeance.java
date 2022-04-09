package znick_.riskofrain2.api.ror.artifact.list.vengeance;

import znick_.riskofrain2.api.ror.artifact.Artifact;

public class ArtifactOfVengeance extends Artifact {

	public ArtifactOfVengeance() {
		super("vengeance");
	}

	@Override
	public String getDescription() {
		return "Your relentless doppelganger will invade every 10 minutes.";
	}

	@Override
	public Shape[] getTabletConfiguration() {
		return new Shape[] {
			DIAMOND, SQUARE, SQUARE,
			DIAMOND, CIRCLE, SQUARE,
			DIAMOND, SQUARE, SQUARE
		};
	}

}
