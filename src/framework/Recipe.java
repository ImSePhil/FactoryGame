package framework;

import java.util.ArrayList;

public class Recipe {

    public MachineType manufacturer;
    public ItemType result;
    public int resultAmount;

    public ArrayList<ItemType> materials = new ArrayList<ItemType>();
    public ArrayList<Integer> amounts = new ArrayList<Integer>();

    public Recipe(ItemType result, int resultAmount, MachineType manufacturer, ItemType[] materials, int[] amounts) {
	this.result = result;
	this.resultAmount = resultAmount;
	this.manufacturer = manufacturer;

	for (ItemType item : materials) {
	    this.materials.add(item);
	}
	for (int amount : amounts) {
	    this.amounts.add(amount);
	}
    }

    public int requires(ItemType itemType) {
	if (materials.contains(itemType)) {
	    int pos = materials.indexOf(itemType);
	    return amounts.get(pos);
	}
	return 0;
    }

}
