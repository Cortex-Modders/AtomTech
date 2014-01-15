package net.cortexmodders.atomtech;

import net.cortexmodders.atomtech.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;

public class ATCreativeTab extends CreativeTabs
{
    
    public ATCreativeTab(final String name)
    {
        super(name);
    }
    
    @Override
    public int getTabIconItemIndex()
    {
        // block id of block that is on the tab
        return ModBlocks.coalGenerator.blockID;
    }
    
    @Override
    public String getTranslatedTabLabel()
    {
        return "Atom Tech";
    }
}
