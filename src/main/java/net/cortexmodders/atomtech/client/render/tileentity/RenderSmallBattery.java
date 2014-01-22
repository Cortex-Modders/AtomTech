package net.cortexmodders.atomtech.client.render.tileentity;

import net.cortexmodders.atomtech.client.model.tileentity.ModelSmallBattery;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderSmallBattery extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    
    ModelSmallBattery model;
    
    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/models/small_battery.png");
    
    public RenderSmallBattery()
    {
        model = new ModelSmallBattery();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float delta)
    {
        this.bindTexture(texture);
        int meta = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        GL11.glPushMatrix();
        {
            GL11.glTranslated(x, y, z);
            GL11.glTranslatef(0.5F, 0.0F, 0.5F);
            GL11.glRotatef(-90 * meta, 0F, 1F, 0F);
            GL11.glScalef(-1F, -1F, 1F);
            
            model.render(0.0625F);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return 0;
    }
    
}
