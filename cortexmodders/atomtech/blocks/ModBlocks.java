package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.lib.BlockIds;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class ModBlocks
{
	public static Block cable;
	
	public static void init()
	{
		cable = new BlockCable(BlockIds.CABLE).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("cable");
		
		registerBlocks();
		registerLang();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(cable, "Cable");
	}
	
	public static void registerLang()
	{
		LanguageRegistry.addName(cable, "Cable");
	}
}