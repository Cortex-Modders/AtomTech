package cortexmodders.atomtech.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cortexmodders.atomtech.blocks.BlockATOre;
import cortexmodders.atomtech.lib.ItemIds;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModItems
{
	public static Item flashDrive;
	public static Item ingots;
	
	public static void init()
	{
		flashDrive = new ItemFlashDrive(ItemIds.FLASH_DRIVE).setUnlocalizedName("flashDrive");
		ingots = new ItemATIngot(ItemIds.INGOTS);
		
		registerLang();
	}
	
	public static void registerLang()
	{
		LanguageRegistry.addName(flashDrive, "Flash Drive");
		for(int i = 0; i < ItemATIngot.unlocalizedNames.length; i++)
		{
			LanguageRegistry.addName(new ItemStack(ingots, 1, i), ItemATIngot.unlocalizedNames[i]);
		}
	}
}