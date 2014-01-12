package net.cortexmodders.atomtech.tileentity;

public class TileEntityCable extends TilePoweredBase
{
    
    public TileEntityCable()
    {
        super(10);
    }
    
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
}
