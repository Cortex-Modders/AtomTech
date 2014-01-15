package net.cortexmodders.atomtech.item.circuits;

public class ElectricalUnit
{
	private double amperes, volts;
	
	public ElectricalUnit(double amps, double volts)
	{
		amperes = amps;
		this.volts = volts;
	}
	
	public double amperes()
	{
		return amperes;
	}
	
	public double volts()
	{
		return volts;
	}
}