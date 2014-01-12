package net.cortexmodders.atomtech.client.render.tileentity;

import net.cortexmodders.atomtech.blocks.BlockCable;
import net.cortexmodders.atomtech.client.model.tileentity.ModelCable;
import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderCable extends TileEntitySpecialRenderer
{
    
    // Model file
    private ModelCable model;
    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/models/cable.png");
    
    private boolean top = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean right = false;
    private boolean front = false;
    private boolean back = false;
    
    public RenderCable()
    {
        // initialization of Model File
        this.model = new ModelCable();
    }
    
    public void configureSides(final TileEntityCable tile)
    {
        int x = tile.xCoord;
        int y = tile.yCoord;
        int z = tile.zCoord;
        this.top = BlockCable.validBlock(tile.worldObj, x, y + 1, z);
        this.bottom = BlockCable.validBlock(tile.worldObj, x, y - 1, z);
        this.left = BlockCable.validBlock(tile.worldObj, x + 1, y, z);
        this.right = BlockCable.validBlock(tile.worldObj, x - 1, y, z);
        this.front = BlockCable.validBlock(tile.worldObj, x, y, z + 1);
        this.back = BlockCable.validBlock(tile.worldObj, x, y, z - 1);
    }
    
    // your TileEntity
    public void renderAModelAt(final TileEntityCable tile, final double d, final double d1, final double d2, final float f)
    {
        if (tile.worldObj != null)
            tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        // directory of the model's texture file
        this.bindTexture(texture);
        
        GL11.glPushMatrix();
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
        GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, -1F, -1F);
        this.configureSides(tile);
        this.model.renderModel(0.0625F, this.top, this.bottom, this.left, this.right, this.front, this.back);
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(final TileEntity tileentity, final double d, final double d1, final double d2, final float f)
    {
        // your TileEntity
        this.renderAModelAt((TileEntityCable) tileentity, d, d1, d2, f);
    }
}
