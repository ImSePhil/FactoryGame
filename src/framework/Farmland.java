package framework;

import java.awt.Graphics2D;

public class Farmland extends Block {

	private Plants plant;
	private Crop crop;

	public Farmland(float x, float y, int width, int height, Material material) {
		super(x, y, width, height, material);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (crop != null)
			crop.render(g);
	}

	@Override
	public void update() {
		super.update();
		if (crop != null)
			crop.update();
	}

	public void setPlant(Plants selectedPlant) {
		this.plant = selectedPlant;
		crop = new Crop(x, y, width, height, plant.getCrop());
	}

}
