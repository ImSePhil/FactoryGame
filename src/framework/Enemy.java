package framework;

import java.awt.Graphics2D;

public abstract class Enemy extends Entity {

	public float xx;
	public float yy;

	public Enemy(float x, float y, int width, int height, float speed) {
		super(x, y, width, height, speed);
	}

	protected void update() {
		xx = (int) x - PlayState.camera.getCamX();
		yy = (int) y - PlayState.camera.getCamY();

	}

	protected void render(Graphics2D g) {

	}

}
