package znick_.riskofrain2.block.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import znick_.riskofrain2.api.mc.enums.Face;

public interface FacingBlock {

	public abstract Face getFacingDirection();
	public abstract String getSideTexture(BlockFace face);
	
	public default void setMetadataUponPlacing(World world, int x, int y, int z, EntityLivingBase placer) {
		byte b = 0;
        int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        switch(l) {
        case 0: b = 2; break;
        case 1: b = 5; break;
        case 2: b = 3; break;
        case 3: b = 4; break;
        default: b = 0;
        }

        world.setBlockMetadataWithNotify(x, y, z, 0, 3);
	}
	
}
