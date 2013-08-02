package cortexmodders.atomtech.client;

import cortexmodders.atomtech.CommonProxy;
import cortexmodders.atomtech.client.render.tileentity.RenderLaptop;
import cortexmodders.atomtech.lib.RenderIds;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
        RenderLaptop laptopRenderer = new RenderLaptop(RenderIds.LAPTOP_RENDER_ID);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, laptopRenderer);
        RenderingRegistry.registerBlockHandler(laptopRenderer);
    }
	
}