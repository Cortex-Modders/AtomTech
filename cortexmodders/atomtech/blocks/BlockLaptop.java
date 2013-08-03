package cortexmodders.atomtech.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cortexmodders.atomtech.AtomTech;
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
    	if(!world.isRemote)
    	{
			TileEntityLaptop tile = (TileEntityLaptop) world.getBlockTileEntity(x, y, z);
			if(tile != null)
			{
				if(player.getHeldItem() != null && player.getHeldItem().getItem() != null && player.getHeldItem().getItem().equals(Item.stick))
				{
					tile.toggleHasFlashDrive();
    			}
				else
				{
					tile.toggleLid();
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
}