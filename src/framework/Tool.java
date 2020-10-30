package framework;

import java.awt.image.BufferedImage;

public enum Tool {
	Hammer(0),
	Hoe(1);
	
	private int id;
	
	private Tool(int id) {
		this.id = id;
	}
	
	public BufferedImage getSprite() {
		return Game.toolSpriteSheet.getSprite(id);
	}

	public int getID() {
		return id;
	}
}
