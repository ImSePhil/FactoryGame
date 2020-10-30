package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import framework.inventory.DrawTool;
import framework.inventory.Slot;

public class GUI {

	public Tool selectedTool = null;

	public void render(Graphics2D g) {
		new Slot(Game.width / 2 - 32, Game.height - 96, 64, 64, Color.DARK_GRAY).render(g);
		new Slot(Game.width / 2 - 32 - 64, Game.height - 96, 64, 64, Color.DARK_GRAY).render(g);
		new Slot(Game.width / 2 - 32 + 64, Game.height - 96, 64, 64, Color.DARK_GRAY).render(g);
		new DrawTool(Game.width / 2 - 32, Game.height - 96, 64, 64, Tool.Hammer).render(g);
		new DrawTool(Game.width / 2 - 32 -64, Game.height - 96, 64, 64, Tool.Hoe).render(g);
		g.setColor(Color.white);
		g.drawString("1", Game.width / 2 - 30 - 64, Game.height - 84);
		g.drawString("2", Game.width / 2 - 30, Game.height - 84);
		g.drawString("3", Game.width / 2 - 30 + 64, Game.height - 84);
		g.setColor(Color.GRAY);
		if (selectedTool == null) {
			g.drawRect(Game.width / 2 - 32 + 64, Game.height - 96, 64, 64);
			return;
		}
		switch (selectedTool) {
		case Hammer:
			g.drawRect(Game.width / 2 - 32, Game.height - 96, 64, 64);
			break;
		case Hoe:
			g.drawRect(Game.width / 2 - 32 - 64, Game.height - 96, 64, 64);
			break;
		}

	}

	public void update() {

	}

	public void keyPressed(KeyEvent e, int k) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_1:
			selectedTool = Tool.Hoe;
			break;
		case KeyEvent.VK_2:
			selectedTool = Tool.Hammer;
			break;
		case KeyEvent.VK_3:
			selectedTool = null;
			break;
		}
		System.out.println(selectedTool);
	}

}
