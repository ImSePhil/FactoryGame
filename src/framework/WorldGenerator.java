package framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WorldGenerator {
	int[][] terrain;
	private int blocksX;
	private int blocksY;
	
	public void generate(int blocksX, int blocksY, String filename, int coalNodes, int ironNodes, int goldNodes) throws IOException {
		this.blocksX = blocksX;
		this.blocksY = blocksY;
		terrain = new int[blocksY][blocksX];
		for (int y = 0; y < blocksY; y++) {
            for (int x = 0; x < blocksX; x++) {
                double noise = Noise.noise(x*0.01,y*0.01);
                int c = (int) Math.round((Math.abs(noise)*10)*3);
                if(c > 3)
                	c = 3;
                terrain[y][x] = c;
            }
        }
		placeNodes(Material.OreCoal, coalNodes);
		placeNodes(Material.OreIron, ironNodes);
		placeNodes(Material.OreGold, goldNodes);
		File f = new File(filename);
		int i = 0;
		while(f.exists()) {
			i++;
			filename = filename.replace(".txt", i + ".txt");
			f = new File(filename);
		}
		f.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write(String.valueOf(blocksX));
		writer.newLine();
		writer.write(String.valueOf(blocksY));
		writer.newLine();
		for(int[] ints: terrain) {
			for(int num: ints) {
				writer.write(String.valueOf(num) + " ");
			}
			writer.newLine();
		}
		writer.write("#ENDTERRAIN");
		writer.newLine();
		writer.write("#ENDUSERBUILD");
		writer.close();
	}

	
	
	private void placeNodes(Material ore, int amount) {
		Random random = new Random();
		for(int i = 0; i < amount; i++) {
			int x = random.nextInt(blocksX);
			int y = random.nextInt(blocksY);
			if(x > 0)
				if(terrain[y][x-1] <= 4)
					terrain[y][x-1] = Material.Stone.getID();
			if(x < blocksX-1)
				if(terrain[y][x+1] <= 4)
					terrain[y][x+1] = Material.Stone.getID();
			if(y > 0)
				if(terrain[y-1][x] <= 4)
					terrain[y-1][x] = Material.Stone.getID();
			if(y < blocksY-1)
				if(terrain[y+1][x] <= 4)
					terrain[y+1][x] = Material.Stone.getID();
			
			
			terrain[y][x] = ore.getID();
			
		}
	}
}
