package znick_.riskofrain2.client.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import znick_.riskofrain2.api.mc.Face;

public class RenderHelper {

	private static final Tessellator T = Tessellator.instance;
	
	public static void renderRectangularPrism(double x, double y, double z, double length, double width, double height, Color color) {
		for (Face face : Face.values()) renderFace(face, x, y, z, length, width, height, color);
	}
	
	public static void renderCube(double x, double y, double z, double side, Color color) {
		renderRectangularPrism(x, y, z, side, side, side, color);
	}
	
	public static void renderCube(double x, double y, double z, double side) {
		renderCube(x, y, z, side, Color.WHITE);
	}
	
	public static void renderFace(Face face, double x, double y, double z, double l, double w, double h, Color color) {
		//GL11.glEnable(GL11.GL_BLEND);
		T.setColorRGBA(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
		T.startDrawingQuads();
		
		switch(face) {
		
		case POSITIVE_X: 
			T.setNormal(1, 0, 0);
			T.addVertexWithUV(x + l, y + h, z + w, 1, 1);
			T.addVertexWithUV(x + l, y,     z + w, 0, 1);
			T.addVertexWithUV(x + l, y,     z,     0, 0);
			T.addVertexWithUV(x + l, y + h, z,     1, 0);
			break;
			
		case NEGATIVE_X:
			T.setNormal(-1, 0, 0);
			T.addVertexWithUV(x, y + h, z,     1, 0);
			T.addVertexWithUV(x, y,     z,     0, 0);
			T.addVertexWithUV(x, y,     z + w, 0, 1);
			T.addVertexWithUV(x, y + h, z + w, 1, 1);
			break;
			
		case POSITIVE_Y:
			T.setNormal(0, 1, 0); 
			T.addVertexWithUV(x + l, y + h, z,     1, 0);
			T.addVertexWithUV(x    , y + h, z,     0, 0);
			T.addVertexWithUV(x    , y + h, z + w, 0, 1);
			T.addVertexWithUV(x + l, y + h, z + w, 1, 1);
			break;
			
		case NEGATIVE_Y:
			T.setNormal(0, -1, 0); 
			T.addVertexWithUV(x + l, y, z + w, 1, 1);
			T.addVertexWithUV(x    , y, z + w, 0, 1);
			T.addVertexWithUV(x    , y, z,     0, 0);
			T.addVertexWithUV(x + l, y, z,     1, 0);
			break;
			
		case POSITIVE_Z:
			T.setNormal(0, 0, 1);
			T.addVertexWithUV(x + l, y + h, z + w, 1, 1);
			T.addVertexWithUV(x    , y + h, z + w, 0, 1);
			T.addVertexWithUV(x    , y,     z + w, 0, 0);
			T.addVertexWithUV(x + l, y,     z + w, 1, 0);
			break;
			
		case NEGATIVE_Z:
			T.setNormal(0, 0, -1);
			T.addVertexWithUV(x + l, y,     z, 1, 1);
			T.addVertexWithUV(x    , y,     z, 0, 1);
			T.addVertexWithUV(x    , y + h, z, 0, 0);
			T.addVertexWithUV(x + l, y + h, z, 1, 0);
			break;
		}
		
		T.draw();
	}
	
	public static void renderFace(Face face, double x, double y, double z, double l, double w, double h) {
		renderFace(face, x, y, z, l, w, h, Color.WHITE);
	}
	
	public static void drawRect2D(int x, int y, int w, int h) {
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		t.addVertexWithUV(x,     y,     0, 0, 0);
		t.addVertexWithUV(x,     y + h, 0, 0, 1);
		t.addVertexWithUV(x + w, y + h, 0, 1, 1);
		t.addVertexWithUV(x + w, y,     0, 1, 0);
		t.draw();
	}
}
