package framework;

import java.util.ArrayList;

public class ItemStack {
    public ArrayList<Item> items;
    public int maxStackSize;

    public ItemStack(int maxItems) {
	items = new ArrayList<Item>();
	this.maxStackSize = maxItems;
    }

    public boolean accepts(Item item) {
	if (items.size() == 0)
	    return true;
	if (items.size() >= maxStackSize)
	    return false;
	if (items.get(0).itemType == item.itemType)
	    return true;
	return false;
    }

    public void addItem(Item item) {
	items.add(item);
    }

    @Override
    public String toString() {
	if (items.size() == 0)
	    return "Empty";
	return items.get(0).itemType.getName() + " " + items.size();
    }

    public Item transferItem() {
	Item item = items.get(items.size() - 1);
	items.remove(item);
	return item;
    }

}
