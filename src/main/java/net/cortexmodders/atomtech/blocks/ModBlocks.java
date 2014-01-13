package net.cortexmodders.atomtech.blocks;

import net.cortexmodders.atomtech.item.ItemBlockATOre;
import net.cortexmodders.atomtech.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
        laptop = new BlockLaptop(BlockIds.LAPTOP).setHardness(2.0F).setResistance(3.0F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("laptop");
        ores = new BlockOre(BlockIds.ORES).setHardness(4.0F).setResistance(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ores");
        
        registerBlocks();
        registerLang();
    }
    
    public static void registerBlocks()
    {
        GameRegistry.registerBlock(cable, "Cable");
        GameRegistry.registerBlock(coalGenerator, "CoalGenerator");
        GameRegistry.registerBlock(battery, "Battery");
        GameRegistry.registerBlock(laptop, "Laptop");
        GameRegistry.registerBlock(ores, ItemBlockATOre.class, "ores");
    }
    
    public static void registerLang()
    {
        LanguageRegistry.addName(cable, "Cable");
        LanguageRegistry.addName(coalGenerator, "Coal Generator");
        LanguageRegistry.addName(battery, "Battery");
        LanguageRegistry.addName(laptop, "Laptop");
        for (int i = 0; i < BlockOre.unlocalizedNames.length; i++)
            LanguageRegistry.addName(new ItemStack(ores, 1, i), BlockOre.unlocalizedNames[i]);
    }
}
