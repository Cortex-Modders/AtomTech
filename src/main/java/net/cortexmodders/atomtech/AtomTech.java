package net.cortexmodders.atomtech;

import net.cortexmodders.atomtech.blocks.ModBlocks;
import net.cortexmodders.atomtech.element.ElementCollection;
import net.cortexmodders.atomtech.handlers.PacketHandler;
import net.cortexmodders.atomtech.item.ModItems;
import net.cortexmodders.atomtech.lib.ATProperties;
import net.cortexmodders.atomtech.lib.Recipes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
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
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "AtomTech" }, packetHandler = PacketHandler.class)
public class AtomTech
{
    
    public static ElementCollection elements;
    
    public static CreativeTabs atomTab = new ATCreativeTab("AtomTech");
    
    @SidedProxy(clientSide = "net.cortexmodders.atomtech.client.ClientProxy", serverSide = "net.cortexmodders.atomtech.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance
    public static AtomTech instance;
    
    @EventHandler
    public void load(final FMLInitializationEvent event)
    {
        ModBlocks.init();
        ModItems.init();
        Recipes.init();
        
        proxy.addElementsJson();
        proxy.registerTileEntities();
        proxy.registerRenderers();
        proxy.addOreDictionaryOres();
        proxy.registerGuis();
        
        GameRegistry.registerWorldGenerator(new WorldGenAtomTech());
        
        MinecraftForge.setBlockHarvestLevel(ModBlocks.ores, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.ores, 1, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.ores, 2, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.ores, 3, "pickaxe", 2);
    }
    
    @EventHandler
    public void postLoad(final FMLPostInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void preLoad(final FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        proxy.registerBlockIds(config);
        proxy.registerItemIds(config);
        
        config.save();
    }
}
