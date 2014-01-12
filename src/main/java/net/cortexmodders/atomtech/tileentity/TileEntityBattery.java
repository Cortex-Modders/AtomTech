package net.cortexmodders.atomtech.tileentity;

public class TileEntityBattery extends TilePoweredBase
{
    
    @Override
    public boolean canRecievePower()
    {
        return true;
    }
    
    @Override
    public boolean canSendPower()
    {
        return true;
    }
    
    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
            System.out.println(this.powerLevel);
        super.updateEntity();
    }
}
