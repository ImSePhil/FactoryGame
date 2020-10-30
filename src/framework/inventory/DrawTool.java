package framework.inventory;

import java.awt.Color;
import java.awt.Graphics2D;

import framework.ItemType;
import framework.Tool;

public class DrawTool extends Paintable {
	Tool tool;

	public DrawTool(int x, int y, int width, int height, Tool tool) {
		super(x, y, width, height, new Color(0,0,0));
		this.tool = tool;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.drawImage(tool.getSprite(), x, y, width, height, null);
	}

}