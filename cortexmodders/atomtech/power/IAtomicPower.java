package cortexmodders.atomtech.power;

import net.minecraft.util.Vec3;

public interface IAtomicPower
{
    /**
     * this is called when power is recieved.
     * 
     * @param sourceLoc
     */
	void onPowerRecieved(Vec3 sourceLoc);
	
	/**
	 * sends power to a certain set of coordinates
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	void sendPower(int x, int y, int z);
	
	/**
	 * returns the current power.
	 * 
	 * @return
	 */
	int getPower();
	
	void setPower(int power);
	
	/**
	 * adds power and returns the remainder of the power not used.
	 * 
	 * @param power
	 * @return
	 */
	int addPower(int power);
	
	/**
	 * whether or not power may be received by this block.
	 * 
	 * @return
	 */
	boolean canRecievePower();
	
	/**
	 * whether or not power may be sent by this block.
	 * 
	 * @return
	 */
	boolean canSendPower();
}