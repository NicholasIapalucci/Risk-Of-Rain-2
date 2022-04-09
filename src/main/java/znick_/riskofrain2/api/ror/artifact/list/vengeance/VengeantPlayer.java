package znick_.riskofrain2.api.ror.artifact.list.vengeance;

import java.util.Map;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import znick_.riskofrain2.api.mc.data.EntityData;
import znick_.riskofrain2.api.mc.data.PlayerData;
import znick_.riskofrain2.api.ror.survivor.Survivor;
import znick_.riskofrain2.item.ror.RiskOfRain2Item;

public class VengeantPlayer extends EntityMob implements IEntityAdditionalSpawnData {
	
	private EntityData data;
	private PlayerData player;
	
	public VengeantPlayer(World world) {
		super(world);
		this.data = (EntityData) EntityData.get(this);
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.setSize(0.6F, 1.8F);
	}
	
	/**
	 * Creates a new {@code VengeantPlayer}. The entity will inherit all of the items of the
	 * player paremeter. 
	 * 
	 * @param player The player to copy
	 * @param x The x-coordinate to spawn at
	 * @param y The y-coordinate to spawn at
	 * @param z The z-coordinate to spawn at
	 */
	public VengeantPlayer(PlayerData player, double x, double y, double z) {
		this(player.getWorld());
		this.setPosition(x, y, z);
		this.player = player;
		this.setCustomNameTag("Vengeant " + player.getEntity().getDisplayName());
		
		// Give the vengeant player the same armor
		for (int i = 0; i < 4; i++) {
			this.setCurrentItemOrArmor(i + 1, this.player.getEntity().getCurrentArmor(i));
		}
		
		// Give the vengeant player all of the players items
		for (Map.Entry<RiskOfRain2Item, Integer> entry : player.getRiskOfRain2Items().entrySet()) {
			this.data.addItem(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }
	
	@Override
	protected boolean isAIEnabled() {
        return true;
	}

	@Override
	public void writeSpawnData(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.player.getEntity().getDisplayName());
	}

	@Override
	public void readSpawnData(ByteBuf buf) {
		this.player = PlayerData.get(this.worldObj.getPlayerEntityByName(ByteBufUtils.readUTF8String(buf)));
	}
	
	public EntityPlayer getPlayer() {
		return this.player.getEntity();
	}

}
