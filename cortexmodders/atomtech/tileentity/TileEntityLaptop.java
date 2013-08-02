package cortexmodders.atomtech.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.Vec3;
import cortexmodders.atomtech.power.IAtomicPower;

public class TileEntityLaptop extends TilePoweredBase implements IAtomicPower {

    public boolean lidClosed;

    public TileEntityLaptop() {
        
    }
    
    @Override
    public void updateEntity() {
        
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
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
    }
    
    // Start of power methods.
    @Override
    public void onPowerRecieved(Vec3 sourceLoc) {
        
    }

    @Override
    public void sendPower(int x, int y, int z) {
       // cannot send power
    }


    @Override
    public boolean canRecievePower() {
        return true;
    }

    @Override
    public boolean canSendPower() {
        return false;
    }
    
    
    
}
