package znick_.riskofrain2.entity.elite.mobs.zombie;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2Mod;
import znick_.riskofrain2.entity.elite.EliteType;
import znick_.riskofrain2.entity.elite.RenderEliteEntity;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class RenderEliteZombie extends RenderZombie implements RenderEliteEntity {

	private final ResourceLocation texture;
	private final EliteType eliteType;

	public RenderEliteZombie(EliteType type) {
		this.eliteType = type;
		this.texture = RiskOfRain2Resources.get(RiskOfRain2Mod.MODID + ":textures/entity/mob/zombie/" + this.eliteType.toString().toLowerCase() + ".png");
	}
	
	@Override
	protected void func_147906_a(Entity entity, String name, double x, double y, double z, int dist) {
		this.renderEntityName(entity, this.renderManager, name, x, y, z, dist);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZombie zombie) {
		return this.texture;
	}

	@Override
	public EliteType getEliteTypeForRender() {
		return this.eliteType;
	}
}
