package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLaptop extends BlockContainer {

    public BlockLaptop(int id, Material material) {
        super(id, material);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityLaptop();
    }
    
    @Override
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    @Override
    public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("atomtech:laptop");
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
