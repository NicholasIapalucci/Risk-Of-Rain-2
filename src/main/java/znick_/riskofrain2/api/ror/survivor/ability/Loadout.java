package znick_.riskofrain2.api.ror.survivor.ability;

import net.minecraft.nbt.NBTTagCompound;
import znick_.riskofrain2.api.mc.data.nbt.SavableToNBT;
import znick_.riskofrain2.util.helper.MathHelper;

public class Loadout implements SavableToNBT<Loadout> {

	private Ability primary;
	private Ability secondary;
	private Ability utility;
	private Ability special;
	private final int uniqueID;
	
	public Loadout(Ability primary, Ability secondary, Ability utility, Ability special) {
		this.primary = primary;
		this.secondary = secondary;
		this.utility = utility;
		this.special = special;
		this.uniqueID = 0;
	}
	
	private Loadout(int id) {
		this.uniqueID = id;
		int[] ids = MathHelper.primeFactorize(id);
		
		this.primary = Ability.fromID(ids[0]);
		this.secondary = Ability.fromID(ids[1]);
		this.utility = Ability.fromID(ids[2]);
		this.special = Ability.fromID(ids[3]);
	}
	
	public Loadout() {
		this.primary = null;
		this.secondary = null;
		this.utility = null;
		this.special = null;
		this.uniqueID = -1;
	}
	
	private int generateUniqueID() {
		return  this.primary.getUniqueID() * 
				this.secondary.getUniqueID() *
				this.utility.getUniqueID() * 
				this.special.getUniqueID();
	}
	
	public Ability getAbility(AbilityType type) {
		switch(type) {
		case PRIMARY: return this.primary;
		case SECONDARY: return this.secondary;
		case UTILITY: return this.utility;
		case SPECIAL: return this.special;
		default: throw new IllegalArgumentException("Unsupported ability type: " + type);
		}
	}
	
	public int getUniqueID() {
		return this.uniqueID;
	}
	
	public static Loadout fromID(int id) {
		int[] primeFactors = MathHelper.primeFactorize(id);
		if (primeFactors.length != 4) return null;
		return new Loadout(id);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt, String key) {
		nbt.setInteger(key, this.getUniqueID());
	}

	@Override
	public Loadout readFromNBT(NBTTagCompound nbt, String key) {
		return fromID(nbt.getInteger(key));
	}

}
