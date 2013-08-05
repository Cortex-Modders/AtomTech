package cortexmodders.atomtech.client.gui;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import cortexmodders.atomtech.client.gui.part.GuiBatteryIcon;
import cortexmodders.atomtech.client.gui.part.GuiCortexBase;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLaptop extends GuiCortexBase {
    
    private TileEntity boundEntity;
    
    private GuiBatteryIcon batteryIcon;
    
    public GuiLaptop(TileEntity entity) {
        this();
        boundEntity = entity;
    }
    
    public GuiLaptop() {
        super();
        xSize = 252;
        ySize = 218;
        texture = new ResourceLocation("atomtech", "textures/gui/laptop.png");
        batteryIcon = new GuiBatteryIcon(1, 1);
    }
    
    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {
        super.drawScreen(x, y, renderPartialTicks);
        batteryIcon.draw(this, this.getLeft(), this.getTop(), 0.6F);
    }
    
    @Override
    public void onGuiClosed() {
        if(boundEntity != null && boundEntity instanceof TileEntityLaptop)
            ((TileEntityLaptop)boundEntity).setLidClosed(true);
    }
    
    
}
