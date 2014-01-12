package cortexmodders.atomtech.client;

import cortexmodders.atomtech.CommonProxy;
import cortexmodders.atomtech.client.render.tileentity.RenderCable;
import cortexmodders.atomtech.client.render.tileentity.RenderLaptop;
import cortexmodders.atomtech.lib.RenderIds;
import cortexmodders.atomtech.tileentity.TileEntityCable;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenderers() {
        RenderLaptop laptopRenderer = new RenderLaptop(RenderIds.LAPTOP_RENDER_ID);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, laptopRenderer);
        RenderingRegistry.registerBlockHandler(laptopRenderer);
    }
	
}