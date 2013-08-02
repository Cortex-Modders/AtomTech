package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public class TileEntityCoalGenerator extends TilePoweredBase implements IAtomicPower
{
	private int fuelLevel = 0;
	private byte powerLevel = 0;
	
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
		this.powerLevel = tag.getByte("power");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("fuel", fuelLevel);
		tag.setByte("power", powerLevel);
	}
	
	@Override
	public void onPowerRecieved(Vec3 sourceLoc)
	{
		
	}
	
	@Override
	public void sendPower(int x, int y, int z)
	{
		Vec3 powerSource = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
		if(worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
		{
			IAtomicPower target = (IAtomicPower) worldObj.getBlockTileEntity(x, y, z);
			if(target.canRecievePower())
			{
				powerLevel = 0;
				target.onPowerRecieved(powerSource);
			}
		}
	}
	
	@Override
	public int getPower()
	{
		return powerLevel;
	}
	
	@Override
	public boolean canRecievePower()
	{
		return false;
	}
	
	@Override
	public boolean canSendPower()
	{
		return true;
	}
}