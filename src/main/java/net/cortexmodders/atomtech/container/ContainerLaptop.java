package net.cortexmodders.atomtech.container;

import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerLaptop extends Container
{
    
    protected TileEntityLaptop tileentity;
    
    public ContainerLaptop(final InventoryPlayer player, final TileEntityLaptop parTileEntity)
    {
        this.tileentity = parTileEntity;
    }
    
    @Override
    public boolean canInteractWith(final EntityPlayer entityplayer)
    {
        return true;
    }
    
}
