package net.cortexmodders.atomtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.energy.EnergyNetworkLoader;
import universalelectricity.api.energy.IConductor;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.energy.IEnergyNetwork;
import universalelectricity.api.vector.Vector3;
import universalelectricity.api.vector.VectorHelper;

public class TileEntityCable extends TileEntity implements IEnergyInterface, IConductor
{
    
    private IEnergyNetwork network;
    
    // all the connections. 6 for 6 sides.
    private TileEntity[] connections = new TileEntity[6];
    
    public TileEntityCable()
    {

    }

    @Override
    public void updateEntity()
    {
        checkConnection();
    }
    
    public void checkConnection()
    {
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            TileEntity tile = VectorHelper.getTileEntityFromSide(this.worldObj, new Vector3(this), side);
            
            if(this.canConnect(tile))
            {
                if(tile instanceof IConductor)
                {
                    this.getNetwork().merge(((IConductor) tile).getNetwork());
                }
                connections[side.ordinal()] = tile;
            }
            else
            {
                connections[side.ordinal()] = null;
            }
        }
    }
    
    @Override
    public TileEntity[] getConnections()
    {
        return this.connections;
    }

    @Override
    public IEnergyNetwork getNetwork()
    {
        if(this.network == null)
        {
            this.network = EnergyNetworkLoader.getNewNetwork(this);
        }
        return network;
    }

    @Override
    public void setNetwork(IEnergyNetwork network)
    {
        this.network = network;
    }

    @Override
    public boolean canConnect(ForgeDirection direction)
    {
        TileEntity tile = VectorHelper.getTileEntityFromSide(this.worldObj, new Vector3(this), direction);
        return canConnect(tile);
    }

    public boolean canConnect(TileEntity entity)
    {
        if(entity instanceof TileEntityCable)
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public long onExtractEnergy(ForgeDirection from, long extract, boolean doExtract)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long onReceiveEnergy(ForgeDirection from, long receive, boolean doReceive)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getCurrentCapacity()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getResistance()
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
