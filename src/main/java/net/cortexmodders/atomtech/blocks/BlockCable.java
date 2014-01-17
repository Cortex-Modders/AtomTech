package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockCable extends BlockContainer
{
    
    protected BlockCable(final int par1)
    {
        super(par1, Material.cloth);
        this.setCreativeTab(AtomTech.atomTab);
    }
    
    @Override
    public TileEntity createNewTileEntity(final World world)
    {
        return new TileEntityCable();
    }
    
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public void registerIcons(final IconRegister register)
    {
        this.blockIcon = register.registerIcon("atomtech:cable");
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return world.doesBlockHaveSolidTopSurface(x, y - 1, z);
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId)
    {
        if (!world.isRemote)
        {
            if (!this.canPlaceBlockAt(world, x, y, z))
            {
                this.dropBlockAsItem(world, x, y, z, 0, 0);
                world.setBlockToAir(x, y, z);
            }

            super.onNeighborBlockChange(world, x, y, z, neighborId);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess access, final int x, final int y, final int z)
    {
        this.setBlockBounds(0.40625F, 0F, 0F, 0.59375F, 0.0625F, 1F);
        
        float minx = (float) this.minX;
        float miny = (float) this.minY;
        float minz = (float) this.minZ;
        float maxx = (float) this.maxX;
        float maxy = (float) this.maxY;
        float maxz = (float) this.maxZ;
        
        TileEntityCable tile = (TileEntityCable) access.getBlockTileEntity(x, y, z);
        int conNum = tile.getNumConnections();
        
        if(conNum > 0 && conNum < 3)
        {
            TileEntity[] connections = tile.getConnections();
            
            for(int i = 0; i < connections.length; i++)
            {
                if(connections[i] == null)
                    continue;
                
                ForgeDirection direction = ForgeDirection.getOrientation(i);
                
                if(conNum == 1)
                {
                    maxz = 0.4375F;
                    
                    switch(direction)
                    {
                        case NORTH: {break;}
                        case SOUTH: {break;}
                        case EAST: {break;}
                        case WEST: {break;}
                    }
                    //System.out.println(String.format("MinX = %s, MinY = %s, MinZ = %s, MaxX = %s, MaxY = %s, MaxZ = %s", minx, miny, minz, maxx, maxy, maxz));
                }
                else if(conNum == 2 && (direction == ForgeDirection.EAST || direction == ForgeDirection.WEST))
                {
                    float swapX1, swapX2;
                    swapX1 = minx;
                    swapX2 = maxx;
                    
                    minx = minz;
                    minz = swapX1;
                    maxx = maxz;
                    maxz = swapX2;
                    
                    break;
                }
            }
        }
        else if(conNum >= 3)
        {
            minx = 0F;
            miny = 0F;
            minz = 0F;
            maxx = 1F;
            maxy = 0.125F;
            maxz = 1F;
        }

        this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
    }
}
