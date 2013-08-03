package cortexmodders.atomtech.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cortexmodders.atomtech.AtomTech;
import cortexmodders.atomtech.item.ModItems;
import cortexmodders.atomtech.lib.RenderIds;
import cortexmodders.atomtech.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.network.PacketDispatcher;

public class BlockLaptop extends BlockContainer {

    public BlockLaptop(int id) {
        super(id, Material.iron);
        setCreativeTab(AtomTech.atomTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityLaptop();
    }
    
    @Override
    public int getRenderType() {
        return RenderIds.LAPTOP_RENDER_ID;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void registerIcons(IconRegister register)
    {
        this.blockIcon = register.registerIcon("atomtech:laptop");
    }
    
    @Override
    public void onFallenUpon(World par1World, int x, int y, int z, Entity entity, float par6)
    {
        if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            ((TileEntityLaptop)par1World.getBlockTileEntity(x, y, z)).degradeCondition();
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote && player != null)
    	{
			TileEntityLaptop tile = (TileEntityLaptop) world.getBlockTileEntity(x, y, z);
			if(tile != null)
			{
				ItemStack heldItem = player.getHeldItem();
				if(!tile.hasFlashDrive())
				{
					if(heldItem != null && heldItem.getItem() != null && heldItem.getItem().equals(ModItems.flashDrive))
					{
						tile.toggleHasFlashDrive();
						heldItem.stackSize--;
						
					}
					else
					{
						tile.toggleLid();
					}
				}
				else
				{
					if(heldItem == null)
					{
						tile.toggleHasFlashDrive();
						player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(ModItems.flashDrive);
					}
					else
					{
						tile.toggleLid();
					}
				}
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				DataOutputStream data = new DataOutputStream(bos);
				
				try
				{
					data.writeByte(0);
					data.writeInt(x);
					data.writeInt(y);
					data.writeInt(z);
					data.writeByte(tile.getData());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket("AtomTech", bos.toByteArray()));
				
				return true;
			}
    	}
    	return false;
    }
    
    @Override
    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
    	super.onBlockPlacedBy(par1World, x, y, z, par5EntityLiving, par6ItemStack);
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
        par1World.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		float minx = (float)this.minX;
		float miny = (float)this.minY;
		float minz = (float)this.minZ;
		float maxx = (float)this.maxX;
		float maxy = (float)this.maxY;
		float maxz = (float)this.maxZ;
		
		if(access.getBlockTileEntity(x, y, z) != null)
		{
			if(!((TileEntityLaptop)access.getBlockTileEntity(x, y, z)).isLidClosed())
			{
				maxy = 0.8125F;
			}
		}
		
		int meta = access.getBlockMetadata(x, y, z);
		if(meta == 0 || meta == 2)
		{
			minz = 0.125F;
			maxz = 0.875F;
		}
		else if(meta == 1 || meta == 3)
		{
			minx = 0.125F;
			maxx = 0.875F;
		}
		
		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
}