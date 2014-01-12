package net.cortexmodders.atomtech.item;

import net.cortexmodders.atomtech.blocks.BlockATOre;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockATOre extends ItemBlock {

    public ItemBlockATOre(int id) {
        super(id);
        setHasSubtypes(true);
    }
    
    public int getMetadata(int i) {
        return i;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + BlockATOre.unlocalizedNames[stack.getItemDamage()];
    }
}
