package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private float vspeed, hspeed;
    private float hs, vs;
    private float ss;

    public Player(float x, float y, int width, int height, int speed) {
	super(x, y, width, height, speed);
    }

    public void render(Graphics2D g) {
	g.setColor(Color.red);
	int xx = GameManager.width / 2 - width / 2;
	int yy = GameManager.height / 2 - height / 2;
	g.fillRect(xx, yy, width, height);
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
}
