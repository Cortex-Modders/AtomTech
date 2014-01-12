package net.cortexmodders.atomtech.client.gui.part;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public abstract class GuiPart extends Gui {

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
    
    public GuiPart(int parX, int parY) {
        x = parX;
        y = parY;

    }
    
    public boolean inRect(Gui gui, int mouseX, int mouseY) {
        return false;
    }
    
    protected void draw(GuiCortexBase gui) {
        draw(gui, gui.getLeft(), gui.getTop());
    }
    
    protected void draw(Gui gui, int guiLeft, int guiTop) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
        
        this.drawTexturedModalRect(guiLeft + x, guiTop + y, srcX, srcY, width, height);
    }
}
