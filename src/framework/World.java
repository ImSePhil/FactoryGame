package framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class World {
	public Chunk[][] chunks;
	public int chunksX;
	public int chunksY;
	public List<Machine> machines = new ArrayList<Machine>();
	public List<Farmland> farmland = new ArrayList<Farmland>();
	public String path;

	public World(Chunk[][] chunks, List<Machine> machines) {
		this.chunks = chunks;
		this.chunksY = chunks.length;
		this.chunksX = chunks[0].length;
		this.machines = machines;
	}

	public void render(Graphics2D g) {
		for (int row = 0; row < chunks.length; row++) {
			for (int col = 0; col < chunks[row].length; col++) {
				chunks[row][col].render(g);
			}
		}
		machines.forEach(machine -> machine.render(g));
		farmland.forEach(f -> f.render(g));
	}

	public void update() {
		for (int row = 0; row < chunks.length; row++) {
			for (int col = 0; col < chunks[row].length; col++) {
				chunks[row][col].update();
			}
		}
		machines.forEach(Machine::update);
		farmland.forEach(Farmland::update);
	}

	public Block getBlockAt(int blockX, int blockY) {
		int chunksize = Game.chunksize;
		int chunkX = blockX / chunksize;
		int chunkY = blockY / chunksize;
		return chunks[chunkY][chunkX].blockAt(blockX, blockY);
	}

	public Machine getMachineAt(int blockX, int blockY) {
		for (Machine machine : machines) {
			int xx = (int) machine.getX();
			int yy = (int) machine.getY();
			if (xx == blockX)
				if (yy == blockY)
					return machine;
		}
		return null;
	}

	public boolean canBuildAt(int blockX, int blockY) {
		for (Machine machine : machines) {
			int xx = (int) machine.getX();
			int yy = (int) machine.getY();
			if (xx == blockX)
				if (yy == blockY)
					return machine.material.isWalkable();
		}
		return true;
	}

	public void place(Material material) {

		Point mousePosition = PlayState.mousePosition;
		int mouseX = (int) mousePosition.getX();
		int camX = PlayState.camera.getCamX();
		int mouseY = (int) mousePosition.getY();
		int camY = PlayState.camera.getCamY();
		int relativeMouseX = mouseX + camX;
		int relativeMouseY = mouseY + camY;
		int blocksize = Game.blocksize;
		int chunksize = Game.chunksize;
		int chunkX = relativeMouseX / (blocksize * chunksize);
		int blockX = relativeMouseX / (blocksize) - chunkX * chunksize;
		int chunkY = relativeMouseY / (blocksize * chunksize);
		int blockY = relativeMouseY / (blocksize) - chunkY * chunksize;
		int placeX = chunkX * chunksize * blocksize + blockX * blocksize;
		int placeY = chunkY * chunksize * blocksize + blockY * blocksize;
		if (material.isMachine()) {
			if (material.getMachineType().class_ == Drill.class) {
				machines.add(new Drill(placeX, placeY, Game.blocksize, Game.blocksize, material));
			}
			if (material.getMachineType().class_ == Conveyor.class) {
				machines.add(new Conveyor(placeX, placeY, Game.blocksize, Game.blocksize, material));
			}
			if (material.getMachineType().class_ == ConveyorExtractor.class) {
				machines.add(new ConveyorExtractor(placeX, placeY, Game.blocksize, Game.blocksize, material));
			}
			if (material.getMachineType().class_ == ConveyorInserter.class) {
				machines.add(new ConveyorInserter(placeX, placeY, Game.blocksize, Game.blocksize, material));
			}
			if (material.getMachineType().class_ == Smelter.class) {
				machines.add(new Smelter(placeX, placeY, Game.blocksize, Game.blocksize, material));
			}
		}
		if (material == Material.Farmland) {
			farmland.add(new Farmland(placeX, placeY, Game.blocksize, Game.blocksize, material));
		}
	}

	public void keyPressed(KeyEvent e, int k) {
		machines.forEach(machine -> machine.keyPressed(e, k));
	}

	public void plant(Plants selectedPlant, int x, int y) {
		System.out.println(selectedPlant + " " + x + " " + y);
		for(Farmland land: farmland) {
			if(land.getX() == x*64 && land.getY() == y*64) {
				land.setPlant(selectedPlant);
			}
		}
	}
	
}
