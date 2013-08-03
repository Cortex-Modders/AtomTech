package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.AtomTech;
import cortexmodders.atomtech.tileentity.TileEntityDataCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDataCable extends BlockContainer {

    public BlockDataCable(int par1) {
        super(par1, Material.circuits);
        setCreativeTab(AtomTech.atomTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return new TileEntityDataCable();
    }

}
