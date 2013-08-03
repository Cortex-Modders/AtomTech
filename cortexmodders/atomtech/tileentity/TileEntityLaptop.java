package cortexmodders.atomtech.tileentity;

import cortexmodders.atomtech.lib.ItemIds;
import net.minecraft.item.Item;
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
    private final float lidAngleOpen = -270.0F;
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
        lidAngleX = tag.getFloat("lidAngle");
        int itemId = tag.getInteger("itemId");
        if(itemId == ItemIds.FLASH_DRIVE)
        {
        	flashDrive = new ItemStack(Item.itemsList[itemId]);
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setByte("data", data);
        tag.setFloat("lidAngle", lidAngleX);
        if(flashDrive != null)
        {
        	tag.setInteger("itemId", flashDrive.itemID);
        }
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
    	return (data & 0b1000) != 0;
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
    
    @Override
    public boolean canRecievePower()
    {
        return true;
    }
}