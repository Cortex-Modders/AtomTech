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
    
    public void addFuel(final int fuel)
    {
        this.fuelLevel += fuel;
    }
    
    @Override
    public boolean canSendPower()
    {
        return true;
    }
    
    @Override
    public void closeChest()
    {
    }
    
    @Override
    public ItemStack decrStackSize(final int i, final int j)
    {
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
                    this.fuelStack = null;
                
                return itemstack;
            }
        }
        else
            return null;
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
    
    public int getFuelLevel()
    {
        return this.fuelLevel;
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    @Override
    public String getInvName()
    {
        return null;
    }
    
    // inventory methods
    
    @Override
    public int getSizeInventory()
    {
        return 1;
    }
    
    @Override
    public ItemStack getStackInSlot(final int i)
    {
        return this.fuelStack;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(final int i)
    {
        if (this.fuelStack != null)
        {
            ItemStack itemstack = this.fuelStack;
            this.fuelStack = null;
            return itemstack;
        }
        else
            return null;
    }
    
    @Override
    public boolean isInvNameLocalized()
    {
        return false;
    }
    
    @Override
    public boolean isItemValidForSlot(final int i, final ItemStack itemstack)
    {
        return false;
    }
    
    @Override
    public boolean isUseableByPlayer(final EntityPlayer entityplayer)
    {
        return true;
    }
    
    @Override
    public void onDataPacket(final INetworkManager net, final Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.data;
        this.readFromNBT(tag);
    }
    
    @Override
    public void openChest()
    {
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.fuelLevel = tag.getInteger("fuel");
    }
    
    @Override
    public void setInventorySlotContents(final int i, final ItemStack itemstack)
    {
        this.fuelStack = itemstack;
        
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
            itemstack.stackSize = this.getInventoryStackLimit();
        
    }
    
    @Override
    public void updateEntity()
    {
        if (this.fuelLevel > 0)
        {
            if (!this.worldObj.isRemote && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) < 4)
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) + 4, 3);
            if (this.fuelLevel % 10 == 0)
            {
                this.powerLevel = 1;
                this.sendPower();
                this.powerLevel = 0;
            }
            this.fuelLevel--;
        }
        else if (this.fuelLevel < 0)
            this.fuelLevel = 0;
        else if (!this.worldObj.isRemote && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) > 3 && this.fuelLevel == 0)
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) - 4, 3);
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("fuel", this.fuelLevel);
    }
}
