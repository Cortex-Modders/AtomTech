package net.cortexmodders.atomtech.lib

import net.minecraft.util.MathHelper
import net.minecraft.world.World

object BlockHelper {

    def getBlockOrientation(entityRotation: Float): Int = {
        return MathHelper.floor_double(entityRotation * 4.0F / 360.0F + 0.5D)
    }
    
    def setBlockMetadataWithNotify(world: World, x: Int, y: Int, z: Int, meta: Int, flag: MetadataNotifyFlag) = {
        //world.setBlockMetadataWithNotify(x, y, z, meta, flag.);
    }
  
}