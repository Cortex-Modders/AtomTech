package cortexmodders.atomtech.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cortexmodders.atomtech.blocks.BlockATOre;

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
