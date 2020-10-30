package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Interactor {
	boolean farmingMode = false;
	boolean buildMode = false;

	boolean showSeedInventory = false;

	public Interactor() {

	}

	public void setBlockType(Material blockType) {

	}

	public void update() {
		buildMode = false;
		farmingMode = false;
		if (PlayState.gui.selectedTool == Tool.Hammer)
			buildMode = true;
		if (PlayState.gui.selectedTool == Tool.Hoe)
			farmingMode = true;
	}

	public void render(Graphics2D g) {
		if (PlayState.gui.selectedTool != null) {
			g.drawImage(PlayState.gui.selectedTool.getSprite(), PlayState.mousePosition.x, PlayState.mousePosition.y,
					64, 64, null);
			g.setColor(Color.white);
			if (PlayState.gui.selectedTool == Tool.Hoe && showSeedInventory == false) {
				g.drawString("Press 'Q' to choose Seed",
						Game.width / 2 - g.getFontMetrics().stringWidth("Press 'Q' to choose Seed") / 2,
						Game.height - 128);
			}
			if (PlayState.gui.selectedTool == Tool.Hammer) {
				g.drawString("Press 'Q' to choose Building",
						Game.width / 2 - g.getFontMetrics().stringWidth("Press 'Q' to choose Building") / 2,
						Game.height - 128);
			}
		}
		if (showSeedInventory) {
			drawSeedInventory();
		}
	}

	private void drawSeedInventory() {
		PlayState.inventoryPane.reset();
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (farmingMode) {
				int blockX = e.getX() / Game.blocksize;
				int blockY = e.getY() / Game.blocksize;

				if (Game.world.getBlockAt(blockX, blockY).material == Material.Grass1
						|| Game.world.getBlockAt(blockX, blockY).material == Material.Grass2
						|| Game.world.getBlockAt(blockX, blockY).material == Material.Grass3
						|| Game.world.getBlockAt(blockX, blockY).material == Material.Grass4) {

					Game.world.place(Material.Farmland);
				}
			}
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void keyPressed(KeyEvent e, int k) {
		if (e.getKeyCode() == KeyEvent.VK_Q)
			if (farmingMode) {
				showSeedInventory = true;
			}
	}
}
