package myMMO.biome;

public class OceanBiome extends Biome{

	public OceanBiome(int xStart, int xEnd, int yStart, int yEnd) {
		super(1, xStart, xEnd, yStart, yEnd);
		
	}
	public int[] tileTypes()
	{
		//3,5 water,sand
		int[] tileIds={3,5};
		
		return tileIds;
	}

}
