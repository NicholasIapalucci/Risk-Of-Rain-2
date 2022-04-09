package znick_.riskofrain2.api.ror.artifact.list.vengeance;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVengeantPlayer extends RenderBiped {

	public RenderVengeantPlayer() {
		super(new ModelBiped(0.0F), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((AbstractClientPlayer) ((VengeantPlayer) entity).getPlayer()).getLocationSkin();
	}

}
