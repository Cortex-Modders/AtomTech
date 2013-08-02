package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public class TileEntityCable extends TilePoweredBase implements IAtomicPower
{
	private int powerLevel = 0;
	private Vec3 sourceLoc = null;
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
			System.out.println(powerLevel);
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
		this.sourceLoc = Vec3.createVectorHelper(tag.getDouble("xv"), tag.getDouble("yv"), tag.getDouble("zv"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("power", powerLevel);
		if(sourceLoc != null)
		{
			tag.setDouble("xv", sourceLoc.xCoord);
			tag.setDouble("yv", sourceLoc.yCoord);
			tag.setDouble("zv", sourceLoc.zCoord);
		}
	}
	
	@Override
	public void onPowerRecieved(Vec3 sourceLoc)
	{
		IAtomicPower source = ((IAtomicPower)worldObj.getBlockTileEntity((int) sourceLoc.xCoord, (int) sourceLoc.yCoord, (int) sourceLoc.zCoord));
		if(source != null && source.canSendPower())
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
				if(target.canRecievePower())
				{
					target.onPowerRecieved(powerSource);
					resetPowerLevel();
				}
			}
		}
	}
	
	@Override
	public boolean canRecievePower()
	{
		return true;
	}
	
	@Override
	public boolean canSendPower()
	{
		return true;
	}
}