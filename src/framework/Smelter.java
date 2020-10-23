package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import framework.inventory.Arrow;
import framework.inventory.Button;
import framework.inventory.DrawItem;
import framework.inventory.Slot;

public class Smelter extends Machine implements ActionListener {
	private List<Item> input = new ArrayList<Item>();
	private List<Item> output = new ArrayList<Item>();
	private Timer timer;
	private Recipes recipe;
	private boolean showInventory;
	private int maxItems = 10;
	private int selected;
	private boolean canCraft;
	

	public Smelter(float x, float y, int width, int height, Material material) {
		super(x, y, width, height, material);
		recipe = Recipes.IronIngot;
		timer = new Timer(1000, this);
		timer.start();
		selected = 0;
	}

	@Override
	public void update() {
		super.update();
		checkRecipe();
	}

	private void checkRecipe() {
		if(output.size() >= maxItems) {
			canCraft = false;
			return;
		}
		List<ItemType> materials = recipe.getRecipe().materials;
		List<Integer> amounts = recipe.getRecipe().amounts;
		List<ItemType> _input = new ArrayList<ItemType>();
		input.forEach(i -> _input.add(i.itemType));
		_input.forEach(i -> System.out.println(i));
		System.out.println("________");
		if (input.size() == 0) {
			canCraft = false;
			return;
		}
		if (materials.size() == 0) {
			canCraft = false;
			return;
		}
		if (input.get(0).itemType == materials.get(0)) {
			if (input.size() >= amounts.get(0))
				canCraft = true;
		}
	}

	public void nextRecipe() {
		selected++;
		if (Recipes.values().length == selected)
			selected = 0;
		recipe = Recipes.values()[selected];
		if (recipe.getRecipe().manufacturer != material.getMachineType())
			nextRecipe();
		input.clear();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inserter != null) {
			if (inserter.item != null) {
				if (recipe.requires(inserter.item.itemType) != 0) {
					if (input.size() < maxItems) {
						input.add(inserter.item);
						inserter.item = null;
					}
				}
			}
		}
		craft();
		if (extractor != null) {
			if (extractor.item == null) {
				if (output.size() != 0) {
					Item item = output.get(output.size() - 1);
					extractor.item = item;
					output.remove(item);
					System.out.println("Moved");
				}
			}
		}
	}

	private void craft() {
		if (canCraft) {
			System.out.println("CRAFTED");
			int s = recipe.getRecipe().amounts.get(0);
			for(int i = 0; i < s; i++) {
				input.remove(0);
			}
			for(int j = 0; j < recipe.getRecipe().resultAmount; j++) {
				output.add(new Item(0, 0, Game.itemsize, Game.itemsize, recipe.getRecipe().result));
			}
			
			
			canCraft = false;
		}
	}

	@Override
	public void interact() {
		showInventory = !showInventory;
		if (showInventory == false) {
			PlayState.inventoryPane.reset();
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (showInventory) {
			PlayState.inventoryPane.reset();

			PlayState.inventoryPane
					.drawComponent(new Slot(Game.width / 2 - 96, Game.height / 2 - 32, 64, 64, Color.gray));
			PlayState.inventoryPane
					.drawComponent(new Arrow(Game.width / 2 - 32, Game.height / 2 - 32, 64, 64, Color.gray));
			PlayState.inventoryPane
					.drawComponent(new Slot(Game.width / 2 + 32, Game.height / 2 - 32, 64, 64, Color.gray));
			if (input.size() != 0) {

				PlayState.inventoryPane.drawComponent(new DrawItem(Game.width / 2 - 96, Game.height / 2 - 32, 64, 64,
						Color.black, input.size(), input.get(0).itemType));
			}
			if (output.size() != 0) {

				PlayState.inventoryPane.drawComponent(new DrawItem(Game.width / 2 + 32, Game.height / 2 - 32, 64, 64,
						Color.black, output.size(), output.get(0).itemType));
			}
			PlayState.inventoryPane.drawComponent(
					new Button(Game.width / 2 - 96, Game.height / 2 + 32, 64 * 3, 64, Color.gray, new Runnable() {
						@Override
						public void run() {
							nextRecipe();
						}
					}, recipe.getRecipe().result.getName()));
			int step = 0;
			for (ItemType material : recipe.getRecipe().materials) {
				PlayState.inventoryPane.drawComponent(
						new Slot(Game.width / 2 - 48, Game.height / 2 + 96 + 32 * step, 32, 32, Color.gray));
				PlayState.inventoryPane.drawComponent(new DrawItem(Game.width / 2 - 48,
						Game.height / 2 + 96 + 32 * step, 32, 32, Color.black,
						recipe.getRecipe().amounts.get(recipe.getRecipe().materials.indexOf(material)), material));
				step++;
			}
			PlayState.inventoryPane
					.drawComponent(new Arrow(Game.width / 2 - 16, Game.height / 2 + 96, 32, 32, Color.gray));
			PlayState.inventoryPane
					.drawComponent(new Slot(Game.width / 2 + 16, Game.height / 2 + 96, 32, 32, Color.gray));
			PlayState.inventoryPane.drawComponent(new DrawItem(Game.width / 2 + 16, Game.height / 2 + 96, 32, 32,
					Color.black, recipe.getRecipe().resultAmount, recipe.getRecipe().result));
		}
	}
}
