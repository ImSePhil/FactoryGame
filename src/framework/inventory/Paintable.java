package framework.inventory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import framework.PlayState;

public abstract class Paintable {
    public int x, y, height, width;
    public Color color;

    public Paintable(int x, int y, int width, int height, Color color) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.color = color;
    }

    public void render(Graphics2D g) {
	if (PlayState.showInfo) {
	    g.setColor(Color.white);
	    g.drawRect(x, y, width, height);
	}
    }

    public void mousePressed(MouseEvent e) {

    }

}
