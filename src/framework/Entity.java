package framework;

public class Entity extends GameObject {

	protected float speed;
	
	public Entity(float x, float y, int width, int height, float speed) {
		super(x, y, width, height);
		this.speed = speed;
	}

	@Override
	protected float getX() {
		return x;
	}

	@Override
	protected float getY() {
		return y;
	}

	@Override
	protected int getWidth() {
		return width;
	}

	@Override
	protected int getHeight() {
		return height;
	}

}
