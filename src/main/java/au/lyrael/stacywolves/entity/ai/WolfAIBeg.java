package au.lyrael.stacywolves.entity.ai;


import au.lyrael.stacywolves.entity.wolf.EntityWolfBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WolfAIBeg extends EntityAIBase {
    private EntityWolfBase theWolf;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int durationRemaining;

    public WolfAIBeg(EntityWolfBase wolf, float range) {
        this.theWolf = wolf;
        this.worldObject = wolf.worldObj;
        this.minPlayerDistance = range;
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, (double) this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return !this.thePlayer.isEntityAlive() ? false : (this.theWolf.getDistanceSqToEntity(this.thePlayer) > (double) (this.minPlayerDistance * this.minPlayerDistance) ? false : this.durationRemaining > 0 && this.hasPlayerGotBoneInHand(this.thePlayer));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.theWolf.setBegging(true);
        this.durationRemaining = 40 + this.theWolf.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.theWolf.setBegging(false);
        this.thePlayer = null;
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + (double) this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, (float) this.theWolf.getVerticalFaceSpeed());
        --this.durationRemaining;
    }

    /**
     * Gets if the Player has the Bone in the hand.
     */
    private boolean hasPlayerGotBoneInHand(EntityPlayer player) {
        ItemStack itemstack = player.inventory.getCurrentItem();
        return itemstack == null ? false : (!this.theWolf.isTamed() && this.theWolf.isWolfBreedingItem(itemstack));
    }
}