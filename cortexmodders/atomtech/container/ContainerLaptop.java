package cortexmodders.atomtech.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;

public class ContainerLaptop extends Container {

    protected TileEntityLaptop tileentity;
    
    public ContainerLaptop(InventoryPlayer player, TileEntityLaptop parTileEntity) {
        tileentity = parTileEntity;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }

}
