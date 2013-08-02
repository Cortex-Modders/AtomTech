package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
    
    @Override
    public void onFallenUpon(World par1World, int x, int y, int z, Entity entity, float par6)
    {
        if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            ((TileEntityLaptop)par1World.getBlockTileEntity(x, y, z)).degradeCondition();
        }
    }
}
