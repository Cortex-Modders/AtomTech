package net.cortexmodders.atomtech.item.circuits;

public class Memory
{
	private long bits;
	
	public Memory(Wire wire, Memory[] memoryModules)
	{
		if(wire != null)
		{
			for(Memory memory : memoryModules)
			{
				bits += memory.bits;
			}
		}
		else
			throw new IllegalArgumentException("Wire cannot be null");
	}
	
	public Memory(Wire wire, Transistor[] transistors)
	{
		if(wire != null)
		{
			for(Transistor t : transistors)
			{
				if(t != null)
					bits++;
			}
		}
		else
			throw new IllegalArgumentException("Wire cannot be null");
	}
	
	public Memory(Transistor t)
	{
		if(t != null)
			bits = 1;
		else
			throw new IllegalArgumentException("Transistor cannot be null");
	}
	
	public long size()
	{
		return bits;
	}
}