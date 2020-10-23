package framework;

import java.awt.event.KeyEvent;

public abstract class Machine extends Block implements Interactable {
    public Material activeMaterial;
    protected boolean active;
    private Material inActiveMaterial;
    protected ConveyorInserter inserter;
    protected ConveyorExtractor extractor;

    public Machine(float x, float y, int width, int height, Material material) {
	super(x, y, width, height, material);
	if (material != Material.Conveyor && material != Material.ConveyorExtractor
		&& material != Material.ConveyorInserter) {
	    this.activeMaterial = Material.values()[material.getID() + 1];
	    this.inActiveMaterial = material;
	}
	active = true;
    }

    @Override
    public void update() {
	super.update();
	if (material != Material.Conveyor && material != Material.ConveyorExtractor
		&& material != Material.ConveyorInserter) {
	    if (active)
		image = activeMaterial.getSprite();
	    else
		image = inActiveMaterial.getSprite();
	}

	if (inserter == null || extractor == null) {
	    Machine r = Game.world.getMachineAt((int) x + 64, (int) y);
	    Machine l = Game.world.getMachineAt((int) x - 64, (int) y);
	    Machine u = Game.world.getMachineAt((int) x, (int) y - 64);
	    Machine d = Game.world.getMachineAt((int) x, (int) y + 64);
	    if (inserter == null) {
		if (r != null)
		    if (r instanceof ConveyorInserter)
			inserter = (ConveyorInserter) r;
		if (l != null)
		    if (l instanceof ConveyorInserter)
			inserter = (ConveyorInserter) l;
		if (u != null)
		    if (u instanceof ConveyorInserter)
			inserter = (ConveyorInserter) u;
		if (d != null)
		    if (d instanceof ConveyorInserter)
			inserter = (ConveyorInserter) d;

	    }
	    if (extractor == null) {
		if (r != null)
		    if (r instanceof ConveyorExtractor)
			extractor = (ConveyorExtractor) r;
		if (l != null)
		    if (l instanceof ConveyorExtractor)
			extractor = (ConveyorExtractor) l;
		if (u != null)
		    if (u instanceof ConveyorExtractor)
			extractor = (ConveyorExtractor) u;
		if (d != null)
		    if (d instanceof ConveyorExtractor)
			extractor = (ConveyorExtractor) d;
	    }
	}
    }

    @Override
    public float getX() {
	return x;
    }

    @Override
    public float getY() {
	return y;
    }

    public void keyPressed(KeyEvent e, int k) {
	if (mouseOver) {
	    if (e.getKeyCode() == KeyEvent.VK_E) {
		interact();
	    }
	}
    }
}
