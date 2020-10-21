package framework.inventory;

import java.awt.Color;
import java.awt.Graphics2D;

import framework.ItemType;

public class DrawItem extends Paintable {
    int amount;
    ItemType itemType;

    public DrawItem(int x, int y, int width, int height, Color color, int amount, ItemType itemType) {
	super(x, y, width, height, color);
	this.amount = amount;
	this.itemType = itemType;
    }

    @Override
    public void render(Graphics2D g) {
	super.render(g);
	g.drawImage(itemType.getHDSprite(), x, y, width, height, null);
	g.setColor(color);
	g.drawString("x" + amount, x + 5, y + height - 5);
    }

}
