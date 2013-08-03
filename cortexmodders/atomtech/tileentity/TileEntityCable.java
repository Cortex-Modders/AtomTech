package cortexmodders.atomtech.tileentity;


public class TileEntityCable extends TilePoweredBase
{
	public TileEntityCable()
	{
		super(10);
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
			System.out.println(powerLevel + " " + xCoord + " " + yCoord + " " + zCoord);
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