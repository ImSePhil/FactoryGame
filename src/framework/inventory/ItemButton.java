package framework.inventory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import framework.ItemType;
import framework.PlayState;

public class ItemButton extends Paintable{
	ItemType itemType;
	Runnable action;
	private boolean mouseOver;
	
	public ItemButton(int x, int y, int width, int height, Color color, ItemType itemType, Runnable action) {
		super(x, y, width, height, color);
		this.itemType = itemType;
		this.action = action;
	}
	
	@Override
	public void render(Graphics2D g) {
		Point mouse = PlayState.mousePosition;
		g.setColor(Color.blue);
		if (mouse.x > x && mouse.x < x + width) {
			if (mouse.y > y && mouse.y < y + height) {
				mouseOver = true;
			} else
				mouseOver = false;
		} else
			mouseOver = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (mouseOver) {
				action.run();
			}
		}
	}
}
