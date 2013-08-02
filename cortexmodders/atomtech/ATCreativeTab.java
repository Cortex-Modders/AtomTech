package cortexmodders.atomtech;

import net.minecraft.creativetab.CreativeTabs;

public class ATCreativeTab extends CreativeTabs
{
	public ATCreativeTab(String name)
	{
		super(name);
	}
	
	public int getTabIconItemIndex()
	{
		// block id of block that is on the tab
		return 54;
	}
	
	public String getTranslatedTabLabel()
	{
		return "Atom Tech";
	}
}