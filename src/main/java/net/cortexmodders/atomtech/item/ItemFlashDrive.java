package net.cortexmodders.atomtech.item;

import net.cortexmodders.atomtech.AtomTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFlashDrive extends Item
{
    
    public ItemFlashDrive(final int par1)
    {
        super(par1);
        this.setCreativeTab(AtomTech.atomTab);
        this.setMaxStackSize(1);
    }
    
    @Override
    public void onCreated(final ItemStack stack, final World world, final EntityPlayer player)
    {
        
    }
    
    @Override
    public void registerIcons(final IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("atomtech:flashdrive");
    }
}
