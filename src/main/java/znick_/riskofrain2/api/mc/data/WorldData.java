package znick_.riskofrain2.api.mc.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.artifact.Artifact;

public class WorldData extends WorldSavedData {

	private static final Map<Artifact, Boolean> ARTIFACTS = new HashMap<>();
	
	static {
		for (Artifact artifact : Artifact.getArtifacts()) {
			ARTIFACTS.put(artifact, false);
		}
	}
	
	public WorldData(String s) {
		super(s);
	}
	
	public static void enableArtifact(Artifact artifact) {
		ARTIFACTS.put(artifact, true);
		if (RiskOfRain2.DEBUG) System.out.println("Enabling artifact \"" + artifact + "\"");
	}
	
	public static void disableArtifact(Artifact artifact) {
		ARTIFACTS.put(artifact, false);
		if (RiskOfRain2.DEBUG) System.out.println("Disabling artifact \"" + artifact + "\"");
	}
	
	public static boolean isArtifactEnabled(Artifact artifact) {
		return ARTIFACTS.get(artifact);
	}

	@Override
	public void readFromNBT(NBTTagCompound p_76184_1_) {
		
	}

	@Override
	public void writeToNBT(NBTTagCompound p_76187_1_) {
		
	}
}
