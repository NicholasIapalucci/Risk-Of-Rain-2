package znick_.riskofrain2.api.mc;

import java.lang.reflect.Field;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.EnumDifficulty;

/**
 * Subclass of {@code FoodStats} to disable natural regeneration and replace it with the custom regeneration speed.
 * 
 * @author zNick_
 */
public class CustomRegenHandler extends FoodStats {
	
	private final Field prevField;
	private final Field timerField;
	private final Field exhaustionField;
	private final Field saturationField;
	private final Field levelField;
	
	private int prevFoodLevel;
	private int foodTimer;
	private int foodLevel;
	private float foodExhaustionLevel;
	private float foodSaturationLevel;
	
	public CustomRegenHandler() {
		try {
			this.prevField = FoodStats.class.getDeclaredField("prevFoodLevel");
			this.timerField = FoodStats.class.getDeclaredField("foodTimer");
			this.levelField = FoodStats.class.getDeclaredField("foodLevel");
			this.exhaustionField = FoodStats.class.getDeclaredField("foodExhaustionLevel");
			this.saturationField = FoodStats.class.getDeclaredField("foodSaturationLevel");
			
			this.prevField.setAccessible(true);
			this.timerField.setAccessible(true);
			this.levelField.setAccessible(true);
			this.exhaustionField.setAccessible(true);
			this.saturationField.setAccessible(true);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void updateFields() {
		try {		
			this.prevFoodLevel = prevField.getInt(this);
			this.foodTimer = timerField.getInt(this);
			this.foodLevel = levelField.getInt(this);
			this.foodExhaustionLevel = exhaustionField.getFloat(this);
			this.foodSaturationLevel = saturationField.getFloat(this);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void setFoodExhaustionLevel(float exhaustion) {
		try {this.exhaustionField.set(this, exhaustion);}
		catch(Exception e) {throw new RuntimeException(e);}
		this.updateFields();
	}
	
	private void setFoodLevelO(int foodLevel) {
		try {this.levelField.set(this, foodLevel);}
		catch(Exception e) {throw new RuntimeException(e);}
		this.updateFields();
	}
	
	private void setFoodTimer(int foodTimer) {
		try {this.timerField.set(this, foodTimer);}
		catch(Exception e) {throw new RuntimeException(e);}
		this.updateFields();
	}
	
	@Override
	public void onUpdate(EntityPlayer player) {
		this.updateFields();
		EnumDifficulty difficulty = player.worldObj.difficultySetting;
		
		//Handle hunger depletion
		this.prevFoodLevel = this.getFoodLevel();
		if (this.foodExhaustionLevel > 4.0F) {
			this.setFoodExhaustionLevel(this.foodExhaustionLevel - 4.0F);
			if (this.foodSaturationLevel > 0.0F) this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
			else if (difficulty != EnumDifficulty.PEACEFUL) this.setFoodLevelO(Math.max(this.getFoodLevel() - 1, 0));
		}

		//Natural regeneration
		boolean isRegenEnabled = player.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration");
		if (isRegenEnabled && this.getFoodLevel() >= 1 && player.shouldHeal()) {
			this.setFoodTimer(this.foodTimer + 1);
			if (this.foodTimer >= 80) {
				player.heal(1.0F); //HERE
				this.addExhaustion(3.0F);
				this.setFoodTimer(0);
			}
		}
		
		//Starvation damage
		else if (this.getFoodLevel() <= 0) {
			this.setFoodTimer(this.foodTimer + 1);
			if (this.foodTimer >= 80) {
				if (player.getHealth() > 10.0F || difficulty == EnumDifficulty.HARD || player.getHealth() > 1.0F && difficulty == EnumDifficulty.NORMAL) {
					player.attackEntityFrom(DamageSource.starve, 1.0F);
				}
				this.setFoodTimer(0);
			}
		} 
		else this.foodTimer = 0;
	}
}
