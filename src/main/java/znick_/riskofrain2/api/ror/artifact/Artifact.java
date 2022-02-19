package znick_.riskofrain2.api.ror.artifact;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.mc.data.WorldData;
import znick_.riskofrain2.api.ror.artifact.list.ArtifactOfCommand;
import znick_.riskofrain2.util.helper.StringHelper;

public abstract class Artifact {
	
	private static final Set<Artifact> ARTIFACTS = new LinkedHashSet<>();
	
	public static final ArtifactOfCommand COMMAND = new ArtifactOfCommand();
	
	protected static final Shape TRIANGLE = new Shape();
	protected static final Shape SQUARE = new Shape();
	protected static final Shape CIRCLE = new Shape();
	
	/**
	 * The name of this artifact. The name should be one word in all lowercase, without the phrase "artifact of"
	 * such as simply "command" or "vengence".
	 */
	private final String name;
	
	private final ResourceLocation texture;
	
	/**
	 * Creates a new artifact with the given name. The name must be unique.
	 * 
	 * @param name The name used to identify this artifact
	 */
	protected Artifact(String name) {
		this.name = name;
		this.texture = new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/artifacts/" + name);
		ARTIFACTS.add(this);
	}
	
	/**
	 * Returns an artifact from its name. Used to process the /artifact command.
	 * 
	 * @param name The name of the artifact
	 * 
	 * @return The artifact with the given name
	 */
	public static Artifact fromName(String name) {
		for (Artifact artifact : ARTIFACTS) if (artifact.getName().equals(name)) return artifact;
		return null;
	}
	
	/**
	 * Returns an array of all artifacts.
	 */
	public static Artifact[] getArtifacts() {
		return ARTIFACTS.toArray(new Artifact[0]);
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the proper name of this artifact, with the phrase "Artifact of " prepended to it.
	 */
	public String getProperName() {
		return "Aritfact of " + StringHelper.format(this.name);
	}
	
	/**
	 * Returns the texture for the icon of this artifact
	 */
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	/**
	 * Returns whether or not this artifact is currently enabled in the current world. 
	 * 
	 * @return
	 */
	public boolean isEnabled(World world) {
		return WorldData.forWorld(world).isArtifactEnabled(this);
	}
	
	/**Returns the description of this artifact.*/
	public abstract String getDescription();
	
	/**Returns the configuration of shapes on the tablet that corresponds to this artifact.*/
	public abstract Shape[] getTabletConfiguration();
	
	public static class Shape {
		private Shape() {}
	}

}
