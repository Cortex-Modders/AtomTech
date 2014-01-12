package net.cortexmodders.atomtech.tileentity;

import net.cortexmodders.atomtech.power.IAtomicPower;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public abstract class TilePoweredBase extends TileEntity implements IAtomicPower
{
    
    protected float powerLevel = 0.0F;
    // maybe idk about this..
    protected float maxInputPower = 10.0F;
    protected final float maxPower;
    
    public int powerUsedTick = 0;
    
    protected Vec3 sourceLoc = Vec3.createVectorHelper(this.xCoord, this.yCoord, this.zCoord);
    
    public TilePoweredBase()
    {
        this(100.0F);
    }
    
    protected TilePoweredBase(final float parMax)
    {
        this.maxPower = parMax;
    }
    
    /**
     * Adds power to block. Returns remainder left if too full.
     * 
     */
    @Override
    public float addPower(float power)
    {
        float remainder = 0;
        
        if (power > this.maxInputPower)
        {
            remainder = power - this.maxInputPower;
            power = this.maxInputPower;
        }
        
        this.powerLevel += power;
        
        if (this.powerLevel > this.maxPower)
        {
            remainder += this.powerLevel - this.maxPower;
            this.powerLevel = this.maxPower;
        }
        
        if (this.powerLevel < 0)
            this.powerLevel = 0;
        
        return remainder;
    }
    
    @Override
    public boolean canRecievePower()
    {
        return false;
    }
    
    @Override
    public boolean canSendPower()
    {
        return false;
    }
    
    @Override
    public float getMaxPower()
    {
        return this.maxPower;
    }
    
    @Override
    public float getPower()
    {
        return this.powerLevel;
    }
    
    // start IAtomicPower methods
    
    @Override
    public float getPowerPercentage()
    {
        return this.powerLevel / this.maxPower;
    }
    
    @Override
    public void onPowerRecieved(final Vec3 sourceLoc)
    {
        int x = (int) sourceLoc.xCoord;
        int y = (int) sourceLoc.yCoord;
        int z = (int) sourceLoc.zCoord;
        if (this.worldObj.getBlockTileEntity(x, y, z) != null && this.worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
        {
            float carryOver = this.addPower(((IAtomicPower) this.worldObj.getBlockTileEntity(x, y, z)).getPower());
            ((IAtomicPower) this.worldObj.getBlockTileEntity(x, y, z)).setPower(carryOver);
            this.sourceLoc = sourceLoc;
        }
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.powerLevel = tag.getFloat("power");
        this.sourceLoc = Vec3.createVectorHelper(tag.getDouble("xv"), tag.getDouble("yv"), tag.getDouble("zv"));
    }
    
    public void resetSourceLoc()
    {
        this.sourceLoc = Vec3.createVectorHelper(this.xCoord, this.yCoord, this.zCoord);
    }
    
    public void sendPower()
    {
        if (this.canSendPower() && this.powerLevel > 0)
        {
            this.sendPower(this.xCoord + 1, this.yCoord, this.zCoord);
            this.sendPower(this.xCoord - 1, this.yCoord, this.zCoord);
            this.sendPower(this.xCoord, this.yCoord + 1, this.zCoord);
            this.sendPower(this.xCoord, this.yCoord - 1, this.zCoord);
            this.sendPower(this.xCoord, this.yCoord, this.zCoord + 1);
            this.sendPower(this.xCoord, this.yCoord, this.zCoord - 1);
        }
    }
    
    @Override
    public void sendPower(final int x, final int y, final int z)
    {
        if ((this.sourceLoc.xCoord != x || this.sourceLoc.yCoord != y || this.sourceLoc.zCoord != z) && this.powerLevel > 0)
        {
            Vec3 powerSource = Vec3.createVectorHelper(this.xCoord, this.yCoord, this.zCoord);
            if (this.worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
                if (((IAtomicPower) this.worldObj.getBlockTileEntity(x, y, z)).canRecievePower())
                    ((IAtomicPower) this.worldObj.getBlockTileEntity(x, y, z)).onPowerRecieved(powerSource);
        }
    }
    
    @Override
    public void setPower(final float power)
    {
        this.powerLevel = power;
    }
    
    @Override
    public void updateEntity()
    {
        this.sendPower();
        this.resetSourceLoc();
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setFloat("power", this.powerLevel);
        if (this.sourceLoc != null)
        {
            tag.setDouble("xv", this.sourceLoc.xCoord);
            tag.setDouble("yv", this.sourceLoc.yCoord);
            tag.setDouble("zv", this.sourceLoc.zCoord);
        }
    }
}
