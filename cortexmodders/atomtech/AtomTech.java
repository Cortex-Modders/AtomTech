package cortexmodders.atomtech;

import cortexmodders.atomtech.handlers.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "AtomTech", name = "Atom Tech", version = "0.1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"AtomTech"}, packetHandler = PacketHandler.class)
public class AtomTech
{
	@SidedProxy(clientSide = "cortexmodders.atomtech.ClientProxy", serverSide = "cortexmodders.atomtech.CommonProxy")
	public static CommonProxy proxy;
	@Instance
	public static CodeLyoko instance;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void preLoad(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void preLoad(FMLPostInitializationEvent event)
	{
		
	}
}