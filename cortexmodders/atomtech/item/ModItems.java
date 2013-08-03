package cortexmodders.atomtech.item;

import net.minecraft.item.Item;
import cortexmodders.atomtech.lib.ItemIds;
import cpw.mods.fml.common.registry.LanguageRegistry;

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