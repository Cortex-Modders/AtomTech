package net.cortexmodders.atomtech.item;

import java.util.List;

import net.cortexmodders.atomtech.AtomTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemATIngot extends Item
{
    
    private static Icon[] icons;
    public final static String[] unlocalizedNames = new String[] { "Cuprum Ingot", "Plumbum Ingot", "Uranium Ingot", "Lithium Ingot", "Aluminium Ingot" };
    
    public ItemATIngot(final int par1)
    {
        super(par1);
        this.setCreativeTab(AtomTech.atomTab);
        this.hasSubtypes = true;
    }
    
    @Override
    public Icon getIconFromDamage(final int i)
    {
        return icons[i];
    }
    
    @Override
    public int getMetadata(final int i)
    {
        return i;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(final int id, final CreativeTabs tabs, final List list)
    {
        for (int i = 0; i < unlocalizedNames.length; i++)
            list.add(new ItemStack(id, 1, i));
    }
    
    @Override
    public String getUnlocalizedName(final ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + unlocalizedNames[stack.getItemDamage()];
    }
    
    @Override
    public void registerIcons(final IconRegister iconRegister)
    {
        icons = new Icon[unlocalizedNames.length];
        for (int i = 0; i < icons.length; i++)
            icons[i] = iconRegister.registerIcon("atomtech:" + unlocalizedNames[i].replace(" ", "").toLowerCase());
    }
}
