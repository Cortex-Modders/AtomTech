package net.cortexmodders.atomtech.client;

import net.cortexmodders.atomtech.CommonProxy;
import net.cortexmodders.atomtech.client.render.tileentity.RenderCable;
import net.cortexmodders.atomtech.client.render.tileentity.RenderLaptop;
import net.cortexmodders.atomtech.client.render.tileentity.RenderSmallBattery;
import net.cortexmodders.atomtech.lib.RenderIds;
import net.cortexmodders.atomtech.tileentity.TileEntitySmallBattery;
import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    
    @Override
    public void registerRenderers()
    {
        RenderLaptop laptopRenderer = new RenderLaptop(RenderIds.LAPTOP_RENDER_ID);
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmallBattery.class, new RenderSmallBattery());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, laptopRenderer);
        RenderingRegistry.registerBlockHandler(laptopRenderer);
    }
    
}
