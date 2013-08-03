package cortexmodders.atomtech.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cortexmodders.atomtech.AtomTech;

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
}