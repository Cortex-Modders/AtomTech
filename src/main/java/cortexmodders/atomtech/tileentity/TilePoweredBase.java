package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public abstract class TilePoweredBase extends TileEntity implements IAtomicPower
{
    protected float powerLevel = 0.0F;
    //maybe idk about this..
    protected float maxInputPower = 10.0F;
    protected final float maxPower;
    
    public int powerUsedTick = 0;
    
    protected Vec3 sourceLoc = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
    
    protected TilePoweredBase(float parMax)
    {
        this.maxPower = parMax;
    }
    
    public TilePoweredBase()
    {
        this(100.0F);
    }
    
    @Override
	public void updateEntity()
	{
    	sendPower();
    	resetSourceLoc();
	}
    
    public void sendPower()
    {
    	if(canSendPower() && powerLevel > 0)
		{
			sendPower(xCoord + 1, yCoord, zCoord);
			sendPower(xCoord - 1, yCoord, zCoord);
			sendPower(xCoord, yCoord + 1, zCoord);
			sendPower(xCoord, yCoord - 1, zCoord);
			sendPower(xCoord, yCoord, zCoord + 1);
			sendPower(xCoord, yCoord, zCoord - 1);
		}
    }
    
    public void resetSourceLoc()
    {
    	sourceLoc = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.powerLevel = tag.getFloat("power");
        this.sourceLoc = Vec3.createVectorHelper(tag.getDouble("xv"), tag.getDouble("yv"), tag.getDouble("zv"));
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setFloat("power", powerLevel);
        if(sourceLoc != null)
        {
            tag.setDouble("xv", sourceLoc.xCoord);
            tag.setDouble("yv", sourceLoc.yCoord);
            tag.setDouble("zv", sourceLoc.zCoord);
        }
    }
    
    // start IAtomicPower methods
    
    @Override
    public void onPowerRecieved(Vec3 sourceLoc)
    {
    	int x = (int) sourceLoc.xCoord;
    	int y = (int) sourceLoc.yCoord;
    	int z = (int) sourceLoc.zCoord;
    	if(worldObj.getBlockTileEntity(x, y, z) != null && worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
    	{
    		float carryOver = addPower(((IAtomicPower)worldObj.getBlockTileEntity(x, y, z)).getPower());
    		((IAtomicPower)worldObj.getBlockTileEntity(x, y, z)).setPower(carryOver);
    		this.sourceLoc = sourceLoc;
    	}
    }

    @Override
    public void sendPower(int x, int y, int z)
    {
    	if((sourceLoc.xCoord != x || sourceLoc.yCoord != y || sourceLoc.zCoord != z) && powerLevel > 0)
    	{
    		Vec3 powerSource = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
    		if(worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
    		{
    			if(((IAtomicPower)worldObj.getBlockTileEntity(x, y, z)).canRecievePower())
    			{
    				((IAtomicPower)worldObj.getBlockTileEntity(x, y, z)).onPowerRecieved(powerSource);
    			}
    		}
    	}
    }

    @Override
    public float getPower()
    {
        return powerLevel;
    }

    /**
     * Adds power to block. Returns remainder left if too full.
     * 
     */
    @Override
    public float addPower(float power)
    {
        float remainder = 0;
        
    	if(power > maxInputPower)
    	{
    		remainder = power - maxInputPower;
    		power = maxInputPower;
    	}
    	
        powerLevel += power;
        
        if(powerLevel > maxPower)
        {
        	remainder += powerLevel - maxPower;
        	powerLevel = maxPower;
        }
        
        if(powerLevel < 0)
        {
        	powerLevel = 0;
        }
        
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
	public void setPower(float power)
	{
		powerLevel = power;
	}
	
	public float getMaxPower()
	{
		return maxPower;
	}
	
	public float getPowerPercentage()
	{
		return powerLevel / maxPower;
	}
}