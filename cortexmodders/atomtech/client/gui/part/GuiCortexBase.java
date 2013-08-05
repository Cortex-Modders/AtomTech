package cortexmodders.atomtech.client.gui.part;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiCortexBase extends GuiScreen {

    protected int xSize = 0;
    protected int ySize = 0;

    protected int width = 0;
    protected int height = 0;
    
    private int guiLeft;
    private int guiTop;
    
    protected ResourceLocation texture;
    
    public GuiCortexBase() {
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2; 
    }
    
    @Override
    public void drawScreen(int x, int y, float partialTicks) {
        super.drawScreen(x, y, partialTicks);
        this.drawScreen(this);
    }
    
    protected void drawScreen(GuiCortexBase gui) {
        drawScreen(gui, gui.getLeft(), gui.getTop());
    }
    
    protected void drawScreen(Gui gui, int guiLeft, int guiTop) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
        
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);;
    }
    
    public int getLeft() {
        return (this.width - this.xSize) / 2;
    }
    
    public int getTop() {
        return (this.height - this.ySize) / 2; 
    }
}
