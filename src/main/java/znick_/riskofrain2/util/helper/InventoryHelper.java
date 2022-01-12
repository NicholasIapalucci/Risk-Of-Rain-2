package znick_.riskofrain2.util.helper;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import znick_.riskofrain2.api.ror.items.equipment.RiskOfRain2Equipment;

public class InventoryHelper {

	public static int amountOfItems(EntityPlayer player, Item item) {
		int amount = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null) {
				if (stackInSlot.getItem() == item) {
					amount += stackInSlot.stackSize;
				}
			}
		}
		return amount;
	}

	public static void removeAll(EntityPlayer player, Item item) {
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null) {
				if (stackInSlot.getItem() == item) {
					player.inventory.setInventorySlotContents(i, null);
				}
			}
		}
	}
	
	public static void removeAmount(EntityPlayer player, Item item, int amount) {
		int amountRemoved = 0;
		int amountLeft = amount;
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null) {
				if (stackInSlot.getItem() == item) {
					System.out.println(amountLeft);
					if (stackInSlot.stackSize > amountLeft) {
						player.inventory.setInventorySlotContents(i, new ItemStack(stackInSlot.getItem(), stackInSlot.stackSize - amountLeft));
						return;
					}
					
					if (stackInSlot.stackSize == amountLeft) {
						player.inventory.setInventorySlotContents(i, null);
						amountLeft = 0;
					}
					
					if (stackInSlot.stackSize < amountLeft) {
						player.inventory.setInventorySlotContents(i, null);
						amountLeft -= stackInSlot.stackSize;
					}
					
					if (amountLeft <= 0) return;
				}
			}
		}
	}
	
	public static int getEquipmentSlot(EntityPlayer player) {
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null && stackInSlot.getItem() instanceof RiskOfRain2Equipment) return i;
		}
		return -1;
	}
	
	public static RiskOfRain2Equipment getCurrentEquipment(EntityPlayer player) {
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null && stackInSlot.getItem() instanceof RiskOfRain2Equipment) return (RiskOfRain2Equipment) stackInSlot.getItem();
		}
		return null;
	}
	
	public static void dropItemInWorld(World world, double x, double y, double z, ItemStack stack) {
		if (world.isRemote) return;
		
		float f0 = new Random().nextFloat() * 0.8F + 0.1F;
        float f1 = new Random().nextFloat() * 0.8F + 0.1F;
        float f2 = new Random().nextFloat() * 0.8F + 0.1F;

        while (stack.stackSize > 0) {
            int i = new Random().nextInt(21) + 10;
            if (i > stack.stackSize) i = stack.stackSize;
            stack.stackSize -= i;
            
            EntityItem entityitem = new EntityItem(world, x + (double) f0, y + (double) f1, z + (double)f2, new ItemStack(stack.getItem(), i));
            if (stack.hasTagCompound()) entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
            
            float f3 = 0.05F;
            
            entityitem.motionX = new Random().nextGaussian() * (double) f3;
            entityitem.motionY = new Random().nextGaussian() * (double) f3 + 0.20000000298023224D; //I have no idea what this number is; Taken from vanilla code
            entityitem.motionZ = new Random().nextGaussian() * (double) f3;
            
            entityitem.delayBeforeCanPickup = 20;
            world.spawnEntityInWorld(entityitem);
        }
	}
	
	public static int getEquipmentAmount(EntityPlayer player) {
		int amount = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack stackInSlot = player.inventory.getStackInSlot(i);
			if (stackInSlot != null && stackInSlot.getItem() instanceof RiskOfRain2Equipment) amount += stackInSlot.stackSize;
		}
		return amount;
	}
	
}
