package FactoryGame.Klassendiagramm;

public class Player extends Entity {

	private float health = maxHealth;
	private float maxHealth;
	private float maxSaturation;
	private float saturation = maxSaturation;
	private CharacterSpritesheet spritesheet;

	public Player(float x, float y, int width, int height, int speed) {
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

	public void keyPressed(KeyEvent e, int k) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public void keyReleased(KeyEvent e, int k) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
