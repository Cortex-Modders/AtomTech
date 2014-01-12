package net.cortexmodders.atomtech.item.circuits

abstract class Electricity(amount: Double)
{
	def getValue = amount
}

class Amp(amount: Double) extends Electricity(amount)
class Volt(amount: Double) extends Electricity(amount)
class Resistance(amount: Double) extends Electricity(amount)

class Range[T <: Electricity](minimum: T, maximum: T)
{
	private val minimumAmount = minimum getValue
	private val maximumAmount = maximum getValue
	def isInRange(value: T) = value.getValue > minimumAmount && value.getValue < maximumAmount
}