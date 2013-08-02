package cortexmodders.atomtech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cortexmodders.atomtech.blocks.ModBlocks;
import cortexmodders.atomtech.element.ElementCollection;
import cortexmodders.atomtech.handlers.PacketHandler;
import cortexmodders.atomtech.lib.ATProperties;
import cortexmodders.atomtech.tileentity.TileEntityBattery;
import cortexmodders.atomtech.tileentity.TileEntityCable;
import cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

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
		
		ModBlocks.init();
		
		GameRegistry.registerTileEntity(TileEntityCable.class, "tileEntityCable");
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, "tileEntityCoalGenerator");
		GameRegistry.registerTileEntity(TileEntityBattery.class, "tileEntityBattery");
	}
	
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
}