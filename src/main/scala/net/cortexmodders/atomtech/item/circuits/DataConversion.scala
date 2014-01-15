package net.cortexmodders.atomtech.item.circuits

class DataConversion
{
	def getBytes(bits: Double) = bits / 8.0D
	
	def getKiloBytes(bits: Double) = getBytes(bits) / 1024.0D
	
	def getMegaBytes(bits: Double) = getKiloBytes(bits) / 1024.0D
	
	def getGigaBytes(bits: Double) = getMegaBytes(bits) / 1024.0D
	
	def getTeraBytes(bits: Double) = getGigaBytes(bits) / 1024.0D
	
	def getPetaBytes(bits: Double) = getTeraBytes(bits) / 1024.0D
	
	def getExaBytes(bits: Double) = getPetaBytes(bits) / 1024.0D
	
	def getZettaBytes(bits: Double) = getExaBytes(bits) / 1024.0D
	
	def getYottaBytes(bits: Double) = getZettaBytes(bits) / 1024.0D
}