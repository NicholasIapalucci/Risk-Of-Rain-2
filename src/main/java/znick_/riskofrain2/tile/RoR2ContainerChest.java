package znick_.riskofrain2.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class RoR2ContainerChest extends Container {
	
	private RoR2TileEntityChest te;

	private int slotID = 0;

	public RoR2ContainerChest(IInventory inventory, RoR2TileEntityChest te) {
		this.te = te;
		this.addSlotToContainer(new Slot(te, 0, 80, 35));

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(inventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 142));
		}
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < 9) {
	            if (!this.mergeItemStack(current, 2, 38, true)) {
	                return null;
	            }
	        } else {
	            if (!this.mergeItemStack(current, 0, 1, false)) {
	                return null;
	            }
	        }

	        if (current.stackSize == 0) {
	            slot.putStack((ItemStack) null);
	        } else {
	            slot.onSlotChanged();
	        }

	        if (current.stackSize == previous.stackSize) {
	            return null;
	        }
	        
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}
	

}
