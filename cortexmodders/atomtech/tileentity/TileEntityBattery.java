package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public class TileEntityBattery extends TilePoweredBase
{
	private int powerLevel = 0;
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
			System.out.println(powerLevel);
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
		if(source != null && source.canSendPower())
		{
			addPower(source.getPower());
		}
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
				target.onPowerRecieved(powerSource);
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