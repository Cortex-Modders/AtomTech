package net.cortexmodders.atomtech.tileentity;

import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;

public class TileEntitySmallBattery extends AbstractTileElectricity implements IEnergyInterface, IEnergyContainer
{
    
    public TileEntitySmallBattery()
    {
        super();
        maxEnergy = 100;
    }
    
    @Override
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
            System.out.println(this.energy);
        super.updateEntity();
    }
    
    // UE Methods
    
    @Override
    public long getEnergy(ForgeDirection from)
    {
        return energy;
    }
    
    @Override
    public long getEnergyCapacity(ForgeDirection from)
    {
        return maxEnergy;
    }
    
    @Override
    public void setEnergy(ForgeDirection from, long energy)
    {
        this.energy = energy;
    }
    
    @Override
    public boolean canConnect(ForgeDirection direction)
    {
        int meta = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        ForgeDirection orientation = ForgeDirection.getOrientation(meta);
        
        if (orientation == direction)
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public long onExtractEnergy(ForgeDirection from, long extract, boolean doExtract)
    {
        return 0;
    }
    
    @Override
    public long onReceiveEnergy(ForgeDirection from, long receive, boolean doReceive)
    {
        if(from != ForgeDirection.UNKNOWN || this.canConnect(from))
        {
            long energyReceived = Math.min(this.maxEnergy - this.energy, Math.min(this.maxReceive, receive));

            if (doReceive)
            {
                this.energy += energyReceived;
            }
            return energyReceived;
        }
        
        return 0;
    }
}
