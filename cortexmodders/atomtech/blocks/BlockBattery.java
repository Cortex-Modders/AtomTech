package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.AtomTech;
import cortexmodders.atomtech.tileentity.TileEntityBattery;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBattery extends BlockContainer
{
	protected BlockBattery(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(AtomTech.atomTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBattery();
	}
}