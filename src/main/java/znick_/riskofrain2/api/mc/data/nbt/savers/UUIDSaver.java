package znick_.riskofrain2.api.mc.data.nbt.savers;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.api.mc.data.nbt.SavableToNBT;

public class UUIDSaver extends Saver<UUID> {

	public UUIDSaver() {
		super();
	}
	
	private UUIDSaver(UUID object) {
		super(object);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt, String key) {
		nbt.setString(key, this.object.toString());
	}

	@Override
	public UUID readFromNBT(NBTTagCompound nbt, String key) {
		return UUID.fromString(nbt.getString(key));
	}

}
