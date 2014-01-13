package net.cortexmodders.atomtech.item.circuits;

public class DataConversion
{
	public static long getBytes(long bits)
	{
		return bits / 8;
	}
	
	public static long getKiloBytes(long bits)
	{
		return getBytes(bits) / 1024;
	}
	
	public static long getMegaBytes(long bits)
	{
		return getKiloBytes(bits) / 1024;
	}
	
	public static long getGigaBytes(long bits)
	{
		return getMegaBytes(bits) / 1024;
	}
	
	public static long getTeraBytes(long bits)
	{
		return getGigaBytes(bits) / 1024;
	}
	
	public static long getPetaBytes(long bits)
	{
		return getTeraBytes(bits) / 1024;
	}
	
	public static long getExaBytes(long bits)
	{
		return getPetaBytes(bits) / 1024;
	}
	
	public static long getZettaBytes(long bits)
	{
		return getExaBytes(bits) / 1024;
	}
	
	public static long getYottaBytes(long bits)
	{
		return getZettaBytes(bits) / 1024;
	}
}