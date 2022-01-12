package znick_.riskofrain2.client.render.entity.elite;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.RiskOfRain2;
import znick_.riskofrain2.api.ror.enemy.EliteType;

public class RenderEliteZombie extends RenderBiped {
	
	private EliteType eliteType;
	private ResourceLocation texture;
	
	private ModelBiped field_82434_o;
	protected ModelBiped field_82437_k;
	protected ModelBiped field_82435_l;
	protected ModelBiped field_82436_m;
	protected ModelBiped field_82433_n;
	
	private int field_82431_q = 1;

	public RenderEliteZombie(EliteType eliteType) {
		super(new ModelZombie(), 0.5F, 1.0F);
		this.field_82434_o = this.modelBipedMain;
		this.eliteType = eliteType;
		
		this.texture = new ResourceLocation(RiskOfRain2.MODID + ":textures/entity/mob/elite/zombie/" + this.eliteType.toString().toLowerCase() + ".png");
	}

	protected void func_82421_b() {
		this.field_82423_g = new ModelZombie(1.0F, true);
		this.field_82425_h = new ModelZombie(0.5F, true);
		this.field_82437_k = this.field_82423_g;
		this.field_82435_l = this.field_82425_h;
		this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
		this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
	}


	protected int shouldRenderPass(EntityZombie zombie, int a, float b) {
		this.func_82427_a(zombie);
		return super.shouldRenderPass((EntityLiving) zombie, a, b);
	}

	public void doRender(EntityZombie zombie, double x, double y, double z, float a, float b) {
		this.func_82427_a(zombie);
		super.doRender((EntityLiving) zombie, x, y, z, a, b);
	}

	protected ResourceLocation getEntityTexture(EntityZombie zombie) {
		return new ResourceLocation(RiskOfRain2.MODID + ":textures/entity/mob/zombie/" + this.eliteType.toString().toLowerCase() + ".png");
	}

	protected void renderEquippedItems(EntityZombie zombie, float f1) {
		this.func_82427_a(zombie);
		super.renderEquippedItems((EntityLiving) zombie, f1);
	}

	private void func_82427_a(EntityZombie zombie) {
		this.mainModel = this.field_82434_o;
		this.field_82423_g = this.field_82437_k;
		this.field_82425_h = this.field_82435_l;

		this.modelBipedMain = (ModelBiped) this.mainModel;
	}


	protected void rotateCorpse(EntityZombie zombie, float a, float b, float c) {
		super.rotateCorpse(zombie, a, b, c);
	}

	protected void renderEquippedItems(EntityLiving zombie, float a) {
		this.renderEquippedItems((EntityZombie) zombie, a);
	}


	protected ResourceLocation getEntityTexture(EntityLiving zombie) {
		return this.getEntityTexture((EntityZombie) zombie);
	}

	public void doRender(EntityLiving zombie, double x, double y, double z, float a, float b) {
		this.doRender((EntityZombie) zombie, x, y, z, a, b);
	}


	protected int shouldRenderPass(EntityLiving zombie, int x, float a) {
		return this.shouldRenderPass((EntityZombie) zombie, x, a);
	}


	protected int shouldRenderPass(EntityLivingBase zombie, int x, float a) {
		return this.shouldRenderPass((EntityZombie) zombie, x, a);
	}

	protected void renderEquippedItems(EntityLivingBase zombie, float a) {
		this.renderEquippedItems((EntityZombie) zombie, a);
	}

	protected void rotateCorpse(EntityLivingBase zombie, float a, float b, float c) {
		this.rotateCorpse((EntityZombie) zombie, a, b, c);
	}

	public void doRender(EntityLivingBase zombie, double x, double y, double z, float a, float b) {
		this.doRender((EntityZombie) zombie, x, y, z, a, b);
	}

	protected ResourceLocation getEntityTexture(Entity zombie) {
		return this.getEntityTexture((EntityZombie) zombie);
	}

	public void doRender(Entity zombie, double x, double y, double z, float a, float b) {
		this.doRender((EntityZombie) zombie, x, y, z, a, b);
	}
}
