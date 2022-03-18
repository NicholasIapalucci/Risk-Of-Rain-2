package znick_.riskofrain2.api.mc.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.ror.artifact.Artifact;

public class WorldData extends WorldSavedData {

	public static final WorldData INSTANCE = new WorldData();
	private final Map<Artifact, Boolean> artifacts = new HashMap<>();
	
	public WorldData() {
		super("world_data");
		for (Artifact artifact : Artifact.getArtifacts()) {
			artifacts.put(artifact, false);
		}
		this.markDirty();
	}
	
	public static WorldData forWorld(World world) {
	      MapStorage storage = world.perWorldStorage;
	      WorldData result = (WorldData) storage.loadData(WorldData.class, "world_data");
	      if (result == null) {
	         result = new WorldData();
	         storage.setData("world_data", result);
	      }
	      return result;
	}
	
	public void enableArtifact(Artifact artifact) {
		this.artifacts.put(artifact, true);
		this.markDirty();
		if (RiskOfRain2Mod.DEBUG) System.out.println("Enabling artifact \"" + artifact + "\"");
	}
	
	public void disableArtifact(Artifact artifact) {
		this.artifacts.put(artifact, false);
		this.markDirty();
		if (RiskOfRain2Mod.DEBUG) System.out.println("Disabling artifact \"" + artifact + "\"");
	}
	
	public boolean isArtifactEnabled(Artifact artifact) {
		return this.artifacts.get(artifact);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagCompound properties = new NBTTagCompound();
		for (Map.Entry<Artifact, Boolean> artifactEntry : artifacts.entrySet()) {
			properties.setBoolean(artifactEntry.getKey().getName(), artifactEntry.getValue());
		}
		nbt.setTag("artifacts", properties);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound properties = (NBTTagCompound) nbt.getTag("artifacts");
		for (Artifact artifact : artifacts.keySet()) {
			artifacts.put(artifact, properties.getBoolean(artifact.getName()));
		}
	}
}
