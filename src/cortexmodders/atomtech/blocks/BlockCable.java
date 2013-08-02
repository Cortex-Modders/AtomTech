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
	protected BlockCable(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(AtomTech.atomTab);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId)
	{
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityCable)
		{
			TileEntityCable cable = (TileEntityCable)world.getBlockTileEntity(x, y, z);
			if(neighborId == this.blockID && cable != null)
			{
				int powerLevelPre = cable.getPower();
				syncBlock(world, x + 1, y, z, cable);
				syncBlock(world, x - 1, y, z, cable);
				syncBlock(world, x, y + 1, z, cable);
				syncBlock(world, x, y - 1, z, cable);
				syncBlock(world, x, y, z + 1, cable);
				syncBlock(world, x, y, z - 1, cable);
				int powerLevelPost = cable.getPower();
				if(powerLevelPre != powerLevelPost)
				{
					world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
				}
			}
		}
	}
	
	public void syncBlock(World world, int x, int y, int z, TileEntityCable localCable)
	{
		if(world.getBlockId(x, y, z) == this.blockID && world.getBlockTileEntity(x, y, z)  instanceof TileEntityCable)
		{
			TileEntityCable sourceCable = (TileEntityCable)world.getBlockTileEntity(x, y, z);
			if(sourceCable != null && sourceCable.getPower() > 0 && localCable.getPower() < sourceCable.getPower())
			{
				localCable.setPower(sourceCable.getPower());
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityCable();
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return -1;
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
	
	private boolean validBlock(int blockId)
	{
		Block block = Block.blocksList[blockId];
		if(block != null && block instanceof IAtomicPower)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
	{
		this.setBlockBounds(0.3125F, 0.3125F, 0.3125F, 0.3125F, 0.3125F, 0.3125F);
		float minx = (float)this.minX;
		float miny = (float)this.minY;
		float minz = (float)this.minZ;
		float maxx = (float)this.maxX;
		float maxy = (float)this.maxY;
		float maxz = (float)this.maxZ;
		
		if(validBlock(access.getBlockId(x-1, y, z)))
			minx = 0;
		if(validBlock(access.getBlockId(x+1, y, z)))
			minx = 0;
		if(validBlock(access.getBlockId(x, y-1, z)))
			minx = 0;
		if(validBlock(access.getBlockId(x, y+1, z)))
			minx = 0;
		if(validBlock(access.getBlockId(x, y, z-1)))
			minx = 0;
		if(validBlock(access.getBlockId(x, y, z+1)))
			minx = 0;
		
		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
}