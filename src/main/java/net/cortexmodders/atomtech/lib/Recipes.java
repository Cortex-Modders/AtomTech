package cortexmodders.atomtech.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cortexmodders.atomtech.blocks.ModBlocks;
import cortexmodders.atomtech.item.ItemATIngot;
import cortexmodders.atomtech.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
		registerSmelting();
		registerItemRecipes();
		registerBlockRecipes();
	}
	
	public static void registerSmelting()
	{
		for(int i = 0; i < ItemATIngot.unlocalizedNames.length; i++)
			FurnaceRecipes.smelting().addSmelting(ModBlocks.ores.blockID, i, new ItemStack(ModItems.ingots, 1, i), 0.5F);
	}
	
	public static void registerItemRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(ModItems.flashDrive, 1), new Object[] {
    		"  *", " # ", "i  ", Character.valueOf('*'), Item.ingotIron, Character.valueOf('#'), Item.redstoneRepeater, Character.valueOf('i'), new ItemStack(Item.dyePowder, 1, 0)
    	});
	}
	
	public static void registerBlockRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(ModBlocks.coalGenerator, 1), new Object[] {
    		"*#*", "*f*", "***", Character.valueOf('*'), Item.ingotIron, Character.valueOf('#'), ModBlocks.cable, Character.valueOf('f'), Block.furnaceIdle
    	});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.cable, 6), new Object[] {
    		"***", "###", "***", Character.valueOf('*'), Block.cloth, Character.valueOf('#'), new ItemStack(ModItems.ingots, 1, 0)
    	});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.battery, 1), new Object[] {
    		"***", "#l#", "*l*", Character.valueOf('*'), new ItemStack(ModItems.ingots, 0, 4), Character.valueOf('#'), ModBlocks.cable, Character.valueOf('l'), new ItemStack(ModItems.ingots, 0, 3)
    	});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.laptop, 1), new Object[] {
    		"*g ", "*g ", "#**", Character.valueOf('*'), new ItemStack(ModItems.ingots, 0, 4), Character.valueOf('#'), ModBlocks.battery, Character.valueOf('g'), Block.glass
    	});
	}
}