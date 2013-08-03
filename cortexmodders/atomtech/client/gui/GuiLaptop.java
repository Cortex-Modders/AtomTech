package cortexmodders.atomtech.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cortexmodders.atomtech.tileentity.TileEntityLaptop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLaptop extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/gui/laptop.png");
    
    private TileEntity boundEntity;
    
    private int xSize;
    private int ySize;

    private int guiLeft;
    private int guiTop;
    
    public GuiLaptop(TileEntity entity) {
        super();
        boundEntity = entity;
    }
    
    public GuiLaptop() {
        xSize = 252;
        ySize = 218;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
        
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
    
    @Override
    public void onGuiClosed() {
        if(boundEntity != null && boundEntity instanceof TileEntityLaptop)
            ((TileEntityLaptop)boundEntity).setLidClosed(true);
    }
    
    
}
