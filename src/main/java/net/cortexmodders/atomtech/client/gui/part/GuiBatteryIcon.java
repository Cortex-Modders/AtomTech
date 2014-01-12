package net.cortexmodders.atomtech.client.gui.part;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class GuiBatteryIcon extends GuiPart
{
    
    private int[] colorY;
    
    public GuiBatteryIcon(final int parX, final int parY)
    {
        super(parX, parY);
        this.texture = new ResourceLocation("atomtech", "textures/gui/icons.png");
        this.srcX = 0;
        this.srcY = 70;
        this.width = 16;
        this.height = 16;
        this.colorY = new int[3];
        this.colorY[0] = 70;
        this.colorY[1] = this.colorY[0]++;
        this.colorY[2] = this.colorY[1]++;
    }
    
    public void draw(final Gui gui, final int guiLeft, final int guiTop, final float battPercent)
    {
        super.draw(gui, guiLeft, guiTop);
        
    }
}
