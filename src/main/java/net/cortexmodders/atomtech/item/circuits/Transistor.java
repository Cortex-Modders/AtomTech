package net.cortexmodders.atomtech.item.circuits;

public class Transistor extends ElectricalComponent
{
	protected double minimumVolts;
	protected boolean on;
	
	public Transistor(double minVolts, double maxAmps, double maxVolts)
	{
		super(maxAmps, maxVolts, 0.0D);
		minimumVolts = minVolts;
		on = false;
	}
	
	@Override
	public ElectricalUnit tick(ElectricalUnit electricity)
	{
		tryToFry(electricity);
		if(fried)
		{
			on = false;
			return null;
		}
		return process(electricity);
	}
	
	// TODO
	public ElectricalUnit process(ElectricalUnit electricity)
	{
		on = electricity.volts() > minimumVolts;
		return electricity;
	}
}