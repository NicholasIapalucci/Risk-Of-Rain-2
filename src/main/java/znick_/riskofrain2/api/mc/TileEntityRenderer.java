package znick_.riskofrain2.api.mc;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class TileEntityRenderer extends TileEntitySpecialRenderer {

	protected static final Face NEGATIVE_X = new Face();
	protected static final Face POSITIVE_X = new Face();
	protected static final Face NEGATIVE_Y = new Face();
	protected static final Face POSITIVE_Y = new Face();
	protected static final Face NEGATIVE_Z = new Face();
	protected static final Face POSITIVE_Z = new Face();
	
	private static final Tessellator T = Tessellator.instance;
	
	protected void renderRectPrism(String texture, int x, int y, int w, int h) {
		
	}
	
	protected static class Face {}
}
