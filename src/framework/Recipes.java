package framework;

public enum Recipes {
    IronIngot(new Recipe(ItemType.IronIngot, 1, MachineType.Smelter, new ItemType[] { ItemType.itemIronOre },
	    new int[] { 1 })),
    GoldIngot(new Recipe(ItemType.GoldIngot, 1, MachineType.Smelter,
	    new ItemType[] { ItemType.itemGoldOre, ItemType.itemIronOre }, new int[] { 1, 2 }));

    private Recipe recipe;

    private Recipes(Recipe recipe) {
	this.recipe = recipe;
    }

    public Recipe getRecipe() {
	return this.recipe;
    }

    public int requires(ItemType itemType) {
	return recipe.requires(itemType);
    }
}
