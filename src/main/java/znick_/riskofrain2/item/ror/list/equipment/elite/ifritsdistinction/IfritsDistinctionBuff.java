package znick_.riskofrain2.item.ror.list.equipment.elite.ifritsdistinction;

import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import znick_.riskofrain2.api.mc.data.AbstractEntityData;
import znick_.riskofrain2.api.ror.buff.Buff;
import znick_.riskofrain2.item.RiskOfRain2Items;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;
import znick_.riskofrain2.util.file.RiskOfRain2Resources;

public class IfritsDistinctionBuff extends Buff {

	public IfritsDistinctionBuff(int itemCount) {
		super(itemCount);
	}

	@Override
	public ResourceLocation getIconTexture() {
		return RiskOfRain2Resources.get(RiskOfRain2Resources.BUFFS + "ifrits_distinction.png");
	}

	@Override
	public void applyEffect(AbstractEntityData entity) {
		entity.getEntity().addPotionEffect(new PotionEffect(Potion.fireResistance.id, Integer.MAX_VALUE, 0));
		entity.getEntity().setFire(Integer.MAX_VALUE);
		int x = (int) entity.getEntity().posX;
		int y = (int) entity.getEntity().posY;
		int z = (int) entity.getEntity().posZ;
		if (!entity.getWorld().isRemote && entity.getWorld().getBlock(x, y, z) == Blocks.air) entity.getWorld().setBlock(x, y, z, Blocks.fire);
	}
	
	@Override
	public boolean shouldRepeat() {
		return true;
	}

	@Override
	public void removeEffect(AbstractEntityData entity) {
		entity.getEntity().removePotionEffect(Potion.fireResistance.id);
		entity.getEntity().extinguish();
	}
	
	@Override
	public RiskOfRain2Item[] getItems() {
		return new RiskOfRain2Item[] {RiskOfRain2Items.IFRITS_DISTINCTION};
	}

}
