package framework;

public class Node extends Block {
    public int density = 2;
    public ItemType resource;

    public Node(float x, float y, int width, int height, Material material) {
	super(x, y, width, height, material);
	if (material == Material.OreCoal)
	    resource = ItemType.itemCoalOre;
	if (material == Material.OreGold)
	    resource = ItemType.itemGoldOre;
	if (material == Material.OreIron)
	    resource = ItemType.itemIronOre;
    }

}
