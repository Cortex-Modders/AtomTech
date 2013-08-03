package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public abstract class TilePoweredBase extends TileEntity implements IAtomicPower
{
    protected int powerLevel = 0;
    //maybe idk about this..
    protected int maxInputPower = 10;
    protected final int maxPower;
    
    private Vec3 sourceLoc;
    
    protected TilePoweredBase(int parMax)
    {
        this.maxPower = parMax;
    }
    
    public TilePoweredBase()
    {
        this(100);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.powerLevel = tag.getInteger("power");
        this.sourceLoc = Vec3.createVectorHelper(tag.getDouble("xv"), tag.getDouble("yv"), tag.getDouble("zv"));
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("power", powerLevel);
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
    		IAtomicPower source = (IAtomicPower) worldObj.getBlockTileEntity(x, y, z);
    		source.setPower(addPower(source.getPower()));
    	}
    }

    @Override
    public void sendPower(int x, int y, int z)
    {
    	
    }

    @Override
    public int getPower()
    {
        return powerLevel;
    }

    /**
     * Adds power to block. Returns remainder left if too full.
     * 
     */
    @Override
    public int addPower(int power)
    {
        int remainder = 0;
        
        powerLevel += power;
        while(powerLevel > maxPower)
        {
        	powerLevel--;
        	remainder++;
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
	public void setPower(int power)
	{
		powerLevel = power;
	}
}