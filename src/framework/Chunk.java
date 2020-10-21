package framework;

import java.awt.Graphics2D;

public class Chunk {
    public Block[][] blocks = new Block[Game.chunksize][Game.chunksize];

    public void render(Graphics2D g) {
	for (int row = 0; row < blocks.length; row++) {
	    for (int col = 0; col < blocks[row].length; col++) {
		blocks[row][col].render(g);
	    }
	}
    }

    public void update() {
	for (int row = 0; row < blocks.length; row++) {
	    for (int col = 0; col < blocks[row].length; col++) {
		blocks[row][col].update();
	    }
	}
    }

    public Block blockAt(int blockX, int blockY) {
	int chunksize = Game.chunksize;
	return blocks[blockY % chunksize][blockX % chunksize];
    }
}
