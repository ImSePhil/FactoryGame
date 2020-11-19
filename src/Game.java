package FactoryGame.Klassendiagramm;

public class Game {

	public static int blocksize = 64;
	public static int chunksize = 4;
	private boolean generate;
	private WorldGenerator generator;
	private static final long serialVersionUID = 1L;
	public static Spritesheet textures = new SpriteSheet("\\src\\Assets\\textures,png",8);
	public static int windowHeight = 800;
	public static int windowWidth = 1200;
	public static World world = worldLoader.getWorld();
	private static WorldLoader worldLoader = new WorldLoader("\\src\\Assets\\world.json");

	public Game(boolean generate) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public static void main(String[] args) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public void newMethod() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
