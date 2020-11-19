package FactoryGame.Klassendiagramm;

public class Zombie extends Enemy {

	private int frame;
	private List<BufferedImage> framesDown = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesLeft = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesRight = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesUp = new ArrayList<BufferedImage>();
	private CharacterSpriteSheet spritesheet;
	private int subframe;

	public Zombie(float x, float y, int width, int height, float speed) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	void fromJson() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected int getHeight() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected float getSpeed() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected int getWidth() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected float getX() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected float getY() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected void render(Graphics2D g) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	JsonObject toJson() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected void update() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
