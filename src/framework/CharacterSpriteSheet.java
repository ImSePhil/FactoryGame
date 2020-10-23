package framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class CharacterSpriteSheet {
	private File file;
	private List<BufferedImage> framesUp = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesDown = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesRight = new ArrayList<BufferedImage>();
	private List<BufferedImage> framesLeft = new ArrayList<BufferedImage>();

	public CharacterSpriteSheet(int width, int height, String filename) {
		file = new File("src\\Assets\\" + filename);
		try {
			initFrames();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initFrames() throws IOException {
		float time = System.nanoTime();
		BufferedImage image = ImageIO.read(file);
		for(int yy = 0; yy < 4; yy++) {
			for(int xx = 0; xx < 8; xx++) {
				int x = xx*64;
				int y = yy*64;
				if(yy == 0) {
					framesDown.add(image.getSubimage(x, y, 64, 64));
				}
				if(yy == 1) {
					framesLeft.add(image.getSubimage(x, y, 64, 64));
				}
				if(yy == 2) {
					framesRight.add(image.getSubimage(x, y, 64, 64));
				}
				if(yy == 3) {
					framesUp.add(image.getSubimage(x, y, 64, 64));
				}
			}
		}
	}

	public List<BufferedImage> getFramesUp() {
		return framesUp;
	}

	public List<BufferedImage> getFramesDown() {
		return framesDown;
	}

	public List<BufferedImage> getFramesRight() {
		return framesRight;
	}

	public List<BufferedImage> getFramesLeft() {
		return framesLeft;
	}

}
