package net.cortexmodders.atomtech;

import net.cortexmodders.atomtech.client.gui.GuiLaptop;
import net.cortexmodders.atomtech.container.ContainerLaptop;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ATGuiHandler implements IGuiHandler
{
    
    // returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        
        if (tileEntity instanceof TileEntityLaptop)
            return new GuiLaptop(player.inventory, (TileEntityLaptop) tileEntity);
        
        return null;
    }
    
    // returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        
        if (tileEntity instanceof TileEntityLaptop)
            return new ContainerLaptop(player.inventory, (TileEntityLaptop) tileEntity);
        
        return null;
    }
}
