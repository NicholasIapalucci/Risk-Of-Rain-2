package znick_.riskofrain2.client.gui.commandessence;

import java.awt.Color;
import java.awt.Point;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.client.gui.logbook.ItemButton;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.list.green.regeneratingscrap.RegeneratingScrapItem;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public class CommandEssenceGui extends GuiScreen {

	public static final int GUI_ID = GuiHandler.getNextID(CommandEssenceGui.class);
	private int nextButtonID = 0;
	
	private final Position pos;
	private CommandEssenceEntity commandEssence;
	
	private RiskOfRain2Item[][] displayItems;
	private RiskOfRain2Item[] items;
	private ItemStack chosenItem;
	
	private Point itemStart;
	
	public CommandEssenceGui(int x, int y, int z) {
		this.pos = new Position(x, y, z);
	}
	
	@Override
	public void initGui() {
		
		this.items = RiskOfRain2Items.ITEM_SET
				.stream()
				.filter(item -> item.getRarity() == this.commandEssence.getRarity())
				.filter(item -> !(item instanceof ScrapItem))
				.filter(item -> !(item instanceof RegeneratingScrapItem))
				.toArray(RiskOfRain2Item[]::new);
			
		this.displayItems = new RiskOfRain2Item[5][(int) Math.ceil(items.length/5)];
			
		int k = 0;
		for (int i = 0; i < displayItems.length; i++) {
			for (int j = 0; j < displayItems[i].length; j++) {
				this.displayItems[i][j] = items[k];
				k++;
			}
		}
		
		int cx = this.width/2;
		int cy = this.height/2;
		int itemSize = 32;
		int space = 3;
		int totalWidth = 5 * itemSize + 4 * space;
		int rows = (int) Math.ceil(this.items.length/5);
		int totalHeight = rows * itemSize + space * (rows - 1);
		int upperLeftX = this.width/2 - totalWidth/2;
		int upperLeftY = this.height/2 - totalHeight/2;
		
		this.itemStart = new Point(upperLeftX, upperLeftY);
		
		for (int i = 0; i < this.displayItems.length; i++) {
			for (int j = 0; j < this.displayItems[i].length; j++) {
				this.buttonList.add(new ItemButton(this.nextButtonID++, this.displayItems[i][j], upperLeftX + i * (itemSize + space), upperLeftY + j * (itemSize + space), itemSize, itemSize, false));
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, EnumChatFormatting.ITALIC + "What is your command?", this.width/2, this.itemStart.y - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 2, Color.WHITE.getRGB());
    }

	@Override
	public void actionPerformed(GuiButton button) {
		if (button instanceof ItemButton) {
			
			// Get the item chosen
			RiskOfRain2Item item = (RiskOfRain2Item) ((ItemButton) button).getItem();
			this.chosenItem = new ItemStack(item, 1);
			
			// Spawn it on the server side
			Minecraft.getMinecraft().thePlayer.playSound(RiskOfRain2Mod.MODID + ":item_spawn_" + item.getRarity().name().toLowerCase(), 1, 1);
			IMessage packet = new DropItemPacketHandler.DropItemPacket(this.chosenItem, this.pos.getX(), this.pos.getY(), this.pos.getZ());
			RiskOfRain2Packets.NET.sendToServer(packet);
			
			// Close the gui
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	public void onGuiClosed() {
		// If the player exited without choosing an item, spawn a new command essence
		if (this.chosenItem == null) {
			this.commandEssence.worldObj.spawnEntityInWorld(new CommandEssenceEntity(Minecraft.getMinecraft().theWorld, this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.commandEssence.getRarity()));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
        return false;
    }

	public void setCommandEssenceEntity(CommandEssenceEntity entity) {
		this.commandEssence = entity;
	}
}
