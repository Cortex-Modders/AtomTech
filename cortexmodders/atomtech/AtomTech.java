package cortexmodders.atomtech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cortexmodders.atomtech.blocks.ModBlocks;
import cortexmodders.atomtech.element.ElementCollection;
import cortexmodders.atomtech.handlers.PacketHandler;
import cortexmodders.atomtech.lib.ATProperties;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ATProperties.ID, name = ATProperties.NAME, version = ATProperties.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"AtomTech"}, packetHandler = PacketHandler.class)
public class AtomTech
{

    public static ElementCollection elements;
    
    public static CreativeTabs atomTab = new ATCreativeTab("AtomTech");
	
	@SidedProxy(clientSide = "cortexmodders.atomtech.client.ClientProxy", serverSide = "cortexmodders.atomtech.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance
	public static AtomTech instance;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		proxy.regesterBlockIds(config);
		
		config.save();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		
		proxy.addElementsJson();
		proxy.registerTileEntities();
		
		ModBlocks.init();
	}
	
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
}