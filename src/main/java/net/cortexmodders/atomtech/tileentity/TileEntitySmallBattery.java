package net.cortexmodders.atomtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.api.energy.EnergyStorageHandler;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.vector.Vector3;

public class TileEntitySmallBattery extends AbstractTileElectricity implements IEnergyInterface, IEnergyContainer
{
    
    public TileEntitySmallBattery()
    {
        super();
        maxEnergy = 100;
//        
//        energy = new EnergyStorageHandler(100, 10, 10);
    }
    
    @Override
    public void updateEntity()
    {
//        if (!this.worldObj.isRemote)
//            System.out.println(this.energy);
        super.updateEntity();
        
        this.produce();
    }
    
    // UE Methods
    
    @Override
    public long getEnergy(ForgeDirection from)
    {
        return this.getEnergy();
    }
    
    public long getEnergy()
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
    
    @Override
    public long produce()
    {
        long totalUsed = 0;
        
        for (ForgeDirection direction : this.getOutputDirections())
        {
            if (this.getEnergy(direction) > 0)
            {
                TileEntity tileEntity = new Vector3(this).modifyPositionFromSide(direction).getTileEntity(this.worldObj);
                
                if (tileEntity != null)
                {
                    // sends the energy
                    long used = CompatibilityModule.receiveEnergy(tileEntity, direction.getOpposite(), this.onExtractEnergy(direction, this.getEnergy(direction), false), true);
                    this.onExtractEnergy(direction, used, true);
                    totalUsed += used;
                }
            }
        }
        
        return totalUsed;
    }
}
