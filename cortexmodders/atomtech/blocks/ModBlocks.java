package cortexmodders.atomtech.blocks;

import net.minecraft.block.Block;
import cortexmodders.atomtech.item.ItemATOre;
import cortexmodders.atomtech.lib.BlockIds;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModBlocks
{
	public static Block cable;
	public static Block coalGenerator;
	public static Block battery;
	public static Block laptop;
	public static Block ores;
	
	public static void init()
	{
		cable = new BlockCable(BlockIds.CABLE).setResistance(1.0F).setHardness(1.0F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("cable");
		coalGenerator = new BlockCoalGenerator(BlockIds.COAL_GENERATOR).setResistance(4.0F).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("coalGenerator");
		battery = new BlockBattery(BlockIds.BATTERY).setResistance(3.0F).setHardness(4.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("battery");
		laptop = new BlockLaptop(BlockIds.LAPTOP).setResistance(3.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("laptop");
		ores = new BlockATOre(BlockIds.ORES).setResistance(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ores");
		
		registerBlocks();
		registerLang();
	}
	
    public static void registerBlocks()
	{
		GameRegistry.registerBlock(cable, "Cable");
		GameRegistry.registerBlock(coalGenerator, "CoalGenerator");
		GameRegistry.registerBlock(battery, "Battery");
		GameRegistry.registerBlock(laptop, "Laptop");
		GameRegistry.registerBlock(ores, ItemATOre.class, "ores");
	}
	
	public static void registerLang()
	{
		LanguageRegistry.addName(cable, "Cable");
		LanguageRegistry.addName(coalGenerator, "Coal Generator");
		LanguageRegistry.addName(battery, "Battery");
		LanguageRegistry.addName(laptop, "Laptop");
	}
}