package znick_.riskofrain2.client.gui.commandessence;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import znick_.riskofrain2.api.mc.Position;
import znick_.riskofrain2.client.gui.GuiHandler;
import znick_.riskofrain2.client.gui.logbook.ItemButton;
import znick_.riskofrain2.entity.inanimate.CommandEssenceEntity;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.item.ror.list.ScrapItem;
import znick_.riskofrain2.item.ror.property.ItemRarity;
import znick_.riskofrain2.net.RiskOfRain2Packets;

public class CommandEssenceGui extends GuiScreen {

	public static final int GUI_ID = GuiHandler.getNextID(CommandEssenceGui.class);
	private int nextButtonID = 0;
	
	private final Position position;
	private CommandEssenceEntity commandEssence;
	
	private final RiskOfRain2Item[][] potentialItems;
	
	public CommandEssenceGui(int x, int y, int z) {
		this.position = new Position(x, y, z);
		RiskOfRain2Item[] items = RiskOfRain2Items.ITEM_SET
			.stream()
			.filter(item -> item.getRarity() == this.getRarity())
			.filter(item -> !(item instanceof ScrapItem))
			.toArray(RiskOfRain2Item[]::new);
		
		this.potentialItems = new RiskOfRain2Item[5][(int) Math.ceil(items.length/5)];
		
		int k = 0;
		for (int i = 0; i < potentialItems.length; i++) {
			for (int j = 0; j < potentialItems[i].length; j++) {
				this.potentialItems[i][j] = items[k];
				k++;
			}
		}
	}
	
	@Override
	public void initGui() {
		int cx = this.width/2;
		int cy = this.height/2;
		int itemSize = 32;
		int space = 3;
		int totalWidth = 5 * itemSize + 4 * space;
		int upperLeftX = this.width/2 - totalWidth/2;
		for (int i = 0; i < this.potentialItems.length; i++) {
			for (int j = 0; j < this.potentialItems[i].length; j++) {
				this.buttonList.add(new ItemButton(this.nextButtonID++, this.potentialItems[i][j], upperLeftX + i * (itemSize + space), j * (itemSize + space), itemSize, itemSize, false));
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
    }
	
	@Override
	public void actionPerformed(GuiButton button) {
		if (button instanceof ItemButton) {
			ItemStack stack = new ItemStack(((ItemButton) button).getItem(), 1);
			this.commandEssence.setDead();
			IMessage packet = new DropItemPacketHandler.DropItemPacket(stack, this.position.getX(), this.position.getY(), this.position.getZ());
			RiskOfRain2Packets.NET.sendToServer(packet);
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
        return false;
    }
	
	public ItemRarity getRarity() {
		return ItemRarity.WHITE;
	}

	public void setCommandEssenceEntity(CommandEssenceEntity entity) {
		this.commandEssence = entity;
	}
}
