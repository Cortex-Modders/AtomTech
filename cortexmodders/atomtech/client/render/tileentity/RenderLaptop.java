package cortexmodders.atomtech.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cortexmodders.atomtech.lib.ATProperties;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderLaptop extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

    private final int renderId;
    
// private ModelLaptop model;    
    
    private static final ResourceLocation texture = new ResourceLocation(ATProperties.ID, "textures/tileentity/laptop.png");
    private static final ResourceLocation broken_texture = new ResourceLocation(ATProperties.ID, "textures/tileentity/broken_laptop.png");
    
    public RenderLaptop(int id) {
        renderId = id;
//      model = new ModelLaptop();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        
        
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        // TODO: ADD INVENTORY RENDER!
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

}
