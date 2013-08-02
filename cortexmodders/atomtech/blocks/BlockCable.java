package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.AtomTech;
import cortexmodders.atomtech.power.IAtomicPower;
import cortexmodders.atomtech.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer
{
	protected BlockCable(int par1)
	{
		super(par1, Material.cloth);
		this.setCreativeTab(AtomTech.atomTab);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityCable();
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}
	
	@Override
	public void registerIcons(IconRegister register)
	{
		this.blockIcon = register.registerIcon("atomtech:cable");
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	private boolean validBlock(TileEntity tile)
	{
		if(tile != null && tile instanceof IAtomicPower)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
	{
		this.setBlockBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
		float minx = (float)this.minX;
		float miny = (float)this.minY;
		float minz = (float)this.minZ;
		float maxx = (float)this.maxX;
		float maxy = (float)this.maxY;
		float maxz = (float)this.maxZ;
		
		if(validBlock(access.getBlockTileEntity(x-1, y, z)))
			minx = 0;
		if(validBlock(access.getBlockTileEntity(x+1, y, z)))
			maxx = 1;
		if(validBlock(access.getBlockTileEntity(x, y-1, z)))
			miny = 0;
		if(validBlock(access.getBlockTileEntity(x, y+1, z)))
			maxy = 1;
		if(validBlock(access.getBlockTileEntity(x, y, z-1)))
			minz = 0;
		if(validBlock(access.getBlockTileEntity(x, y, z+1)))
			maxz = 1;
		
		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
}