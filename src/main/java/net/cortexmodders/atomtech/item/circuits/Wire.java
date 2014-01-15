package net.cortexmodders.atomtech.item.circuits;

public class Wire extends ElectricalComponent
{
	public Wire(double maxAmps, double maxVolts)
	{
		super(maxAmps, maxVolts, 0.0D);
	}
	
	public ElectricalUnit process(ElectricalUnit electricity)
	{
		return electricity;
	}
}