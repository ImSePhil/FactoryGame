package framework.inventory;

import java.awt.Color;
import java.awt.Graphics2D;

public class Arrow extends Paintable {

    public Arrow(int x, int y, int width, int height, Color color) {
	super(x, y, width, height, color);
    }

    @Override
    public void render(Graphics2D g) {
	super.render(g);
	int[] xx = new int[] { x + 0, x + width - width / 4, x + width - width / 4, x + width, x + width - width / 4,
		x + width - width / 4, x + 0 };
	int[] yy = new int[] { (int) (y + height / 2.5), (int) (y + height / 2.5), y + height / 4, y + height / 2,
		y + height - height / 4, (int) (y + height - height / 2.5), (int) (y + height - height / 2.5) };

	g.fillPolygon(xx, yy, 7);

    }

}
