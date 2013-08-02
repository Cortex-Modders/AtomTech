package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntityLaptop extends TilePoweredBase
{
    private byte data = 0b100;

    public float lidAngleX = -180.0F;
    private final float lidAngleOpen = -80.0F;
    private final float lidAngleClosed = -180.0F;
    
    public TileEntityLaptop()
    {
        super(20);
    }
    
    @Override
    public void updateEntity() {
        if(isLidClosed() & lidAngleX != lidAngleClosed) {
            lidAngleX += 3.0F;
        }
        else if(!isLidClosed() & lidAngleX != lidAngleOpen) {
            lidAngleX -= 3.0F;
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.customParam1;
        this.readFromNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        data = tag.getByte("data");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setByte("data", data);
    }
    
    public boolean isLidClosed()
    {
    	return (data & 0b100) != 0;
    }
    
    public void setLidClosed(boolean b) 
    {
        if(b)
        {
                data |= 0b100;
         }
         else
         {
                data &= ~0b100;
          }
    }
    
    public void toggleLid() {
        setLidClosed(!isLidClosed());
    }
    /**
     * condition of laptop. 0 = best, 1 = ok, 2 = broken.
     */
    public byte getCondition()
    {
    	return (byte) (data & 0b11);
    }
    
    /**
     * makes the laptop more broken.
     * 
     */
    public void degradeCondition()
    {
        if(getCondition() < 2)
        	// It's ok to use data++ for the condition because it takes up the first 2 bits
        	// TODO change to a more acceptable way of changing it
        	data++;
    }
    
    /**
     * returns if laptop is broken or not.
     * 
     * @return
     */
    public boolean isBroken()
    {
        return (data & 0b11) >= 2;
    }
    
    @Override
    public boolean canRecievePower()
    {
        return true;
    }
}