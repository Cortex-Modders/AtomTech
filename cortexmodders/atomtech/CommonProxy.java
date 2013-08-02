package cortexmodders.atomtech;

import net.minecraftforge.common.Configuration;
import cortexmodders.atomtech.element.ElementCollection;
import cortexmodders.atomtech.lib.ATResources;
import cortexmodders.atomtech.lib.BlockIds;

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
	}
}