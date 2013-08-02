package cortexmodders.atomtech.power;

public interface IAtomicPower
{
	void onPowerRecieved(int power);
	int sendPower();
}