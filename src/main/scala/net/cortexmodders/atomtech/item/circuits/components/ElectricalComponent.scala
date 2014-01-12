package net.cortexmodders.atomtech.item.circuits.components

import net.minecraft.item.Item

class ElectricalComponentWrapper(id: Integer) extends Item(id)
{
	
}

abstract class ElectricalComponent(val maxAmperes: Double, val maxVolts: Double, val providedResistance: Double)
{
	protected var fried = false
	// TODO name it something better
	def process(amps: Double, volts: Double)
	def fry = fried = true
	def isFried = fried
	def maxWatts = maxAmperes * maxVolts
}

class Transistor(val minVolts: Double, maxAmps: Double, maxVolts: Double) extends ElectricalComponent(maxAmps, maxVolts, 0.0D)
{
	var on = false
	// TODO
	def process(amps: Double, volts: Double) = {
		
	}
}