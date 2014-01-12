package net.cortexmodders.atomtech.client.gui;

import net.cortexmodders.atomtech.client.gui.part.GuiBatteryIcon;
import net.cortexmodders.atomtech.container.ContainerLaptop;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLaptop extends GuiContainer
{
    
    private TileEntityLaptop boundEntity;
    
    private GuiBatteryIcon batteryIcon;
    
    private int screrenLeft;
    private int screenTop;
    private int screenWidth;
    private int screenHeight;
    
    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/gui/laptop.png");;
    
    public GuiLaptop(final InventoryPlayer player, final TileEntityLaptop laptop)
    {
        super(new ContainerLaptop(player, laptop));
        this.boundEntity = laptop;
        this.xSize = 252;
        this.ySize = 218;
        this.batteryIcon = new GuiBatteryIcon(1, 1);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(final float renderPartialTicks, final int x, final int y)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.getTextureManager().bindTexture(texture);
        
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        
        this.batteryIcon.draw(this, this.screrenLeft + this.screenWidth - 16, this.screenTop + this.screenHeight - 16, 0.6F);
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        this.screrenLeft = this.guiLeft + 14;
        this.screenTop = this.guiTop + 14;
        this.screenWidth = 224;
        this.screenHeight = 190;
    }
    
    @Override
    public void onGuiClosed()
    {
        if (this.boundEntity != null && this.boundEntity instanceof TileEntityLaptop)
            this.boundEntity.setLidClosed(true);
    }
    
}
