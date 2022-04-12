package znick_.riskofrain2.api.ror.artifact.list.command.gui;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.api.ror.artifact.list.command.entity.CommandEssenceEntity;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.client.gui.logbook.ItemButton;
import znick_.riskofrain2.entity.util.EntityPacketHandler.EntityPacket;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.list.green.regeneratingscrap.RegeneratingScrapItem;
import znick_.riskofrain2.net.RiskOfRain2Packets;

/**
 * Class for creating the command essence GUI that lets the player pick their item.
 * 
 * @author zNick_
 */
public class CommandEssenceGui extends GuiScreen {

	/**The GUI ID*/
	public static final int GUI_ID = GuiHandler.getNextID(CommandEssenceGui.class);
	/**The next available button ID*/
	private int nextButtonID = 0;
	
	/**The location of the GUI*/
	private final Position pos;
	/**The command essence entity that created the GUI*/
	private CommandEssenceEntity commandEssence;
	
	/**The items to display, formatted as a 2D array.*/
	private RiskOfRain2Item[][] displayItems;
	/**The items to display*/
	private RiskOfRain2Item[] items;
	
	/**The upper left corner of the first item*/
	private Point itemStart;
	
	/**
	 * Creates a new {@code CommandEssenceGui} at the specified position
	 * 
	 * @param x The x-coordinate of the GUI
	 * @param y The y-coordinate of the GUI
	 * @param z The z-coordinate of the GUI
	 */
	public CommandEssenceGui(int x, int y, int z) {
		this.pos = new Position(x, y, z);
	}
	
	@Override
	public void initGui() {
		
		this.items = Arrays.stream(RiskOfRain2Items.itemSet())
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
		
		// Draw the translucent gray background
		this.drawDefaultBackground();
		
		// Draw the buttons onto the screen
		super.drawScreen(mouseX, mouseY, partialTick);
		
		// Draw the "What's your command?" text
		this.drawCenteredString(
			Minecraft.getMinecraft().fontRenderer, 
			EnumChatFormatting.ITALIC + "What is your command?", 
			this.width/2, this.itemStart.y - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 2, 
			Color.WHITE.getRGB()
		);
    }

	@Override
	public void actionPerformed(GuiButton button) {
		
		// Check if the player chose an item
		if (button instanceof ItemButton) {
			
			// Get the item chosen
			RiskOfRain2Item item = (RiskOfRain2Item) ((ItemButton) button).getItem();
			ItemStack chosenItem = new ItemStack(item, 1);
			
			// Spawn it on the server side
			Minecraft.getMinecraft().thePlayer.playSound(RiskOfRain2Mod.MODID + ":item_spawn_" + item.getRarity().name().toLowerCase(), 1, 1);
			IMessage dropItemPacket = new DropItemPacketHandler.DropItemPacket(chosenItem, this.pos.getX(), this.pos.getY(), this.pos.getZ());
			RiskOfRain2Packets.NET.sendToServer(dropItemPacket);
			
			// Kill the command essence entity and close the gui
			IMessage entityPacket = new EntityPacket(this.commandEssence) {
				@Override
				public void onServerMessage(Entity entity) {
					entity.setDead();
				}
			};
			RiskOfRain2Packets.NET.sendToServer(entityPacket);
			this.commandEssence.setDead();
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	public void onGuiClosed() {
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
        return false;
    }

	/**
	 * Sets the command essence entity that created this GUI. This is the entity that will be killed
	 * if the player choosen an item.
	 */
	public void setCommandEssenceEntity(CommandEssenceEntity entity) {
		this.commandEssence = entity;
	}
}
