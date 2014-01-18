package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.tileentity.TileEntitySmallBattery;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSmallBattery extends BlockContainer
{
    
    protected BlockSmallBattery(final int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(AtomTech.atomTab);
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, item);
        int meta = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.28125F, 0.0F, 0.21875F, 0.71875F, 0.25F, 0.78125F);
        
        int meta = world.getBlockMetadata(x, y, z);
        if(meta == 1 || meta == 3)
        {
            // swap x for z.
            this.setBlockBounds((float)this.minZ, (float)this.minY, (float)this.minX, (float)this.maxZ, (float)this.maxY, (float)this.maxX);
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(final World world)
    {
        return new TileEntitySmallBattery();
    }
}
