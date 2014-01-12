package net.cortexmodders.atomtech.item;

import net.cortexmodders.atomtech.AtomTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFlashDrive extends Item
{
	public ItemFlashDrive(int par1)
	{
		super(par1);
		this.setCreativeTab(AtomTech.atomTab);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("atomtech:flashdrive");
	}
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		
	}
}