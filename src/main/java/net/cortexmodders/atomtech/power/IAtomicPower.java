package net.cortexmodders.atomtech.power;

import net.minecraft.util.Vec3;

public interface IAtomicPower
{
    
    /**
     * adds power and returns the remainder of the power not used.
     * 
     * @param power
     * @return
     */
    float addPower(float power);
    
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
    
    float getMaxPower();
    
    /**
     * returns the current power.
     * 
     * @return
     */
    float getPower();
    
    float getPowerPercentage();
    
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
    
    void setPower(float power);
}
