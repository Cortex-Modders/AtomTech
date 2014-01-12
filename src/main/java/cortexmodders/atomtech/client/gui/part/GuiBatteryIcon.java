package cortexmodders.atomtech.client.gui.part;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class GuiBatteryIcon extends GuiPart {

    private int colorX;
    private int colorW;
    private int[] colorY;
    
    public GuiBatteryIcon(int parX, int parY) {
        super(parX, parY);
        texture = new ResourceLocation("atomtech", "textures/gui/icons.png");
        srcX = 0;
        srcY = 70;
        width = 16;
        height = 16;
        colorX = 0;
        colorW = 0;
        colorY = new int[3];
        colorY[0] = 70;
        colorY[1] = colorY[0]++;
        colorY[2] = colorY[1]++;
    }

    public void draw(Gui gui, int guiLeft, int guiTop, float battPercent) {
        super.draw(gui, guiLeft, guiTop);
        
    }
}
