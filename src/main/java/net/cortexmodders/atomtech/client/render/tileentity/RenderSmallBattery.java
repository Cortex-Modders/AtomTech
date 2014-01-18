package net.cortexmodders.atomtech.client.render.tileentity;

import net.cortexmodders.atomtech.client.model.tileentity.ModelSmallBattery;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;


public class RenderSmallBattery extends TileEntitySpecialRenderer
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
        
        GL11.glPushMatrix();
        {
            GL11.glTranslated(x, y, z);
            GL11.glTranslatef(0.5F, 0.0F, 0.5F);
            GL11.glScalef(-1F, -1F, 1F);
            
            model.render(0.0625F);
        }
        GL11.glPopMatrix();
    }
    
}
