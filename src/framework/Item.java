package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item extends GameObject {
    ItemType itemType;
    BufferedImage image;
    private int xx, yy;
    private Conveyor belt;
    private int targetX, targetY;
    private int speed;

    public Item(float x, float y, int width, int height, ItemType itemType) {
	super(x, y, width, height);
	this.itemType = itemType;
	this.image = itemType.getSprite();
	targetX = (int) x;
	targetY = (int) y;
    }

    public void render(Graphics2D g) {
	g.drawImage(image, xx - Game.itemsize / 2, yy - Game.itemsize / 2, width, height, null);
	g.setColor(Color.white);
    }

    public void update() {
	xx = (int) x - PlayState.camera.getCamX();
	yy = (int) y - PlayState.camera.getCamY();
	int x_ = ((int) x / 64) * 64;
	int y_ = ((int) y / 64) * 64;
	Machine machine = Game.world.getMachineAt(x_, y_);
	if (machine != null) {
	    if (machine instanceof Conveyor) {
		belt = (Conveyor) machine;
		speed = belt.speed;
		if (x == targetX && y == targetY) {
		    if (belt instanceof ConveyorInserter)
			return;
		    switch (belt.direction) {
		    case Down:
			targetX = (int) belt.x + 32;
			targetY = ((int) ((belt.y + 32) + 64));
			break;
		    case Left:
			targetX = ((int) ((belt.x + 32) - 64));
			targetY = (int) belt.y + 32;
			break;
		    case Right:
			targetX = ((int) ((belt.x + 32) + 64));
			targetY = (int) belt.y + 32;
			break;
		    case Up:
			targetX = (int) belt.x + 32;
			targetY = ((int) ((belt.y + 32) - 64));
			break;
		    default:
			break;
		    }
		    if (!PlayState.isSlotFree(targetX, targetY)) {
			targetX = (int) x;
			targetY = (int) y;
		    }
		}
	    }
	}

	if (x > targetX) {
	    x -= speed;
	}
	if (x < targetX) {
	    x += speed;
	}
	if (y > targetY) {
	    y -= speed;
	}
	if (y < targetY) {
	    y += speed;
	}
    }

    @Override
    protected float getX() {
	return 0;
    }

    @Override
    protected float getY() {
	return 0;
    }

    @Override
    protected int getWidth() {
	return 0;
    }

    @Override
    protected int getHeight() {
	return 0;
    }

}
