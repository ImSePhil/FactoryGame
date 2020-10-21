package framework;

import java.util.ArrayList;

public class Inventory {
    public int slots;
    public ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
    private int maxItems;
    public boolean full;

    public Inventory(int inventorySize) {
	this.slots = inventorySize;
	full = false;
    }

    public int getItems() {
	int amount = 0;
	for (ItemStack stack : itemStacks) {
	    amount += stack.items.size();
	}
	return amount;
    }

    public int getMaxItems() {
	return maxItems;
    }

    public boolean addItem(Item item) {
	if (itemStacks.size() == 0) {
	    itemStacks.add(new ItemStack(maxItems));
	}
	for (ItemStack stack : itemStacks) {
	    if (stack.accepts(item)) {
		stack.addItem(item);
		return true;
	    } else {
		full = true;
	    }
	}
	return false;
    }

    @Override
    public String toString() {
	String name = "Inventory\n";
	if (itemStacks.size() == 0)
	    return "Empty";
	for (int i = 0; i < itemStacks.size(); i++) {
	    name += "Slot" + i + "\n";
	    ItemStack stack = itemStacks.get(i);
	    name += stack.toString() + "\n";
	}
	return name;
    }

    public Item transferItem() {
	if (itemStacks.size() == 0)
	    return null;
	for (ItemStack stack : itemStacks) {
	    if (stack.items.size() == 0)
		continue;
	    full = false;
	    return stack.transferItem();
	}

	return null;
    }

    public void setMaxItems(int maxItems) {
	this.maxItems = maxItems;
    }
}
