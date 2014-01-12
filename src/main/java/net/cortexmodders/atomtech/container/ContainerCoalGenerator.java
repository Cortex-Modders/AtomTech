package net.cortexmodders.atomtech.container;

import net.cortexmodders.atomtech.tileentity.TileEntityCoalGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCoalGenerator extends Container {

	private TileEntityCoalGenerator generator;
	
	public ContainerCoalGenerator(TileEntityCoalGenerator parTile) {
		generator = parTile;
		this.addSlotToContainer(new Slot(parTile, 0, 0, 0));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
        return this.generator.isUseableByPlayer(player);
    }

}
