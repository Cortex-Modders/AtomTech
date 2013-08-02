package cortexmodders.atomtech;

import net.minecraftforge.common.Configuration;
import cortexmodders.atomtech.element.ElementCollection;
import cortexmodders.atomtech.lib.ATResources;
import cortexmodders.atomtech.lib.BlockIds;
import cortexmodders.atomtech.tileentity.TileEntityBattery;
import cortexmodders.atomtech.tileentity.TileEntityCable;
import cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
	public void addElementsJson() {
	    ElementCollection elements = new ElementCollection();
	    elements.parseElementInfo(ATResources.ELEMENT_JSON_FILE);
	    AtomTech.elements = elements;
	}
	
	public void regesterBlockIds(Configuration config) {
		BlockIds.CABLE = config.getBlock("cable", BlockIds.CABLE_DEF).getInt();
		BlockIds.COAL_GENERATOR = config.getBlock("coalGenerator", BlockIds.COAL_GENERATOR_DEF).getInt();
		BlockIds.BATTERY = config.getBlock("battery", BlockIds.BATTERY_DEF).getInt();
		BlockIds.LAPTOP = config.getBlock("laptop", BlockIds.BATTERY_DEF).getInt();
		BlockIds.ORES = config.getBlock("ores", BlockIds.ORES_DEF).getInt();
	}
	
	public void registerTileEntities() {
	    GameRegistry.registerTileEntity(TileEntityCable.class, "tileEntityCable");
        GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, "tileEntityCoalGenerator");
        GameRegistry.registerTileEntity(TileEntityBattery.class, "tileEntityBattery");
        GameRegistry.registerTileEntity(TileEntityLaptop.class, "tileEntityLaptop");
	}
	
	public void registerEntities() {
	    
	}
	
	public void registerRenderers() {
	    // stub.
	}
	
	
}