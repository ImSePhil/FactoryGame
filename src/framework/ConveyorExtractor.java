package framework;

public class ConveyorExtractor extends Conveyor {

	private int genX, genY;
	public Item item;

	public ConveyorExtractor(float x, float y, int width, int height, Material material) {
		super(x, y, width, height, material);
		genX = (int) x + Game.blocksize / 2 - Game.itemsize / 2;
		genY = (int) y + Game.blocksize / 2 - Game.itemsize / 2;
	}

	@Override
	public void update() {
		super.update();
		if (item != null) {
			PlayState.items.add(new Item(genX, genY, Game.itemsize, Game.itemsize, item.itemType));
			item = null;
		}
	}
}
