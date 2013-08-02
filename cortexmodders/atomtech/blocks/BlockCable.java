package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.AtomTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer
{
	protected BlockCable(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(AtomTech.atomTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return null;
	}
}