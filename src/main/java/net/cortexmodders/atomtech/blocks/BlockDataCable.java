package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.AtomTech;
import net.cortexmodders.atomtech.tileentity.TileEntityDataCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDataCable extends BlockContainer
{
    
    public BlockDataCable(final int par1)
    {
        super(par1, Material.circuits);
        this.setCreativeTab(AtomTech.atomTab);
    }
    
    @Override
    public TileEntity createNewTileEntity(final World world)
    {
        // TODO Auto-generated method stub
        return new TileEntityDataCable();
    }
    
}
