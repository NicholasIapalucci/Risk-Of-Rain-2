package znick_.riskofrain2.api.ror.artifact;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.ror.artifact.list.command.ArtifactOfCommand;
import znick_.riskofrain2.api.ror.artifact.list.vengeance.ArtifactOfVengeance;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;
import znick_.riskofrain2.util.helper.StringHelper;

/**
 * Artifacts are modifiers from the Risk of Rain 2 game
 * 
 * @author zNick_
 */
public abstract class Artifact {
	
	/**The set of all artifacts. All artifacts are added to this set during construction.*/
	private static final Set<Artifact> ARTIFACTS = new LinkedHashSet<>();
	
	/**
	 * The "Artifact of Command". Upon generating an item, chests instead generate "command essence"
	 * which allows the player to choose their items.
	 */
	public static final ArtifactOfCommand COMMAND = new ArtifactOfCommand();
	
	/**
	 * The "Artifact of Vengeance". Every 10 minutes, an evil player will spawn near a player with
	 * the same Risk of Rain 2 items as them.
	 */
	public static final ArtifactOfVengeance VENGEANCE = new ArtifactOfVengeance();
	
	/**The artifact tablet triangle shape.*/
	protected static final Shape TRIANGLE = new Shape();
	/**The artifact tablet square shape.*/
	protected static final Shape SQUARE = new Shape();
	/**The artifact tablet circle shape.*/
	protected static final Shape CIRCLE = new Shape();
	/**The artifact tablet diamond shape.*/
	protected static final Shape DIAMOND = new Shape();
	
	/**
	 * The name of this artifact. The name should be one word in all lowercase, without the phrase "artifact of"
	 * such as simply "command" or "vengence".
	 */
	private final String name;
	
	/**
	 * Returns the texture for this artifact icon as a {@code ResourceLocation}
	 */
	private final ResourceLocation texture;
	
	/**
	 * Creates a new artifact with the given name. The name must be unique.
	 * 
	 * @param name The name used to identify this artifact
	 */
	protected Artifact(String name) {
		this.name = name;
		this.texture = RiskOfRain2Resources.get(RiskOfRain2Resources.GUI + "artifacts/" + name);
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
	
	/**
	 * Returns the code name of this artifact.
	 */
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
	
	/**Returns the description of this artifact.*/
	public abstract String getDescription();
	
	/**Returns the configuration of shapes on the tablet that corresponds to this artifact.*/
	public abstract Shape[] getTabletConfiguration();
	
	/**
	 * Class for creating shapes for the artifact tablets.
	 * 
	 * @author zNick_
	 */
	public static class Shape {
		private Shape() {}
	}

}
