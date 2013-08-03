package cortexmodders.atomtech.item;

import cortexmodders.atomtech.blocks.BlockATOre;
import cortexmodders.atomtech.blocks.BlockBattery;
import cortexmodders.atomtech.blocks.BlockCable;
import cortexmodders.atomtech.blocks.BlockCoalGenerator;
import cortexmodders.atomtech.blocks.BlockLaptop;
import cortexmodders.atomtech.lib.BlockIds;
import cortexmodders.atomtech.lib.ItemIds;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems
{
	public static Item flashDrive;
	
	public static void init()
	{
		flashDrive = new ItemFlashDrive(ItemIds.FLASH_DRIVE).setUnlocalizedName("flashDrive");
		
		registerLang();
	}
	
	public static void registerLang()
	{
		LanguageRegistry.addName(flashDrive, "Flash Drive");
	}
}