package znick_.riskofrain2.util.misc.customs;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import znick_.riskofrain2.api.ror.artifact.ArtifactCommand;

public class RiskOfRain2Commands {

	public static void registerCommands(FMLServerStartingEvent event) {
		event.registerServerCommand(new ArtifactCommand());
	}
}
