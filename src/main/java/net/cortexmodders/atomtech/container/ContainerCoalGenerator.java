package net.cortexmodders.atomtech.container;

import net.cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCoalGenerator extends Container
{
    
    private TileEntityCoalGenerator generator;
    
    public ContainerCoalGenerator(final TileEntityCoalGenerator parTile)
    {
        this.generator = parTile;
        this.addSlotToContainer(new Slot(parTile, 0, 0, 0));
    }
    
    @Override
    public boolean canInteractWith(final EntityPlayer player)
    {
        return this.generator.isUseableByPlayer(player);
    }
    
}
