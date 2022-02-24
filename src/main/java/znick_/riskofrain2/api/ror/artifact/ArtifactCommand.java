package znick_.riskofrain2.api.ror.artifact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import znick_.riskofrain2.api.mc.data.WorldData;

public class ArtifactCommand implements ICommand {

	@Override
	public int compareTo(Object obj) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "artifact";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public List getCommandAliases() {
		return null;
	}

	/**
	 * Executes the command.
	 * 
	 * @param sender The entity sending the command, traditionally either a player or a tile entity from
	 * a command block.
	 * @param args The set of argument strings the user typed
	 */
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		World world = sender.getEntityWorld();
		WorldData data = WorldData.forWorld(world);
		if (world.isRemote) return;
		
		// Throw error if incorrect number of arguments
		if (args.length != 2) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Error processing artifact command: invalid number of arguments"));
			return;
		}
		
		// Check whether the artifact is being enabled or disabled
		boolean isEnable;
		if (args[0].equals("enable")) isEnable = true;
		else if (args[0].equals("disable")) isEnable = false;
		
		// If neither, throw an error
		else {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Error processing artifact command: Second argument must be \"enable\" or \"disable\""));
			return;
		}
		
		// Find the artifact being changed
		Artifact artifact = Artifact.fromName(args[1]);
		
		// If there is no artifact with that name, throw an error.
		if (artifact == null) {
			sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Error processing artifact command: Cannot find artifact with the name \"" + args[1] + "\""));
			return;
		}
		
		// If the artifact is attempting to be enabled
		if (isEnable) {
			// If the artifact is already enabled, let them nknow
			if (artifact.isEnabled(world)) {
				sender.addChatMessage(new ChatComponentText(artifact.getProperName() + " is already enabled"));
				return;
			}
			
			// Otherwise, enable it.
			data.enableArtifact(artifact);
			sender.addChatMessage(new ChatComponentText("Enabled " + artifact.getProperName()));
		}
		
		// If the artifact is attempting to be disabled
		else {
			// If it's already disabled, let them know
			if (!artifact.isEnabled(world)) {
				sender.addChatMessage(new ChatComponentText(artifact.getProperName() + " is already disabled"));
				return;
			}
			
			//Otherwise, disable it
			data.disableArtifact(artifact);
			sender.addChatMessage(new ChatComponentText("Disabled " + artifact.getProperName()));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (!(sender instanceof EntityPlayer)) return true;
		return Minecraft.getMinecraft().getIntegratedServer().getConfigurationManager().func_152596_g(((EntityPlayer) sender).getGameProfile());
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		List<String> tabs = new ArrayList<>();
		System.out.println(Arrays.toString(args));
		
		if (args.length == 1 && "enable".startsWith(args[0])) {
			tabs.add("enable");
			return tabs;
		}
		
		if (args.length == 1 && "disable".startsWith(args[0])) {
			tabs.add("disable");
			return tabs;
		}
		
		if (args.length == 2) {
			String[] artifactNames = Arrays.asList(Artifact.getArtifacts())
										.stream()
										.map(artifact -> artifact.getName())
										.toArray(String[]::new);
			
			for (String name : artifactNames) {
				if (name.startsWith(args[1])) tabs.add(name); 
			}
			
			return tabs;
		}
		
		return tabs;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
