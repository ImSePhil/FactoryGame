package framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WorldLoader {
    public String path;
    private World world;
    private int chunksize = Game.chunksize;
    private String[][] tempWorld;
    private int blocksX;
    private int blocksY;
    private int chunksX;
    private int chunksY;
    private int blocksize = Game.blocksize;
    private List<String[]> temp_machines;

    public WorldLoader(String path) {
	this.path = System.getProperty("user.dir") + path;
	loadworld();
	buildTerrain();
    }

    private void buildTerrain() {
	chunksX = (blocksX / chunksize);
	chunksY = (blocksY / chunksize);
	Chunk[][] chunks = new Chunk[chunksY][chunksX];
	for (int y = 0; y < blocksY; y++) {
	    for (int x = 0; x < blocksX; x++) {
		int chunkX = x / chunksize;
		int chunkY = y / chunksize;
		int blockX = x - chunkX * chunksize;
		int blockY = y - chunkY * chunksize;
		int xx = x * blocksize;
		int yy = y * blocksize;
		int id = Integer.parseInt(tempWorld[y][x]);
		Block block;
		if (id == Material.OreCoal.getID()) {
		    block = new Node(xx, yy, blocksize, blocksize, Material.values()[id]);
		} else if (id == Material.OreIron.getID()) {
		    block = new Node(xx, yy, blocksize, blocksize, Material.values()[id]);
		} else if (id == Material.OreGold.getID()) {
		    block = new Node(xx, yy, blocksize, blocksize, Material.values()[id]);
		} else {
		    block = new Block(xx, yy, blocksize, blocksize, Material.values()[id]);
		}
		if (chunks[chunkY][chunkX] == null)
		    chunks[chunkY][chunkX] = new Chunk();
		chunks[chunkY][chunkX].blocks[blockY][blockX] = block;
	    }
	}

	List<Machine> machines = new ArrayList<Machine>();
	temp_machines.forEach(tmachine -> {
	    int id = Integer.parseInt(tmachine[0]);
	    int x = Integer.parseInt(tmachine[1]);
	    int y = Integer.parseInt(tmachine[2]);
	    Class class_ = Material.values()[id].getMachineType().class_;
	    Machine machine = null;
	    if (class_ == Drill.class) {
		machine = new Drill(x, y, Game.blocksize, Game.blocksize, Material.values()[id]);
	    }
	    if (class_ == Conveyor.class) {
		machine = new Conveyor(x, y, Game.blocksize, Game.blocksize, Material.values()[id]);
	    }
	    if (class_ == ConveyorExtractor.class) {
		machine = new ConveyorExtractor(x, y, Game.blocksize, Game.blocksize, Material.values()[id]);
	    }
	    if (class_ == ConveyorInserter.class) {
		machine = new ConveyorInserter(x, y, Game.blocksize, Game.blocksize, Material.values()[id]);
	    }
	    if (class_ == Smelter.class) {
		machine = new Smelter(x, y, Game.blocksize, Game.blocksize, Material.values()[id]);
	    }
	    machines.add(machine);
	});
	world = new World(chunks, machines);
	world.path = path;
    }

    public World getWorld() {
	return world;
    }

    private void loadworld() {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
	    blocksX = Integer.parseInt(reader.readLine());
	    blocksY = Integer.parseInt(reader.readLine());
	    tempWorld = new String[blocksY][blocksX];
	    String line = reader.readLine();
	    int col = 0;
	    while (!line.contains("#ENDTERRAIN")) {
		String[] row = line.split(" ");
		tempWorld[col] = row;
		col++;
		line = reader.readLine();
	    }
	    line = reader.readLine();
	    temp_machines = new ArrayList<String[]>();
	    while (!line.contains("#ENDUSERBUILD")) {
		String[] row = line.split(" ");
		temp_machines.add(row);
		line = reader.readLine();
	    }
	    reader.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
