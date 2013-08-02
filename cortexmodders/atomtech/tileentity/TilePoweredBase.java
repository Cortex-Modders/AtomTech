package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public abstract class TilePoweredBase extends TileEntity implements IAtomicPower {

    private int powerLevel = 0;
    //maybe idk about this..
    private int maxInputPower = 10;
    private final int maxPower;
    
    private Vec3 sourceLoc = null;
    
    public TilePoweredBase(int parMax) {
        this.maxPower = parMax;
    }
    
    public TilePoweredBase() {
        // TODO: Figure out power units.
        this.maxPower = 10;
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
    public void onPowerRecieved(Vec3 sourceLoc) {

    }

    @Override
    public void sendPower(int x, int y, int z) {
        
    }

    @Override
    public int getPower() {

        return powerLevel;
    }

    /**
     * Adds power to block. Returns remainder left if too full.
     * 
     */
    @Override
    public int addPower(int power) {
        int remainder = 0;
        
        if(power <= 0)
            return 0;
        
        if(this.powerLevel + power > maxPower) {
            this.powerLevel = maxPower;
            remainder = maxPower - power;
        }
        else
            this.powerLevel += power;
        
        return remainder;
    }

    @Override
    public boolean canRecievePower() {
        return false;
    }

    @Override
    public boolean canSendPower() {
        return false;
    }

}
