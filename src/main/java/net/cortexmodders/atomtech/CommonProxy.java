package net.cortexmodders.atomtech;

import net.cortexmodders.atomtech.blocks.ModBlocks;
import net.cortexmodders.atomtech.element.ElementCollection;
import net.cortexmodders.atomtech.item.ModItems;
import net.cortexmodders.atomtech.lib.ATResources;
import net.cortexmodders.atomtech.lib.BlockIds;
import net.cortexmodders.atomtech.lib.ItemIds;
import net.cortexmodders.atomtech.tileentity.TileEntityBattery;
import net.cortexmodders.atomtech.tileentity.TileEntityCable;
import net.cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
	public void addElementsJson() {
	    ElementCollection elements = new ElementCollection();
	    elements.parseElementInfo(ATResources.ELEMENT_JSON_FILE);
	    AtomTech.elements = elements;
	}
	
	public void registerBlockIds(Configuration config) {
		BlockIds.CABLE = config.getBlock("cable", BlockIds.CABLE_DEF).getInt();
		BlockIds.COAL_GENERATOR = config.getBlock("coalGenerator", BlockIds.COAL_GENERATOR_DEF).getInt();
		BlockIds.BATTERY = config.getBlock("battery", BlockIds.BATTERY_DEF).getInt();
		BlockIds.LAPTOP = config.getBlock("laptop", BlockIds.BATTERY_DEF).getInt();
		BlockIds.ORES = config.getBlock("ores", BlockIds.ORES_DEF).getInt();
	}
	
	public void registerItemIds(Configuration config)
	{
		ItemIds.FLASH_DRIVE = config.getItem("flashDrive", ItemIds.FLASH_DRIVE_DEF).getInt();
		ItemIds.INGOTS = config.getItem("ingots", ItemIds.INGOTS_DEF).getInt();
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
	
	public void addOreDictionaryOres()
	{
		OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.ores, 1, 0));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.ingots, 1, 0));
		OreDictionary.registerOre("oreRadioactiveLead", new ItemStack(ModBlocks.ores, 1, 1));
		OreDictionary.registerOre("ingotRadioactiveLead", new ItemStack(ModItems.ingots, 1, 1));
		OreDictionary.registerOre("oreUranium", new ItemStack(ModBlocks.ores, 1, 2));
		OreDictionary.registerOre("ingotUranium", new ItemStack(ModItems.ingots, 1, 2));
		OreDictionary.registerOre("oreLithium", new ItemStack(ModBlocks.ores, 1, 3));
		OreDictionary.registerOre("ingotLithium", new ItemStack(ModItems.ingots, 1, 3));
		OreDictionary.registerOre("oreAluminium", new ItemStack(ModBlocks.ores, 1, 4));
		OreDictionary.registerOre("ingotAluminium", new ItemStack(ModItems.ingots, 1, 4));
	}
	
	public void registerGuis() {
	    NetworkRegistry.instance().registerGuiHandler(this, new ATGuiHandler());
	}
	
	public static boolean isVecInsideBB(Vec3 vec, AxisAlignedBB box) {
	    return (vec.xCoord >= box.minX && vec.xCoord <= box.maxX
	         && vec.yCoord >= box.minY && vec.yCoord <= box.maxY
	         && vec.zCoord >= box.minZ && vec.zCoord <= box.maxZ);
	}
}