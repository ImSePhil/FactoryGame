package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import framework.inventory.DrawItem;
import framework.inventory.ItemButton;
import framework.inventory.Slot;

public class Interactor {
	boolean farmingMode = false;
	boolean buildMode = false;
	boolean showSeedInventory = false;
	private Plants selectedPlant;
	int x, y;
	private boolean working = true;

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
		if (PlayState.mousePosition != null) {
			Point mousePosition = PlayState.mousePosition;
			int mouseX = (int) mousePosition.getX();
			int camX = PlayState.camera.getCamX();
			int mouseY = (int) mousePosition.getY();
			int camY = PlayState.camera.getCamY();
			int relativeMouseX = mouseX + camX;
			int relativeMouseY = mouseY + camY;
			x = relativeMouseX;
			y = relativeMouseY;
		}

	}

	public void render(Graphics2D g) {
		if (PlayState.gui.selectedTool != null) {
			if (working) {
				g.drawImage(PlayState.gui.selectedTool.getSprite(), PlayState.mousePosition.x,
						PlayState.mousePosition.y, 64, 64, null);
			}
			g.setColor(Color.white);
			if (PlayState.gui.selectedTool == Tool.Hoe && showSeedInventory == false) {
				if (selectedPlant == null) {
					g.drawString("Press 'Q' to choose Seed",
							Game.width / 2 - g.getFontMetrics().stringWidth("Press 'Q' to choose Seed") / 2,
							Game.height - 128);
				} else {
					g.drawString(selectedPlant.getSeedName(),
							Game.width / 2 - g.getFontMetrics().stringWidth(selectedPlant.getSeedName()) / 2,
							Game.height - 128);
					showSeedInventory = false;
				}
			}
			if (PlayState.gui.selectedTool == Tool.Hammer) {
				g.drawString("Press 'Q' to choose Building",
						Game.width / 2 - g.getFontMetrics().stringWidth("Press 'Q' to choose Building") / 2,
						Game.height - 128);

			}
		}
		PlayState.inventoryPane.reset();
		if (showSeedInventory) {
			drawSeedInventory();
		}

	}

	private void drawSeedInventory() {
		PlayState.inventoryPane.drawComponent(new Slot(Game.width / 2 - 32, Game.height / 2 - 32, 64, 64, Color.gray));
		PlayState.inventoryPane
				.drawComponent(new Slot(Game.width / 2 - 32 - 64, Game.height / 2 - 32, 64, 64, Color.gray));
		PlayState.inventoryPane
				.drawComponent(new Slot(Game.width / 2 - 32 + 64, Game.height / 2 - 32, 64, 64, Color.gray));
		PlayState.inventoryPane.drawComponent(new ItemButton(Game.width / 2 - 32, Game.height / 2 - 32, 64, 64,
				Color.gray, ItemType.wheatSeeds, new Runnable() {
					@Override
					public void run() {
						selectedPlant = Plants.plant_wheat;
					}
				}));
		PlayState.inventoryPane.drawComponent(new ItemButton(Game.width / 2 - 32 - 64, Game.height / 2 - 32, 64, 64,
				Color.gray, ItemType.carrotSeeds, new Runnable() {
					@Override
					public void run() {
						selectedPlant = Plants.plant_carrot;
					}
				}));
		PlayState.inventoryPane.drawComponent(new ItemButton(Game.width / 2 - 32 + 64, Game.height / 2 - 32, 64, 64,
				Color.gray, ItemType.potatoSeeds, new Runnable() {
					@Override
					public void run() {
						selectedPlant = Plants.plant_potato;
					}
				}));
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (working) {
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
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			if (working) {
				if (farmingMode) {
					int blockX = x / Game.blocksize;
					int blockY = y / Game.blocksize;
					Game.world.plant(selectedPlant, blockX, blockY);
				}
			}
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void keyPressed(KeyEvent e, int k) {
		if (e.getKeyCode() == KeyEvent.VK_Q)
			if (farmingMode) {
				showSeedInventory = !showSeedInventory;
				working = !showSeedInventory;
			}
	}
}
