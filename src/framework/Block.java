package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	protected BufferedImage image;
	protected int xx;
	protected int yy;
	protected int mx;
	protected int my;
	protected boolean mouseOver;
	public Material material;

	public Block(float x, float y, int width, int height, Material material) {
		super(x, y, width, height);
		this.material = material;
		image = Game.terrainSpriteSheet.getSprite(material.getID());
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

	public void render(Graphics2D g) {
		g.setColor(Color.white);

		xx = (int) x - PlayState.camera.getCamX();
		yy = (int) y - PlayState.camera.getCamY();

		boolean outOfBorder = false;
		if (xx < -64)
			outOfBorder = true;
		if (yy < -64)
			outOfBorder = true;
		if (xx > Game.width + 64)
			outOfBorder = true;
		if (yy > Game.height + 64)
			outOfBorder = true;
		if (outOfBorder)
			return;
		g.drawImage(image, xx, yy, width, height, null);
		if (mouseOver && PlayState.buildMode) {
			g.drawRect(xx, yy, width - 1, height - 1);
		}
	}

	public void update() {
		xx = (int) x - PlayState.camera.getCamX();
		yy = (int) y - PlayState.camera.getCamY();
		try {
			mx = (int) PlayState.mousePosition.getX();
			my = (int) PlayState.mousePosition.getY();
		} catch (Exception e) {

		}
		mouseOver = checkMouse();

	}

	public boolean checkMouse() {
		if (mx < xx)
			return false;
		if (my < yy)
			return false;
		if (my > yy + height)
			return false;
		if (mx > xx + width)
			return false;
		return true;
	}

}
