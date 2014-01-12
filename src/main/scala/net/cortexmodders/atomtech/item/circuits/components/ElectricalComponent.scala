package net.cortexmodders.atomtech.item.circuits.components

import net.minecraft.item.Item

class ElectricalComponentWrapper(id: Integer) extends Item(id)
{
	
}

class ElectricalUnit(val amperes: Double, val volts: Double)
{
	object Utilities {
		def getResistance(start: ElectricalUnit, end: ElectricalUnit) = {
			start.volts / start.amperes - end.volts / end.amperes
		}
	}
}

abstract class ElectricalComponent(val maxAmperes: Double, val maxVolts: Double, val providedResistance: Double)
{
	protected var fried = false
	// TODO name it something better
	def process(electricity: ElectricalUnit): ElectricalUnit
	def shouldFry(electricity: ElectricalUnit) = {
		if(electricity.volts > maxVolts || electricity.amperes > maxAmperes)
			true
		false
	}
	def fry = fried = true
	def isFried = fried
	def maxWatts = maxAmperes * maxVolts
}

class Wire(maxAmps: Double, maxVolts: Double) extends ElectricalComponent(maxAmps, maxVolts, 0.0D)
{
	def process(electricity: ElectricalUnit) = {
		if(shouldFry(electricity))
		{
			fry
			null
		}
		electricity
	}
}

class Transistor(val minVolts: Double, maxAmps: Double, maxVolts: Double) extends ElectricalComponent(maxAmps, maxVolts, 0.0D)
{
	var on = false
	// TODO
	def process(electricity: ElectricalUnit) = {
		if(shouldFry(electricity))
		{
			fry
			null
		}
		if(electricity.volts > minVolts)
			on = true
		else
			on = false
		electricity
	}
}