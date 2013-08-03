package cortexmodders.atomtech.tileentity;

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
		//if(!worldObj.isRemote)
			//System.out.println(fuelLevel);
		if(fuelLevel > 0)
		{
			if(fuelLevel % 10 == 0)
			{
				powerLevel = 1;
				sendPower(xCoord + 1, yCoord, zCoord);
				sendPower(xCoord - 1, yCoord, zCoord);
				sendPower(xCoord, yCoord + 1, zCoord);
				sendPower(xCoord, yCoord - 1, zCoord);
				sendPower(xCoord, yCoord, zCoord + 1);
				sendPower(xCoord, yCoord, zCoord - 1);
				powerLevel = 0;
			}
			fuelLevel--;
		}
		else if(fuelLevel < 0)
		{
			fuelLevel = 0;
		}
	}
	
	public void addFuel(int fuel) {
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