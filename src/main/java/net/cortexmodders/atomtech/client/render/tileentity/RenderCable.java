package net.cortexmodders.atomtech.client.render.tileentity;

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

    public RenderCable()
    {
        // initialization of Model File
        this.model = new ModelCable();
    }
    
    // your TileEntity
    public void renderModel(final TileEntityCable tile, final double x, final double y, final double z, final float delta)
    {
        if (tile.worldObj != null)
            tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
        
        // directory of the model's texture file
        this.bindTexture(texture);
        
        GL11.glPushMatrix();
        {
            GL11.glTranslatef((float)x, (float)y, (float)z);
            TileEntity[] connections = tile.getConnections();
            
            if(connections[0] != null || connections[1] != null)
            {
                
            }
            
            this.model.render(tile, 0.0625F);
        }
        GL11.glPopMatrix();
    }
    
    @Override
    public void renderTileEntityAt(final TileEntity tileentity, final double x, final double y, final double z, final float delta)
    {
        // your TileEntity
        this.renderModel((TileEntityCable) tileentity, x, y, z, delta);
    }
}
