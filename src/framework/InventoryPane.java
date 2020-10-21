package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import framework.inventory.Paintable;

public class InventoryPane {
    private ArrayList<Paintable> items = new ArrayList<>();
    int maxX, maxY, minX, minY;

    public void update() {

    }

    public void render(Graphics2D g) {

	g.setColor(Color.DARK_GRAY);
	g.fillRect(minX - 10, minY - 10, (maxX - minX) + 20, (maxY - minY) + 20);
	g.setColor(Color.black);
	g.drawRect(minX - 10, minY - 10, (maxX - minX) + 20, (maxY - minY) + 20);

	items.forEach(paintable -> {
	    paintable.render(g);
	});

    }

    public void drawComponent(Paintable paintable) {
	if (paintable.x < minX)
	    minX = paintable.x;
	if (paintable.y < minY)
	    minY = paintable.y;
	if (paintable.x + paintable.width > maxX)
	    maxX = paintable.x + paintable.width;
	if (paintable.y + paintable.height > maxY)
	    maxY = paintable.y + paintable.height;
	this.items.add(paintable);
    }

    public void reset() {
	items = new ArrayList<>();
	maxX = 0;
	maxY = 0;
	minX = Integer.MAX_VALUE;
	minY = Integer.MAX_VALUE;
    }

    public void mousePressed(MouseEvent e) {
	items.forEach(paintable -> {
	    paintable.mousePressed(e);
	});
    }
}
