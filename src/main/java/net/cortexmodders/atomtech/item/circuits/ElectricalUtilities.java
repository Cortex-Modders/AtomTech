package net.cortexmodders.atomtech.item.circuits;

public class ElectricalUtilities
{
	public static double getResistance(ElectricalUnit start, ElectricalUnit end)
	{
		return start.volts() / start.amperes() - end.volts() / end.amperes();
	}
}