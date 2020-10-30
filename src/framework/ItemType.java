package framework;

import java.awt.image.BufferedImage;

public enum ItemType {
	itemIronOre(0, "Eisenerz", 0), itemGoldOre(1, "Golderz", 1), itemCoalOre(2, "Kohleerz", 2),
	IronIngot(3, "Eisenbarren", 3), GoldIngot(4, "Goldbarren", 4), Wheat(5, "Weizen",5), wheatSeeds(6, "Weizensamen",6),
	Potato(7, "Kartoffel",7), potatoSeeds(8, "Kartoffelsamen",8), Carrot(9, "Karotte",9), carrotSeeds(10, "Karottensamen",10)
	
	;

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
