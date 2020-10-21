package framework;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
    private BufferedImage spritesheet;
    int cols;
    private int size;
    private String path;

    public Spritesheet(String path, int size) {
	this.size = size;
	this.path = System.getProperty("user.dir") + path;
	loadImage();
    }

    private void loadImage() {
	File f = new File(path);
	try {
	    spritesheet = ImageIO.read(f);
	} catch (NullPointerException npe) {
	    System.out.println("Path is empty!");
	} catch (IllegalArgumentException iae) {
	    System.out.println("File not found!");
	} catch (IOException ioe) {
	    System.out.println("Unknown error occured!");
	    ioe.printStackTrace();
	    System.exit(420);
	}
	cols = spritesheet.getWidth() / size;
    }

    public BufferedImage getSprite(int id) {
	int x = (id % cols) * size;
	int y = (id / cols) * size;
	try {
	    return spritesheet.getSubimage(x, y, size, size);
	} catch (Exception e) {
	    System.out.println("X: " + x);
	    System.out.println("Y: " + y);
	    e.printStackTrace();
	}
	return null;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
	int w = image.getWidth();
	int h = image.getHeight();
	BufferedImage rotated = new BufferedImage(w, h, image.getType());
	Graphics2D graphics = rotated.createGraphics();
	graphics.rotate(Math.toRadians(angle), w / 2, h / 2);
	graphics.drawImage(image, null, 0, 0);
	graphics.dispose();
	return rotated;
    }

}
