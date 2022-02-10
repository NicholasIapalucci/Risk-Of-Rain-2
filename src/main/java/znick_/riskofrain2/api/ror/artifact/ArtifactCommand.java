package znick_.riskofrain2.api.ror.artifact;

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
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "artifact";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return null;
	}

	@Override
	public List getCommandAliases() {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		World world = sender.getEntityWorld();
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
		
		//
		if (isEnable) {
			if (artifact.isEnabled()) {
				sender.addChatMessage(new ChatComponentText(artifact.getProperName() + " is already enabled"));
				return;
			}
			WorldData.enableArtifact(artifact);
			sender.addChatMessage(new ChatComponentText("Enabled " + artifact.getProperName()));
		}
		
		else {
			if (!artifact.isEnabled()) {
				sender.addChatMessage(new ChatComponentText(artifact.getProperName() + " is already disabled"));
				return;
			}
			WorldData.disableArtifact(artifact);
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
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
