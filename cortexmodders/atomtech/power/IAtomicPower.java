package cortexmodders.atomtech.power;

import net.minecraft.util.Vec3;

public interface IAtomicPower
{
	void onPowerRecieved(Vec3 sourceLoc);
	void sendPower(int x, int y, int z);
	int getPower();
	void addPower(int power);
	boolean canRecievePower();
	boolean canSendPower();
}