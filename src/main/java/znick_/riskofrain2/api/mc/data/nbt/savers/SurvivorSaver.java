package znick_.riskofrain2.api.mc.data.nbt.savers;

import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.api.ror.survivor.Survivor;

public class SurvivorSaver extends Saver<Survivor> {

	public SurvivorSaver() {
		super();
	}
	
	public SurvivorSaver(Survivor survivor) {
		super(survivor);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt, String key) {
		nbt.setInteger(key, this.object.getUniqueID());
	}

	@Override
	public Survivor readFromNBT(NBTTagCompound nbt, String key) {
		return Survivor.fromID(nbt.getInteger(key));
	}

}
