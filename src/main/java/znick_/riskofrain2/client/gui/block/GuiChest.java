package znick_.riskofrain2.client.gui.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.tile.RoR2ContainerChest;
import znick_.riskofrain2.tile.RoR2TileEntityChest;

public class GuiChest extends GuiContainer {

	private World world;
	private int x;
	private int y;
	private int z;
	
	private IInventory inventory;
	private RoR2TileEntityChest te;
	
	public GuiChest(World world, int x, int y, int z, IInventory playerInventory, RoR2TileEntityChest tileEntity) {
		super(new RoR2ContainerChest(playerInventory, tileEntity));
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.inventory = playerInventory;
		this.te = tileEntity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	    this.mc.getTextureManager().bindTexture(new ResourceLocation(RiskOfRain2.MODID + ":textures/gui/chest_gui.png"));
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	 @Override
	 protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
	    fontRendererObj.drawString(I18n.format(te.getInventoryName()), (xSize / 2) - (fontRendererObj.getStringWidth(I18n.format(te.getInventoryName())) / 2), 6, 4210752, false);
	    fontRendererObj.drawString(I18n.format(inventory.getInventoryName()), 8, ySize - 96 + 2, 4210752);
	 }

}
