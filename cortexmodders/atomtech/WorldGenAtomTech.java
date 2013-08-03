package cortexmodders.atomtech;

import java.util.Random;

import cortexmodders.atomtech.blocks.ModBlocks;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenAtomTech implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
		case -1: generateNether(world, rand, chunkX * 16, chunkZ * 16);
		case 0: generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateSurface(World world, Random rand, int baseX, int baseZ)
	{
		//rarity -smaller number = rarer
		for(int x = 0; x < 4; x++)
		{
			int Xcoord = baseX + rand.nextInt(16);				
			int Zcoord = baseZ + rand.nextInt(16);
			int Ycoord = rand.nextInt(20) + 30;
														//Max Vein Size
			(new WorldGenMinable(ModBlocks.ores.blockID, 8, 0)).generate(world, rand, Xcoord, Ycoord, Zcoord);
		}
	}
	
	private void generateNether(World world, Random rand, int baseX, int baseZ)
	{
		
	}
}