package znick_.riskofrain2.api.ror.artifact.list;

import net.minecraft.item.Item;
import znick_.riskofrain2.api.ror.artifact.Artifact;
import znick_.riskofrain2.api.ror.items.property.ItemRarity;
import znick_.riskofrain2.item.RiskOfRain2Items;

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
	public Item getEssenceFromRarity(ItemRarity rarity) {
		switch(rarity) {
		case WHITE: return RiskOfRain2Items.WHITE_COMMAND_ESSENCE;
		default: return null;
		}
	}

}
