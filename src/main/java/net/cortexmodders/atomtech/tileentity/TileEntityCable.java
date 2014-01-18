package net.cortexmodders.atomtech.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.energy.EnergyNetworkLoader;
import universalelectricity.api.energy.IConductor;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.energy.IEnergyNetwork;
import universalelectricity.api.vector.Vector3;
import universalelectricity.api.vector.VectorHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCable extends TileEntity implements IEnergyInterface, IConductor
{
    
    private IEnergyNetwork network;
    
    // all the connections. 6 for 6 sides.
    private TileEntity[] connections = new TileEntity[6];
    
    private float resistance = 1;
    private int capacity = 1;
    
    public TileEntityCable()
    {
        // copper resistance
        resistance = 0.0000000168F;
        capacity = 1;
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

    public int getNumConnections()
    {
        int length = 0;
        for(int i = 0; i < connections.length; i++)
            if(connections[i] != null)
                length++;
        return length;
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
        if(entity instanceof TileEntityCable ||
           entity instanceof IEnergyInterface)
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
        if (this.canConnect(from) && this.getNetwork() != null)
        {
            return this.getNetwork().produce(this, from.getOpposite(), receive, doReceive);
        }
        return 0;
    }

    @Override
    public long getCurrentCapacity()
    {
        return capacity;
    }

    @Override
    public float getResistance()
    {
        return resistance;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
    }
}
