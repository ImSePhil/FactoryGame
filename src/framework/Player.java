package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

	private float vspeed, hspeed;
	private float hs, vs;
	private float ss;
	private final float maxHealth = 100;
	private float health = maxHealth;
	private final float maxSaturation = 100;
	private float saturation = maxSaturation;
	public int xx,yy;
	private CharacterSpriteSheet sheet = new CharacterSpriteSheet(width, height,"PlayerSpriteSheet.png");
	
	private List<BufferedImage> framesUp = sheet.getFramesUp();
	private List<BufferedImage> framesDown = sheet.getFramesDown();
	private List<BufferedImage> framesRight = sheet.getFramesRight();
	private List<BufferedImage> framesLeft = sheet.getFramesLeft();
	public List<BufferedImage> frames = framesDown;
	private int frame = 0;
	private int subframe = 0;

	public Player(float x, float y, int width, int height, int speed) {
		super(x, y, width, height, speed);
	}

	public void render(Graphics2D g) {
		renderStats(g);
		g.setColor(Color.red);
		g.fillRect(0, 0, 100, 10);
		xx = GameManager.width / 2 - width / 2;
		yy = GameManager.height / 2 - height / 2;
		g.drawImage(frames.get(frame),xx,yy,64,64,null);
	}

	public void update() {
		float maxSpeed = speed;
		float pythagoras = ((hspeed * hspeed) + (vspeed * vspeed));
		if (pythagoras > (maxSpeed * maxSpeed)) {
			float magnitude = (float) Math.sqrt(pythagoras);
			float multiplier = maxSpeed / magnitude;
			vs = vspeed * multiplier;
			hs = hspeed * multiplier;
		} else {
			vs = vspeed;
			hs = hspeed;
		}
		x = x + hs * speed;
		y = y + vs * speed;
		regenerate();
		if(hs != 0) {
			if(hs < 0) {
				frames = framesLeft;
			}
			if(hs > 0) {
				frames = framesRight;
			}
		}
		if(vs != 0) {
			if(vs < 0) {
				frames = framesUp;
			}
			if(vs > 0) {
				frames = framesDown;
			}
		}
		if(vs == 0.0 && hs == 0.0) {
			frames = framesDown;
			frame = 0;
		}else {
			subframe++;
			if(subframe == 4) {
				subframe = 0;
				frame++;
			}
			if(frame == 8)
				frame = 0;
		}
	}

	public void regenerate() {
		if (health < 100) {
			if (saturation > 0) {
				saturation -= .1;
				health += .2;
			}
		}
	}

	public void keyPressed(KeyEvent e, int k) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			vspeed = -1;
			break;
		case KeyEvent.VK_A:
			hspeed = -1;
			break;
		case KeyEvent.VK_D:
			hspeed = 1;
			break;
		case KeyEvent.VK_S:
			vspeed = 1;
			break;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			dealDamage(10);
	}

	public void keyReleased(KeyEvent e, int k) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (vspeed == -1)
				vspeed = 0;
			break;
		case KeyEvent.VK_A:
			if (hspeed == -1)
				hspeed = 0;
			break;
		case KeyEvent.VK_D:
			if (hspeed == 1)
				hspeed = 0;
			break;
		case KeyEvent.VK_S:
			if (vspeed == 1)
				vspeed = 0;
			break;
		}
	}

	private void renderStats(Graphics2D g) {
		// Rendering background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Game.width / 2 - 105, 0, 200 + 10, 55);
		
		// Rendering effective Stats
		// Health
		g.setColor(Color.red);
		g.fillRect((int) (Game.width / 2 - health), 5, (int) health * 2, 20);
		// Saturation
		g.setColor(Color.green);
		g.fillRect((int) (Game.width / 2 - saturation), 30, (int) saturation * 2, 20);
		
		g.drawString(hs + " " + vs, 100, 200);
	}

	private void dealDamage(int i) {
		for(int j = 0; j < i; j++) {
			health-= 1;
		}
	}
}
