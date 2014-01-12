package net.cortexmodders.atomtech.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntityCoalGenerator extends TilePoweredBase implements IInventory
{
	private int fuelLevel = 0;
	
	private ItemStack fuelStack;
	
	@Override
	public void updateEntity()
	{
		if(fuelLevel > 0)
		{
			if(!worldObj.isRemote && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) < 4)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) + 4, 3);
			}
			if(fuelLevel % 10 == 0)
			{
				powerLevel = 1;
				sendPower();
				powerLevel = 0;
			}
			fuelLevel--;
		}
		else if(fuelLevel < 0)
		{
			fuelLevel = 0;
		}
		else if(!worldObj.isRemote && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) > 3 && fuelLevel == 0)
		{
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) - 4, 3);
		}
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	public void addFuel(int fuel)
	{
	    fuelLevel += fuel;
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
		this.fuelLevel = tag.getInteger("fuel");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("fuel", fuelLevel);
	}
	
	@Override
	public boolean canSendPower()
	{
		return true;
	}

	//inventory methods
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.fuelStack;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.fuelStack != null)
        {
            ItemStack itemstack;

            if (this.fuelStack.stackSize <= j)
            {
                itemstack = this.fuelStack;
                this.fuelStack = null;
                return itemstack;
            }
            else
            {
                itemstack = this.fuelStack.splitStack(j);

                if (this.fuelStack.stackSize == 0)
                {
                    this.fuelStack = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.fuelStack != null)
        {
            ItemStack itemstack = this.fuelStack;
            this.fuelStack = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.fuelStack = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
		
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}