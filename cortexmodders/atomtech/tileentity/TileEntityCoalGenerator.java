package cortexmodders.atomtech.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntityCoalGenerator extends TilePoweredBase
{
	private int fuelLevel = 0;
	
	@Override
	public void updateEntity()
	{
		if(fuelLevel > 0)
		{
			if(!worldObj.isRemote && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) < 4)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) + 4, 3);
			}
			if(fuelLevel % 10 == 0)
			{
				powerLevel = 1;
				super.updateEntity();
				powerLevel = 0;
			}
			fuelLevel--;
		}
		else if(fuelLevel < 0)
		{
			fuelLevel = 0;
		}
		else if(!worldObj.isRemote && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) > 3 && fuelLevel == 0)
		{
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) - 4, 3);
		}
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	public void addFuel(int fuel)
	{
	    fuelLevel += fuel;
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.fuelLevel = tag.getInteger("fuel");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("fuel", fuelLevel);
	}
	
	@Override
	public boolean canSendPower()
	{
		return true;
	}
}