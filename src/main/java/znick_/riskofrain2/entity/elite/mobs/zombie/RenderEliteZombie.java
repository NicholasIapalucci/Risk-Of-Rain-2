package znick_.riskofrain2.entity.elite.mobs.zombie;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.entity.elite.EliteType;

public class RenderEliteZombie extends RenderZombie {
	
	private final ResourceLocation texture;
	private final EliteType eliteType;
	
	public RenderEliteZombie(EliteType type) {
		this.eliteType = type;
		this.texture = new ResourceLocation(RiskOfRain2Mod.MODID + ":textures/entity/mob/zombie/" + this.eliteType.toString().toLowerCase() + ".png");
	}
	
	@Override 
	protected ResourceLocation getEntityTexture(EntityZombie zombie) {
        return this.texture;
    }
}
