package framework;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class Zombie extends Enemy implements ActionListener {
	private CharacterSpriteSheet sheet = new CharacterSpriteSheet(width, height, "ZombieSpriteSheet.png");
	private List<BufferedImage> framesUp = sheet.getFramesUp();
	private List<BufferedImage> framesDown = sheet.getFramesDown();
	private List<BufferedImage> framesRight = sheet.getFramesRight();
	private List<BufferedImage> framesLeft = sheet.getFramesLeft();
	public List<BufferedImage> frames = framesDown;
	private int frame = 0;
	private int subframe = 0;
	private float mh;
	private float mv;
	private Timer timer;
	private float hs;
	private float vs;
	private int dir = 2;
	private boolean idle;
	private Player target;

	public Zombie(float x, float y, int width, int height, float speed) {
		super(x, y, width, height, speed);
		timer = new Timer(1000, this);
		timer.start();
	}

	public void update() {
		super.update();
		switch (dir) {
		case 0:
			hs = 0;
			vs = -1;
			break;
		case 1:
			hs = 1;
			vs = 0;
			break;
		case 2:
			hs = 0;
			vs = 1;
			break;
		case 3:
			hs = -1;
			vs = 0;
			break;
		}

		if (hs != 0) {
			if (hs < 0) {
				frames = framesLeft;
			}
			if (hs > 0) {
				frames = framesRight;
			}
		}
		if (vs != 0) {
			if (vs < 0) {
				frames = framesUp;
			}
			if (vs > 0) {
				frames = framesDown;
			}
		}
		if (vs == 0.0 && hs == 0.0 || idle == true) {
			frame = 0;
		} else {
			subframe++;
			if (subframe == 4) {
				subframe = 0;
				frame++;
			}
			if (frame == 8)
				frame = 0;
			if (x + hs * speed > 0 -32) {

				x = x + hs * speed;
			}
			if (y + vs * speed > 0 -32) {

				y = y + vs * speed;
			}

		}
	}

	public void render(Graphics2D g) {
		g.drawImage(frames.get(frame), (int) xx, (int) yy, 64, 64, null);
		g.drawLine((int) xx, (int) yy, (int) PlayState.player.xx, (int) PlayState.player.yy);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.setDelay((int) (Math.random() * 3000));
		if (idle == false)
			timer.setDelay((int) (Math.random() * 7000));
		dir = (int) (Math.random() * 4);
		idle = !idle;
	}

}
