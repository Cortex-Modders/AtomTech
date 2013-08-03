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