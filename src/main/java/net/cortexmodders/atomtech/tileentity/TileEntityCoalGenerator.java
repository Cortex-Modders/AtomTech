package net.cortexmodders.atomtech.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.electricity.IVoltageInput;
import universalelectricity.api.electricity.IVoltageOutput;
import universalelectricity.api.energy.IConductor;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.vector.Vector3;
import universalelectricity.api.vector.VectorHelper;

public class TileEntityCoalGenerator extends TileEntity implements IInventory, IEnergyInterface, IEnergyContainer, IVoltageOutput, IVoltageInput
{
    
    private int fuelLevel = 0;
    private ItemStack fuelStack;
    
    private long maxStored = 0;
    private long outputMax = 8;
    
    private long energy = 0;
    
    // My methods
    
    public void addFuel(final int fuel)
    {
        this.fuelLevel += fuel;
    } 
    
    public int getFuelLevel()
    {
        return this.fuelLevel;
    }
    
    // TileEntity methods
    
    @Override
    public void updateEntity()
    {
        if(!this.worldObj.isRemote)
        {
            if (this.fuelLevel > 0)
            {
                if (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) < 4)
                    this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) + 4, 3);
                if (this.fuelLevel % 10 == 0)
                {
                    //this.powerLevel = 1;
                    //this.sendPower();
                    //this.powerLevel = 0;
                    
                    
                }
                this.fuelLevel--;
            }
            else if (this.fuelLevel < 0)
                this.fuelLevel = 0;
            else if (!this.worldObj.isRemote && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) > 3 && this.fuelLevel == 0)
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) - 4, 3);
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
    
    @Override
    public void onDataPacket(final INetworkManager net, final Packet132TileEntityData pkt)
    {
        NBTTagCompound tag = pkt.data;
        this.readFromNBT(tag);
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.fuelLevel = tag.getInteger("fuel");
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("fuel", this.fuelLevel);
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
    public void setInventorySlotContents(final int i, final ItemStack itemstack)
    {
        this.fuelStack = itemstack;
        
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
            itemstack.stackSize = this.getInventoryStackLimit();
        
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    @Override
    public String getInvName()
    {
        return "";
    }
    
    @Override
    public void openChest()
    {
    }
    
    @Override
    public void closeChest()
    {
    }
    
    // Universal Electricity Methods
    
    @Override
    public boolean canConnect(ForgeDirection direction)
    {
        TileEntity tile = VectorHelper.getTileEntityFromSide(worldObj, new Vector3(this), direction);
        if(tile instanceof IConductor)
            return true;
        
        return false;
    }
    
    @Override
    public long getEnergy(ForgeDirection direction)
    {
        return energy;
    }
    
    @Override
    public long getEnergyCapacity(ForgeDirection direction)
    {
        return this.maxStored;
    }
    
    @Override
    public void setEnergy(ForgeDirection direction, long energy)
    {
        this.energy = energy;
    }
    
    @Override
    public long onExtractEnergy(ForgeDirection direction, long extract, boolean doExtract)
    {
        //TODO fix this
        if(doExtract && canConnect(direction))
            return energy -= outputMax;
        
        return 0;
    }
    
    @Override
    public long onReceiveEnergy(ForgeDirection arg0, long arg1, boolean arg2)
    {
        return 0;
    }

    @Override
    public long getVoltageInput(ForgeDirection direction)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void onWrongVoltage(ForgeDirection direction, long voltage)
    {
        
    }

    @Override
    public long getVoltageOutput(ForgeDirection side)
    {
        return 0;
    }
}
