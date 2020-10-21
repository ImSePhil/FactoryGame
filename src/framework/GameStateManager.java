package framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GameStateManager {
    public static final int PLAYSTATE = 0;
    private State[] states;
    public int state;

    public GameStateManager(int playstate) {
	states = new State[1];
	states[0] = new PlayState(this);
    }

    public void render(Graphics2D g2) {
	states[state].render(g2);
    }

    public void update() {
	states[state].update();
    }

    public void keyPressed(KeyEvent e, int keyCode) {
	states[state].keyPressed(e, keyCode);

    }

    public void keyReleased(KeyEvent e, int keyCode) {
	states[state].keyReleased(e, keyCode);

    }

    public void mouseMoved(MouseEvent e, Point mousePoition) {
	states[state].mouseMoved(e, mousePoition);
    }

    public void mousePressed(MouseEvent e) {
	states[state].mousePressed(e);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
	states[state].mouseWheelMoved(e);
    }
}
