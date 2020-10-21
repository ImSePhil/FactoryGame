package framework;

import java.awt.Color;

import framework.inventory.Paintable;

public class Rectangle extends Paintable {

    public boolean fill;

    public Rectangle(int x, int y, int width, int height, Color color, boolean fill) {
	super(x, y, width, height, color);
	this.fill = fill;
    }

}
