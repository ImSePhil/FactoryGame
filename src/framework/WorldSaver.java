package framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorldSaver {
    private String filename;
    private int blocksX;
    private int blocksY;
    private World world;

    public WorldSaver(World world) {
	this.world = world;
    }

    public void save() throws Exception {
	filename = world.path;
	blocksX = world.chunksX * Game.chunksize;
	blocksY = world.chunksY * Game.chunksize;
	File f = new File(filename);
	BufferedWriter writer = new BufferedWriter(new FileWriter(f));
	writer.write(blocksX + "");
	writer.newLine();
	writer.write(blocksY + "");
	writer.newLine();
	for (int y = 0; y < blocksY; y++) {
	    for (int x = 0; x < blocksX; x++) {
		writer.write(world.getBlockAt(x, y).material.getID() + " ");
	    }
	    writer.newLine();
	}
	writer.write("#ENDTERRAIN");
	writer.newLine();
	world.machines.forEach(machine -> {
	    try {
		writer.write(machine.material.getID() + " " + (int) machine.getX() + " " + (int) machine.getY());
		writer.newLine();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	});
	writer.write("#ENDUSERBUILD");
	writer.close();
    }
}
