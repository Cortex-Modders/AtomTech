package net.cortexmodders.atomtech;

import java.util.Random;

import net.cortexmodders.atomtech.blocks.ModBlocks;
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
		
		//rarity -smaller number = rarer
		for(int x = 0; x < 5; x++)
		{
			int Xcoord = baseX + rand.nextInt(16);				
			int Zcoord = baseZ + rand.nextInt(16);
			int Ycoord = rand.nextInt(20) + 15;
														//Max Vein Size
			(new WorldGenMinable(ModBlocks.ores.blockID, 4, 1)).generate(world, rand, Xcoord, Ycoord, Zcoord);
		}
		
		//rarity -smaller number = rarer
		for(int x = 0; x < 4; x++)
		{
			int Xcoord = baseX + rand.nextInt(16);				
			int Zcoord = baseZ + rand.nextInt(16);
			int Ycoord = rand.nextInt(40);
														//Max Vein Size
			(new WorldGenMinable(ModBlocks.ores.blockID, 3, 2)).generate(world, rand, Xcoord, Ycoord, Zcoord);
		}
		
		//rarity -smaller number = rarer
		for(int x = 0; x < 3; x++)
		{
			int Xcoord = baseX + rand.nextInt(16);				
			int Zcoord = baseZ + rand.nextInt(16);
			int Ycoord = rand.nextInt(10) + 10;
														//Max Vein Size
			(new WorldGenMinable(ModBlocks.ores.blockID, 6, 3)).generate(world, rand, Xcoord, Ycoord, Zcoord);
		}
		
		//rarity -smaller number = rarer
		for(int x = 0; x < 6; x++)
		{
			int Xcoord = baseX + rand.nextInt(16);				
			int Zcoord = baseZ + rand.nextInt(16);
			int Ycoord = rand.nextInt(30) + 30;
														//Max Vein Size
			(new WorldGenMinable(ModBlocks.ores.blockID, 10, 4)).generate(world, rand, Xcoord, Ycoord, Zcoord);
		}
	}
	
	private void generateNether(World world, Random rand, int baseX, int baseZ)
	{
		
	}
}