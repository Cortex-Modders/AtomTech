package net.cortexmodders.atomtech.tileentity;

import java.util.EnumSet;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public abstract class AbstractTileElectricity extends TileEntity
{
    
    // Pre-implemented fields
    
    long energy = 0;
    long maxEnergy = 100;
    long maxReceive = 0;
    long maxExtract = 0;
    float resistance = 0.0000000168F;
    
//    protected EnergyStorageHandler energy;
    
    // Abstract methods
    
    protected abstract long produce();
    
    // Pre-implemented methods
    
    public EnumSet<ForgeDirection> getOutputDirections()
    {
        return EnumSet.of(ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
    }
}
