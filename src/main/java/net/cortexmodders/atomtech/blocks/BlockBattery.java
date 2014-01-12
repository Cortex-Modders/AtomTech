package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.tileentity.TileEntityBattery;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBattery extends BlockContainer
{
    
    protected BlockBattery(final int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(AtomTech.atomTab);
    }
    
    @Override
    public TileEntity createNewTileEntity(final World world)
    {
        return new TileEntityBattery();
    }
}
