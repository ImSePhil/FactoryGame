package framework;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Builder {
    private GhostBlock ghost;
    private Material blockType;
    int x;
    int y;
    private ArrayList<Material> buildables;
    private int selected;

    public Builder() {
	buildables = new ArrayList<Material>();
	buildables.add(Material.MachineDrill);
	buildables.add(Material.MachineSmelter);
	buildables.add(Material.Conveyor);
	buildables.add(Material.ConveyorExtractor);
	buildables.add(Material.ConveyorInserter);
	selected = 0;
	this.blockType = buildables.get(selected);
	this.ghost = new GhostBlock(x, y, Game.blocksize, Game.blocksize, blockType);
    }

    public void setBlockType(Material blockType) {
	this.blockType = blockType;
	this.ghost = new GhostBlock(x, y, Game.blocksize, Game.blocksize, blockType);
    }

    public void update() {
	if (ghost != null) {
	    ghost.update();
	    ghost.setMaterial(buildables.get(selected));
	    blockType = buildables.get(selected);
	}
    }

    public void render(Graphics2D g) {
	if (ghost != null)
	    ghost.render(g);
    }

    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1)
	    if (PlayState.buildMode)
		if (ghost.canBuild)
		    Game.world.place(blockType);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
	selected += e.getWheelRotation();
	if (selected == buildables.size()) {
	    selected = 0;
	}
	if (selected == -1) {
	    selected = buildables.size() - 1;
	}
    }
}
