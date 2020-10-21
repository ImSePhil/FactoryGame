package framework;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class State {
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
	this.gsm = gsm;
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public abstract void keyPressed(KeyEvent e, int k);

    public abstract void keyReleased(KeyEvent e, int k);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e, Point mousePoition);

    public abstract void mouseWheelMoved(MouseWheelEvent e);
}
