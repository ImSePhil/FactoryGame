package framework;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Conveyor extends Machine {
    private BufferedImage imageRight;
    private BufferedImage imageLeft;
    private BufferedImage imageUp;
    private BufferedImage imageDown;
    public Direction direction;
    public int speed;

    public Conveyor(float x, float y, int width, int height, Material material) {
	super(x, y, width, height, material);
	generateImages();
	direction = Direction.Up;
	speed = 1;
    }

    @Override
    public void interact() {

    }

    public void generateImages() {
	imageRight = Spritesheet.rotate(image, 90);
	imageDown = Spritesheet.rotate(image, 180);
	imageLeft = Spritesheet.rotate(image, 270);
	imageUp = image;
    }

    @Override
    public void render(Graphics2D g) {
	switch (direction) {
	case Right:
	    g.drawImage(imageRight, xx, yy, width, height, null);
	    break;
	case Left:
	    g.drawImage(imageLeft, xx, yy, width, height, null);
	    break;
	case Down:
	    g.drawImage(imageDown, xx, yy, width, height, null);
	    break;
	case Up:
	    g.drawImage(imageUp, xx, yy, width, height, null);
	    break;
	default:
	    g.drawImage(image, xx, yy, width, height, null);
	    break;
	}
	if (mouseOver) {
	    g.drawRect(xx, yy, width, height);
	}

    }

    public enum Direction {
	Right, Left, Down, Up;
    }

    @Override
    public void keyPressed(KeyEvent e, int k) {
	if (mouseOver) {
	    if (e.getKeyCode() == KeyEvent.VK_R) {
		switch (direction) {
		case Right:
		    direction = Direction.Down;
		    break;
		case Left:
		    direction = Direction.Up;
		    break;
		case Down:
		    direction = Direction.Left;
		    break;
		case Up:
		    direction = Direction.Right;
		    break;
		}
	    }
	}
    }

}
