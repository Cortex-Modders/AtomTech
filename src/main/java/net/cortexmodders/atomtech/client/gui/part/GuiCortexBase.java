package net.cortexmodders.atomtech.client.gui.part;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCortexBase extends GuiContainer
{
    
    protected ResourceLocation texture;
    
    public GuiCortexBase(final Container container)
    {
        super(container);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(final float f, final int x, final int y)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(this.texture);
        
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        ;
    }
    
    protected void drawScreen(final Gui gui, final int x, final int y)
    {
        
    }
    
    protected void drawScreen(final GuiCortexBase gui, final int x, final int y, final float partialTicks)
    {
        this.drawScreen(gui, gui.getLeft(), gui.getTop());
    }
    
    public int getLeft()
    {
        return this.guiLeft;
    }
    
    public int getTop()
    {
        return this.guiTop;
    }
}
