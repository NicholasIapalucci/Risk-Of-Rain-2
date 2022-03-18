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
	
	/**
	 * Returns the instance of the command essence item with the specified rarity.
	 * 
	 * @param rarity The rarity of the command essence
	 */
	public static RiskOfRain2Item getEssenceFromRarity(ItemRarity rarity) {
		switch(rarity) {
		case WHITE: return RiskOfRain2Items.WHITE_COMMAND_ESSENCE;
		case GREEN: return RiskOfRain2Items.GREEN_COMMAND_ESSENCE;
		case RED: return RiskOfRain2Items.RED_COMMAND_ESSENCE;
		case BOSS: return RiskOfRain2Items.BOSS_COMMAND_ESSENCE;
		case LUNAR: return RiskOfRain2Items.LUNAR_COMMAND_ESSENCE;
		default: return null;
		}
	}

}
