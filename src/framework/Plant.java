package framework;

import java.awt.image.BufferedImage;

public enum Plant {
	wheat_stage0(0), wheat_stage1(1), wheat_stage2(2), wheat_stage3(3), wheat_stage4(4), wheat_stage5(5),
	wheat_stage6(6), wheat_stage7(7), carrot_stage0(8), carrot_stage1(9), carrot_stage2(10), carrot_stage3(11),
	carrot_stage4(12), carrot_stage5(13), carrot_stage6(14), carrot_stage7(15), potato_stage0(8), potato_stage1(9),
	potato_stage2(10), potato_stage3(11), potato_stage4(12), potato_stage5(13), potato_stage6(14), potato_stage7(15),
	crop_wheat(wheat_stage0, wheat_stage1, wheat_stage2, wheat_stage3, wheat_stage4, wheat_stage5, wheat_stage6,
			wheat_stage7),
	crop_carrot(carrot_stage0, carrot_stage1, carrot_stage2, carrot_stage3, carrot_stage4, carrot_stage5, carrot_stage6,
			carrot_stage7),
	crop_potato(potato_stage0, potato_stage1, potato_stage2, potato_stage3, potato_stage4, potato_stage5, potato_stage6,
			potato_stage7);
	Plant stage0, stage1, stage2, stage3, stage4, stage5, stage6, stage7;

	int id;

	private Plant(int id) {
		this.id = id;
	}

	Plant(Plant stage0, Plant stage1, Plant stage2, Plant stage3, Plant stage4, Plant stage5, Plant stage6,
			Plant stage7) {
		this.stage0 = stage0;
		this.stage1 = stage1;
		this.stage2 = stage2;
		this.stage3 = stage3;
		this.stage4 = stage4;
		this.stage5 = stage5;
		this.stage6 = stage6;
		this.stage7 = stage7;
	}

	public BufferedImage getSprite() {
		return Game.plantSpriteSheet.getSprite(id);
	}

}
