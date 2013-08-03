package cortexmodders.atomtech.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cortexmodders.atomtech.client.model.tileentity.ModelLaptop;
import cortexmodders.atomtech.lib.ATProperties;
import cortexmodders.atomtech.lib.RenderIds;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLaptop extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    private final int renderId;
    
    private ModelLaptop model;
    
    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/models/laptop.png");
    private static final ResourceLocation broken_texture = new ResourceLocation(ATProperties.ID, "textures/tileentity/broken_laptop.png");
    
    public RenderLaptop(int id) {
        renderId = id;
        model = new ModelLaptop();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        TileEntityLaptop laptop;
        if(entity instanceof TileEntityLaptop)
            laptop = (TileEntityLaptop)entity;
        else
            return;
        
        ResourceLocation currentTexture = texture;
        if(laptop.isBroken())
            currentTexture = broken_texture;
        
        //Binds the texture
        this.func_110628_a(texture);
        
        GL11.glPushMatrix();
        
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glTranslatef(0.5F, 0.0625F, 0.5F);
        GL11.glRotatef(180F, 0F, 0F, 1F);
        model.render(laptop, laptop.lidAngleX, 0f, 0f, 0f, 0f, 0.0625F);
        
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        // TODO: ADD INVENTORY RENDER!
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

}
