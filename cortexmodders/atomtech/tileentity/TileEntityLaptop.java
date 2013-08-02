package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public class TileEntityLaptop extends TilePoweredBase
{
    public boolean lidClosed;
    public boolean isBroken = false;
    /**
     * condition of laptop. 0 = best, 1 = ok, 2 = broken.
     */
    public int condition = 0;
    
    public TileEntityLaptop()
    {
        super(20);
    }
    
    @Override
    public void updateEntity()
    {
        
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
        lidClosed = tag.getBoolean("lidClosed");
        isBroken = tag.getBoolean("isBroken");
        condition = tag.getInteger("condition");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setBoolean("lidClosed", lidClosed);
        tag.setBoolean("isBroken", isBroken);
        tag.setInteger("condition", condition);
    }
    
    /**
     * makes the laptop more broken.
     * 
     */
    public void degradeCondition() {
        if(this.condition != 2)
            this.condition++;
    }
    
    /**
     * returns if laptop is broken or not.
     * 
     * @return
     */
    public boolean isBroken() {
        return this.condition == 2;
    }
    
    // Start of power methods.
    @Override
    public void onPowerRecieved(Vec3 sourceLoc)
    {
        
    }

    @Override
    public void sendPower(int x, int y, int z)
    {
       // cannot send power
    }


    @Override
    public boolean canRecievePower()
    {
        return true;
    }

    @Override
    public boolean canSendPower()
    {
        return false;
    }
}