package net.cortexmodders.atomtech.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.cortexmodders.atomtech.item.ModItems;
import net.cortexmodders.atomtech.tileentity.TileEntityLaptop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
        EntityPlayer sender = (EntityPlayer) player;
        
        if(packet.channel.equals("AtomTech"))
        {
        	try
        	{
        		byte id = data.readByte();
        		if(id == 0)
        			syncLaptop(data, sender.worldObj);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }
	
	public void syncLaptop(DataInputStream data, World world) throws Exception
	{
		int x = data.readInt();
		int y = data.readInt();
		int z = data.readInt();
		
		TileEntityLaptop tile = (TileEntityLaptop) world.getBlockTileEntity(x, y, z);
		tile.setData(data.readByte());
	}
}