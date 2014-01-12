package net.cortexmodders.atomtech.item.circuits.components

abstract class ElectricalComponent(val maxAmperes: Double, val maxVolts: Double, val providedResistance: Double)
{
	def maxWatts = maxAmperes * maxVolts
}

class Transistor(amps: Double, volts: Double) extends ElectricalComponent(amps, volts, 0.0D)
{
	
}