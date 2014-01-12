package cortexmodders.atomtech.client.gui.part;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCortexBase extends GuiContainer {
    
    protected ResourceLocation texture;
    
    public GuiCortexBase(Container container) {
        super(container);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.func_110434_K().func_110577_a(texture);
        
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);;
    }
    
    protected void drawScreen(GuiCortexBase gui, int x, int y, float partialTicks) {
        drawScreen(gui, gui.getLeft(), gui.getTop());
    }
    
    protected void drawScreen(Gui gui, int x, int y) {
        
    }
    
    public int getLeft() {
        return this.guiLeft;
    }
    
    public int getTop() {
        return this.guiTop;
    }
}
