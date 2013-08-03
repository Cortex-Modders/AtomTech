package cortexmodders.atomtech.tileentity;

import cortexmodders.atomtech.blocks.BlockLaptop;
import cortexmodders.atomtech.blocks.ModBlocks;
import cortexmodders.atomtech.item.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityLaptop extends TilePoweredBase implements IInventory
{
    private byte data = 0b100;
    private ItemStack[] inv;
    
    public float lidAngleX = -180.0F;
    private final float lidAngleOpen = -276.0F;
    private final float lidAngleClosed = -180.0F;
    
    // local coords
    public final static AxisAlignedBB[] flashDriveHitPositions = {
    	//south
    	AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, -0.0625*2, -0.0625, -0.0625).getOffsetBoundingBox(-0.625F, 0.0F, -0.1875F),
    	AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, -0.0625*2, -0.0625, -0.0625).getOffsetBoundingBox(-0.625F, 0.0F, -0.1875F),
    	AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, -0.0625*2, -0.0625, -0.0625).getOffsetBoundingBox(-0.625F, 0.0F, -0.1875F),
    	AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, -0.0625*2, -0.0625, -0.0625).getOffsetBoundingBox(-0.625F, 0.0F, -0.1875F),
    };
    
    public TileEntityLaptop()
    {
        super(20);
        inv = new ItemStack[1];
    }
    
    @Override
    public void updateEntity()
    {
    	if(!worldObj.isRemote)
    	{
    		if(worldObj.getBlockId(xCoord, yCoord, zCoord) != ModBlocks.laptop.blockID)
    		{
    			worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
    		}
//    		System.out.println(hasFlashDrive());
    	}
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
        NBTTagList tagList = tag.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag2 = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag2.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag2);
			}
		}
        data = tag.getByte("data");
        lidAngleX = tag.getFloat("lidAngle");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setByte("data", data);
        tag.setFloat("lidAngle", lidAngleX);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setByte("Slot", (byte) i);
				stack.writeToNBT(tag2);
				itemList.appendTag(tag2);
			}
		}
		tag.setTag("Inventory", itemList);
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
    	return isItemValidForSlot(0, getStackInSlot(0));
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
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize <= amt)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
				player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	@Override
	public void openChest() {}
	
	@Override
	public void closeChest() {}
	
	@Override
	public String getInvName()
	{
		return "tileentitylaptop";
	}
	
	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		if(stack != null && stack.getItem().equals(ModItems.flashDrive) && slot == 0)
		{
			return true;
		}
		return false;
	}
	
	public AxisAlignedBB getFlashDriveBoundingBox(int x, int y, int z) {
	    AxisAlignedBB bb = flashDriveHitPositions[this.blockMetadata];
//        return bb.offset(this.blockType., par3, par5);
	    return AxisAlignedBB.getAABBPool().getAABB( bb.minX + x, bb.minY + y, bb.minZ + z, bb.maxX + x, bb.maxY + y, bb.maxZ + z);
	}
}