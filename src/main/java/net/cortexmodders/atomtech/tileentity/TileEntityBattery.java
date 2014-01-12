package net.cortexmodders.atomtech.tileentity;


public class TileEntityBattery extends TilePoweredBase
{
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
			System.out.println(powerLevel);
		super.updateEntity();
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