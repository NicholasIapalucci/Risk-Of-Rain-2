package znick_.riskofrain2.block.printer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**{@code TileEntitySpecialRenderer} subclass used to render the 3D Printer block.*/
public class Render3DPrinter extends TileEntitySpecialRenderer {

	/**The render ID to use for the 3D Printer block*/
	public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
	/**The tessellator instance to render with*/
	private final Tessellator tessellator = Tessellator.instance;
	/**The angle of the spinning entityItem to be rendered at at any moment in time*/
	private int angle = 0;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		TileEntity3DPrinter tileEntity = (TileEntity3DPrinter) tile;
		this.renderItem(tileEntity.getItemStack(), x, y, z);
		//TODO: Update 3D Printer to render base
	}
	
	/**
	 * Renders the floating item above the printer.
	 * 
	 * @param stack The {@code ItemStack to render}
	 * @param x The x-coordinate to render it at
	 * @param y The y-coordinate to render it at
	 * @param z The z-coordinate to render it at
	 */
	private void renderItem(ItemStack stack, double x, double y, double z) {
		
		//Save the state
		GL11.glPushMatrix();
		RenderItem.renderInFrame = true;
		
		//Create the entity to render
		EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, stack);
		entityItem.hoverStart = 0;
		
		//Apply transformations
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1F, (float) z + 0.5f);
		GL11.glRotatef(angle++, 0, 1, 0);
		GL11.glScaled(1.75, 1.75, 1.75);
		
		//Render the item
		RenderManager.instance.renderEntityWithPosYaw(entityItem, 0, 0, 0, 0, 0);
		
		//Restore the state
		RenderItem.renderInFrame = false;
		GL11.glPopMatrix();
	}

	
}
