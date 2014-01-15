package net.cortexmodders.atomtech.item.circuits;

public abstract class ElectricalComponent
{
	protected boolean fried;
	protected double maxAmperes, maxVolts, providedResistance;
	
	public ElectricalComponent(double maxAmps, double maxVolts, double resistance)
	{
		maxAmperes = maxAmps;
		this.maxVolts = maxVolts;
		providedResistance = resistance;
		fried = false;
	}
	
	// TODO name it something better
	public abstract ElectricalUnit process(ElectricalUnit electricity);
	
	public ElectricalUnit tick(ElectricalUnit electricity)
	{
		tryToFry(electricity);
		if(fried)
			return null;
		return process(electricity);
	}
	
	public void tryToFry(ElectricalUnit electricity)
	{
		if(shouldFry(electricity) || fried)
		{
			fry();
		}
	}
	
	public boolean shouldFry(ElectricalUnit electricity)
	{
		if(electricity.volts() > maxVolts || electricity.amperes() > maxAmperes)
			return true;
		return false;
	}
	
	public void fry()
	{
		fried = true;
	}
	
	public boolean isFried()
	{
		return fried;
	}
	
	public double getMaxWatts()
	{
		return maxAmperes * maxVolts;
	}
}