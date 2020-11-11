package framework;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	public static int blocksize = 64;
	public static int chunksize = 5;
	public static int itemsize = 16;
	public static Spritesheet terrainSpriteSheet = new Spritesheet("\\src\\Assets\\terrain.png", blocksize);
	public static Spritesheet itemSpriteSheet = new Spritesheet("\\src\\Assets\\items.png", itemsize);
	public static Spritesheet itemHDSpriteSheet = new Spritesheet("\\src\\Assets\\itemsHD.png", 128);
	public static int width = 1200;
	public static int height = 800;
	private static WorldLoader worldLoader = new WorldLoader("\\src\\Assets\\world12.txt");
	public static World world = worldLoader.getWorld();
	public static Spritesheet toolSpriteSheet = new Spritesheet("\\src\\Assets\\ToolSpriteSheet.png", 64);
	public static Spritesheet plantSpriteSheet = new Spritesheet("\\src\\Assets\\PlantSpriteSheet.png", 122);

	public boolean generate;
	private WorldGenerator generator;

	public Game(boolean generate) {
		super("Factory Game");
		if (generate) {
			try {
				generator = new WorldGenerator();
				generator.generate(400, 400, "src\\Assets\\world.txt", 50, 50, 50);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setLayout(new BorderLayout());
		add(new GameManager(), BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Game(false);
	}

	

}
