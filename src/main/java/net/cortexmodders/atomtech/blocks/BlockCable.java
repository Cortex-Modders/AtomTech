package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.power.IAtomicPower;
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
    
    public static boolean validBlock(final TileEntity tile)
    {
        if (tile != null && tile instanceof IAtomicPower)
            return true;
        return false;
    }
    
    public static boolean validBlock(final World world, final int x, final int y, final int z)
    {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        return validBlock(tile);
    }
    
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
    public void registerIcons(final IconRegister register)
    {
        this.blockIcon = register.registerIcon("atomtech:cable");
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
        int length = tile.getNumConnections();
        
        if(length > 0 && length < 3)
        {
            TileEntity[] connections = tile.getConnections();
            
            for(int i = 0; i < connections.length; i++)
            {
                if(connections[i] == null)
                    continue;
                
                ForgeDirection direction = ForgeDirection.getOrientation(i);
                
                if(length == 1)
                {
                    
                    break;
                }
            }
        }
        else if(length > 3)
        {
            
        }
        
        //        if (validBlock(access.getBlockTileEntity(x - 1, y, z)))
        //            minx = 0;
        //        if (validBlock(access.getBlockTileEntity(x + 1, y, z)))
        //            maxx = 1;
        //        if (validBlock(access.getBlockTileEntity(x, y - 1, z)))
        //            miny = 0;
        //        if (validBlock(access.getBlockTileEntity(x, y + 1, z)))
        //            maxy = 1;
        //        if (validBlock(access.getBlockTileEntity(x, y, z - 1)))
        //            minz = 0;
        //        if (validBlock(access.getBlockTileEntity(x, y, z + 1)))
        //            maxz = 1;
        
        //        this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
    }
}
