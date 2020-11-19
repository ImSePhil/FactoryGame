package FactoryGame.Klassendiagramm;

public abstract class GameObject extends GameElement {

	protected float x;
	protected float y;
	protected int width;
	protected int height;

	protected GameObject(float x, float y, int width, int height) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	protected abstract int getWidth();

	protected abstract float getX();

	protected abstract float getY();

	protected abstract int getHeight();

	void update() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	void render(Graphics2D g) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	void fromJson() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	JsonObject toJson() {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

}
