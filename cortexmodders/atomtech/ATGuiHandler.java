package cortexmodders.atomtech;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cortexmodders.atomtech.client.gui.GuiLaptop;
import cortexmodders.atomtech.container.ContainerLaptop;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.network.IGuiHandler;

public class ATGuiHandler implements IGuiHandler {
	
    //returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(tileEntity instanceof TileEntityLaptop) {
			return new ContainerLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		
		return null;
	}
	
	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(tileEntity instanceof TileEntityLaptop) {
			return new GuiLaptop(player.inventory, (TileEntityLaptop) tileEntity);
		}
		
		return null;
	}
}