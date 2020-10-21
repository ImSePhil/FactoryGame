package framework;

import java.awt.Graphics2D;

public class ConveyorInserter extends Conveyor {

    public Item item;

    public ConveyorInserter(float x, float y, int width, int height, Material material) {
	super(x, y, width, height, material);
    }

    @Override
    public void update() {
	super.update();
	if (!PlayState.isSlotFree((int) x + 32, (int) y + 32) && item == null) {
	    for (Item i : PlayState.items) {
		if (i.x == x + 32)
		    if (i.y == y + 32) {
			item = i;
			PlayState.items.remove(i);
			break;
		    }
	    }
	}
    }

    @Override
    public void render(Graphics2D g) {
	super.render(g);
    }
}
