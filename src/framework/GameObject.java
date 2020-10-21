package framework;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected int width;
    protected int height;

    public GameObject(float x, float y, int width, int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    protected abstract float getX();

    protected abstract float getY();

    protected abstract int getWidth();

    protected abstract int getHeight();

}
