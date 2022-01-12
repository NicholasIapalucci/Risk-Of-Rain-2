package znick_.riskofrain2.tile;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class RoR2TileEntityChest extends TileEntity implements IInventory, ISidedInventory {

	private ItemStack[] items = new ItemStack[1];
	private String customName = "Risk Of Rain 2 Chest";
	private World world = Minecraft.getMinecraft().theWorld;

	public int getSizeInventory() {
		return items.length;
	}

	public void addToSlot(int index, Item item, int amount) {
		if (this.items[index] != null) {
			this.items[index].stackSize += amount;
		} else {
			this.items[index] = new ItemStack(item, amount);
		}
	}

	private boolean slotFull(int slot) {
		if (this.items[slot] != null) {
			return this.items[slot].stackSize >= 64;
		}

		return false;
	}

	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory()) {
			return null;
		}
		return this.items[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).stackSize <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).stackSize <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int slot) {
		if (items[slot] != null) {
			ItemStack itemstack = items[slot];
			items[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory()) {
			return;
		}

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		if (stack != null && stack.stackSize == 0) {
			stack = null;
		}

		this.items[index] = stack;
		this.markDirty();
	}

	public String getInventoryName() {
		return this.customName;
	}

	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		items = new ItemStack[getSizeInventory()];

		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound comp = list.getCompoundTagAt(i);
			int j = comp.getByte("Slot") & 255;
			if (j >= 0 && j < items.length) {
				items[j] = ItemStack.loadItemStackFromNBT(comp);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < items.length; ++i) {
			if (items[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setByte("Slot", (byte) i);
				items[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}

		nbt.setTag("Items", list);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory() {

	}

	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	public void setCustomName(String name) {
		this.customName = name;

	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return this.isItemValidForSlot(slot, stack);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	public void setItem(Item item) {
		this.items[0] = new ItemStack(item, 1);
	}

}
