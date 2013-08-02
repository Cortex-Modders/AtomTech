package cortexmodders.atomtech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import cortexmodders.atomtech.blocks.ModBlocks;
import cortexmodders.atomtech.handlers.PacketHandler;
import cortexmodders.atomtech.lib.BlockIds;
import cortexmodders.atomtech.tileentity.TileEntityCable;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "AtomTech", name = "Atom Tech", version = "0.1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"AtomTech"}, packetHandler = PacketHandler.class)
public class AtomTech
{
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
		
		BlockIds.CABLE = config.getBlock("cable", BlockIds.CABLE_DEF).getInt();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		ModBlocks.init();
		
		GameRegistry.registerTileEntity(TileEntityCable.class, "tileEntityCable");
	}
	
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
}