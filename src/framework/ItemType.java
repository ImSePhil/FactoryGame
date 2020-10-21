package framework;

import java.awt.image.BufferedImage;

public enum ItemType {
    itemIronOre(0, "Eisenerz", 1), itemGoldOre(1, "Golderz", 5), itemCoalOre(2, "Kohleerz", 3),
    IronIngot(3, "Eisenbarren", 0), GoldIngot(4, "Goldbarren", 4);

    private String name;
    private int id;
    private int hdid;

    private ItemType(int id, String name) {
	this.name = name;
	this.id = id;
    }

    private ItemType(int id, String name, int hdid) {
	this.name = name;
	this.id = id;
	this.hdid = hdid;
    }

    public String getName() {
	return name;
    }

    public BufferedImage getSprite() {
	return Game.itemSpriteSheet.getSprite(id);
    }

    public BufferedImage getHDSprite() {
	return Game.itemHDSpriteSheet.getSprite(hdid);
    }
}
