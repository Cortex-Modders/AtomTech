package cortexmodders.atomtech.tileentity;

import cortexmodders.atomtech.blocks.BlockLaptop;
import cortexmodders.atomtech.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntityLaptop extends TilePoweredBase
{
    private byte data = 0b0100;
    public ItemStack flashDrive;
    
    public float lidAngleX = -180.0F;
    private final float lidAngleOpen = -276.0F;
    private final float lidAngleClosed = -180.0F;
    
    public TileEntityLaptop()
    {
        super(20);
    }
    
    @Override
    public void updateEntity()
    {
    	if(!isBroken())
    	{
    		if(isLidClosed() && lidAngleX != lidAngleClosed)
    		{
    			lidAngleX += 4.0F;
    		}
    		else if(!isLidClosed() && lidAngleX != lidAngleOpen)
    		{
    			lidAngleX -= 4.0F;
    		}
    	}
    	else
    	{
    		if(hasFlashDrive() && !worldObj.isRemote)
    		{
    			BlockLaptop.ejectFlashDrive(worldObj, xCoord, yCoord, zCoord);
    		}
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
        lidAngleX = tag.getFloat("lidAngle");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setByte("data", data);
        tag.setFloat("lidAngle", lidAngleX);
    }
    
    public boolean isLidClosed()
    {
    	return (data & 0b100) != 0;
    }
    
    public void setLidClosed(boolean closeLid) 
    {
    	if(closeLid)
    	{
    		data |= 0b100;
    	}
    	else
    	{
    		data &= ~0b100;
    	}
    }
    
    public void toggleLid()
    {
        setLidClosed(!isLidClosed());
    }
    
    /**
     * condition of laptop. 0 = best, 1 = ok, 2 = broken.
     */
    public byte getCondition()
    {
    	return (byte) (data & 0b11);
    }
    
    public void setCondition(byte condition)
    {
    	data &= ~0b11;
    	data |= condition & 0b11;
    }
    
    /**
     * makes the laptop more broken.
     * 
     */
    public void degradeCondition()
    {
        if(getCondition() < 2)
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
    
    public boolean hasFlashDrive()
    {
    	return (data & 0b1000) != 0 && flashDrive != null;
    }
    
    public void setHasFlashDrive(boolean hasFlashDrive)
    {
    	if(hasFlashDrive)
    	{
    		data |= 0b1000;
    	}
    	else
    	{
    		data &= ~0b1000;
    	}
    }
    
    public void toggleHasFlashDrive()
    {
    	setHasFlashDrive(!hasFlashDrive());
    }
    
    public byte getData()
    {
    	return data;
    }
    
    public void setData(byte data)
    {
    	this.data = data;
    }
    
    public void fix()
    {
    	data &= ~0b11;
    }
    
    @Override
    public boolean canRecievePower()
    {
        return !isBroken();
    }
}