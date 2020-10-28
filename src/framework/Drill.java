package framework;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class Drill extends Machine implements ActionListener {
	public Node node;
	public Timer timer;
	private int maxItems = 100;
	private List<Item> items = new ArrayList<Item>();

	public Drill(float x, float y, int width, int height, Material material) {
		super(x, y, width, height, material);
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	public void update() {
		super.update();
		int blockX = (int) x / Game.blocksize;
		int blockY = (int) y / Game.blocksize;
		node = (Node) Game.world.getBlockAt(blockX, blockY);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void interact() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < node.density; i++) {
			mine();
		}
		if (extractor != null) {
			if (extractor.item == null) {
				if (items.size() != 0) {
					Item item = items.get(items.size() - 1);
					extractor.item = item;
					items.remove(item);
				}
			}
		}
	}

	private void mine() {
		if (items.size() < maxItems) {
			items.add(new Item(x, y, Game.itemsize, Game.itemsize, node.resource));
		}
	}

}
