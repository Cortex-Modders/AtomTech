package net.cortexmodders.atomtech.tileentity;

import net.minecraft.tileentity.TileEntity;

public abstract class AbstractTileElectricity extends TileEntity
{
    
    long energy = 0;
    long maxEnergy = 100;
    long maxReceive = 0;
    long maxSend = 0;
    float resistance = 0.0000000168F;
    
    
}
