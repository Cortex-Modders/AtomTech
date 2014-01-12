package net.cortexmodders.atomtech.item;

import net.cortexmodders.atomtech.blocks.BlockATOre;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockATOre extends ItemBlock
{
    
    public ItemBlockATOre(final int id)
    {
        super(id);
        this.setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(final int i)
    {
        return i;
    }
    
    @Override
    public String getUnlocalizedName(final ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + BlockATOre.unlocalizedNames[stack.getItemDamage()];
    }
}
