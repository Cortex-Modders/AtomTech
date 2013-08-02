package cortexmodders.atomtech.blocks;

import cortexmodders.atomtech.AtomTech;
import cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class BlockCoalGenerator extends BlockContainer
{
	protected BlockCoalGenerator(int par1)
	{
		super(par1, Material.iron);
		this.setCreativeTab(AtomTech.atomTab);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote && world.getBlockTileEntity(x, y, z) != null && player != null)
		{
			ItemStack heldItem = player.getHeldItem();
			if(TileEntityFurnace.getItemBurnTime(heldItem) > 0)
			{
				TileEntityCoalGenerator coalGen = (TileEntityCoalGenerator)world.getBlockTileEntity(x, y, z);
				coalGen.addPower(TileEntityFurnace.getItemBurnTime(heldItem) / 8);
				heldItem.stackSize--;
				if(heldItem.stackSize <= 0)
				{
					heldItem = null;
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityCoalGenerator();
	}
}