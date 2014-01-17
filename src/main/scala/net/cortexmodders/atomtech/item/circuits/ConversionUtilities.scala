package net.cortexmodders.atomtech.item.circuits

class ConversionUtilities
{
	object Bytes
	{
		def getBytes(bits: Long) = bits / 8.0D
		
		def getKiloBytes(bits: Long) = getBytes(bits) / 1024.0D
		
		def getMegaBytes(bits: Long) = getKiloBytes(bits) / 1024.0D
		
		def getGigaBytes(bits: Long) = getMegaBytes(bits) / 1024.0D
		
		def getTeraBytes(bits: Long) = getGigaBytes(bits) / 1024.0D
		
		def getPetaBytes(bits: Long) = getTeraBytes(bits) / 1024.0D
		
		def getExaBytes(bits: Long) = getPetaBytes(bits) / 1024.0D
		
		def getZettaBytes(bits: Long) = getExaBytes(bits) / 1024.0D
		
		def getYottaBytes(bits: Long) = getZettaBytes(bits) / 1024.0D
	}
	
	object Hertz
	{
		def getKiloHertz(hertz: Long) = hertz / 1000.0D
		
		def getMegaHertz(hertz: Long) = getKiloHertz(hertz) / 1000.0D
		
		def getGigaHertz(hertz: Long) = getMegaHertz(hertz) / 1000.0D
		
		def getTeraHertz(hertz: Long) = getGigaHertz(hertz) / 1000.0D
		
		def getPetaHertz(hertz: Long) = getTeraHertz(hertz) / 1000.0D
		
		def getExaHertz(hertz: Long) = getPetaHertz(hertz) / 1000.0D
		
		def getZettaHertz(hertz: Long) = getExaHertz(hertz) / 1000.0D
		
		def getYottaHertz(hertz: Long) = getZettaHertz(hertz) / 1000.0D
	}
}