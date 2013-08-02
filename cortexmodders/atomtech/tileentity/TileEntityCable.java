package cortexmodders.atomtech.tileentity;

import cortexmodders.atomtech.power.IAtomicPower;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TileEntityCable extends TileEntity implements IAtomicPower
{
	private int powerLevel = 0;
	private Vec3 sourceLoc = null;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(powerLevel > 0)
		{
			sendPower(xCoord + 1, yCoord, zCoord);
			sendPower(xCoord - 1, yCoord, zCoord);
			sendPower(xCoord, yCoord + 1, zCoord);
			sendPower(xCoord, yCoord - 1, zCoord);
			sendPower(xCoord, yCoord, zCoord + 1);
			sendPower(xCoord, yCoord, zCoord - 1);
		}
	}
	
	public void addPower(int power)
	{
		powerLevel += power;
	}
	
	public int getPower()
	{
		return powerLevel;
	}
	
	public void resetPowerLevel()
	{
		powerLevel = 0;
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
		this.powerLevel = tag.getInteger("power");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("power", powerLevel);
	}
	
	@Override
	public void onPowerRecieved(Vec3 sourceLoc)
	{
		IAtomicPower source = ((IAtomicPower)worldObj.getBlockTileEntity((int) sourceLoc.xCoord, (int) sourceLoc.yCoord, (int) sourceLoc.zCoord));
		if(source != null)
		{
			addPower(source.getPower());
			this.sourceLoc = sourceLoc;
		}
	}
	
	@Override
	public void sendPower(int x, int y, int z)
	{
		if(sourceLoc.xCoord != x && sourceLoc.yCoord != y && sourceLoc.zCoord != z)
		{
			Vec3 powerSource = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
			if(worldObj.getBlockTileEntity(x, y, z) instanceof IAtomicPower)
			{
				IAtomicPower target = (IAtomicPower) worldObj.getBlockTileEntity(x, y, z);
				target.onPowerRecieved(powerSource);
				resetPowerLevel();
			}
		}
	}
}