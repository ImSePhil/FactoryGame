package framework.inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Slot extends Paintable {

	public Slot(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(color.darker());
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(2.0f));
		g.drawRect(x, y, width, height);
		g.setStroke(oldStroke);
	}
}
