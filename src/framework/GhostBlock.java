package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GhostBlock extends Block {
	public BufferedImage image;
	public boolean canBuild = false;
	int placeX, placeY;
	public ArrayList<Material> materials = new ArrayList<Material>();

	public GhostBlock(float x, float y, int width, int height, Material material) {
		super(x, y, width, height, material);
		image = Game.terrainSpriteSheet.getSprite(material.getID());
		setGhost();
		addMaterials();
	}

	public void addMaterials() {
		materials = new ArrayList<Material>();
		if (material == Material.MachineDrill) {
			materials.add(Material.OreCoal);
			materials.add(Material.OreIron);
			materials.add(Material.OreGold);
		}
		if (material == Material.Conveyor || material == Material.ConveyorInserter
				|| material == Material.ConveyorExtractor || material == Material.MachineSmelter) {
			materials.add(Material.Grass1);
			materials.add(Material.Grass2);
			materials.add(Material.Grass3);
			materials.add(Material.Grass4);
			materials.add(Material.Stone);
			materials.add(Material.OreCoal);
			materials.add(Material.OreIron);
			materials.add(Material.OreGold);
		}
	}

	private void setGhost() {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage gray = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				int rgb = image.getRGB(i, j);
				gray.setRGB(i, j, rgb);
			}
		}
		image = gray;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(image, mx, my, width, height, null);
		g.setColor(new Color(1, 0, 0, .25f));
		if (!canBuild)
			g.fillRect(mx, my, width, height);
	}

	public void setMaterial(Material material) {
		this.material = material;
		this.image = material.getSprite();
		addMaterials();
	}

	@Override
	public void update() {
		mx = (int) PlayState.mousePosition.getX();
		my = (int) PlayState.mousePosition.getY();
		Point mousePosition = PlayState.mousePosition;
		int mouseX = (int) mousePosition.getX();
		int camX = PlayState.camera.getCamX();
		int mouseY = (int) mousePosition.getY();
		int camY = PlayState.camera.getCamY();
		int relativeMouseX = mouseX + camX;
		int relativeMouseY = mouseY + camY;
		int blocksize = Game.blocksize;
		int chunksize = Game.chunksize;
		int chunkX = relativeMouseX / (blocksize * chunksize);
		int blockX = relativeMouseX / (blocksize);
		int chunkY = relativeMouseY / (blocksize * chunksize);
		int blockY = relativeMouseY / (blocksize);
		placeX = chunkX * chunksize * blocksize + blockX * blocksize;
		placeY = chunkY * chunksize * blocksize + blockY * blocksize;
		canBuild = Game.world.canBuildAt(placeX, placeY);
		try {
			Block block = Game.world.getBlockAt(blockX, blockY);
			if (!materials.contains(block.material))
				canBuild = false;
		} catch (Exception e) {
			canBuild = false;
		}
	}

}
