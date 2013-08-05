package cortexmodders.atomtech.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cortexmodders.atomtech.client.gui.part.GuiBatteryIcon;
import cortexmodders.atomtech.container.ContainerLaptop;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLaptop extends GuiContainer {
    
    private TileEntityLaptop boundEntity;
    
    private GuiBatteryIcon batteryIcon;
    
    private static final ResourceLocation texture = new ResourceLocation("atomtech", "textures/gui/laptop.png");;
    
    public GuiLaptop(InventoryPlayer player, TileEntityLaptop laptop) {
        super(new ContainerLaptop(player, laptop));
        boundEntity = laptop;
        xSize = 252;
        ySize = 218;
        batteryIcon = new GuiBatteryIcon(1, 1);
    }
    
    @Override
    public void drawGuiContainerBackgroundLayer(float renderPartialTicks, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        this.mc.func_110434_K().func_110577_a(texture);
        
        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
        
        batteryIcon.draw(this, this.guiLeft, this.guiTop, 0.6F);
    }
    
    @Override
    public void onGuiClosed() {
        if(boundEntity != null && boundEntity instanceof TileEntityLaptop)
            ((TileEntityLaptop)boundEntity).setLidClosed(true);
    }
    
    
}
