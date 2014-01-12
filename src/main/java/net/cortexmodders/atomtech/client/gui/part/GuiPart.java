package net.cortexmodders.atomtech.client.gui.part;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public abstract class GuiPart extends Gui
{
    
    private int x;
    private int y;
    
    protected int width;
    protected int height;
    
    protected int scaleWidth = 1;
    protected int scaleHeight = 1;
    
    /** Image source x. */
    protected int srcX = 0;
    /** Image source y. */
    protected int srcY = 0;
    
    protected ResourceLocation texture;
    
    public GuiPart(final int parX, final int parY)
    {
        this.x = parX;
        this.y = parY;
        
    }
    
    protected void draw(final Gui gui, final int guiLeft, final int guiTop)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        
        this.drawTexturedModalRect(guiLeft + this.x, guiTop + this.y, this.srcX, this.srcY, this.width, this.height);
    }
    
    protected void draw(final GuiCortexBase gui)
    {
        this.draw(gui, gui.getLeft(), gui.getTop());
    }
    
    public boolean inRect(final Gui gui, final int mouseX, final int mouseY)
    {
        return false;
    }
}
