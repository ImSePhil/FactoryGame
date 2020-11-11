package framework;

public enum Plants {
	plant_wheat(Plant.crop_wheat, ItemType.wheatSeeds, ItemType.Wheat),
	plant_carrot(Plant.crop_carrot, ItemType.carrotSeeds, ItemType.Carrot),
	plant_potato(Plant.crop_potato, ItemType.potatoSeeds, ItemType.Potato);

	private Plant crop;
	private ItemType seeds;
	private ItemType drop;

	Plants(Plant crop, ItemType seeds, ItemType drop) {
		this.crop = crop;
		this.seeds = seeds;
		this.drop = drop;
	}
	
	public String getSeedName() {
		return seeds.getName();
	}
	
	public Plant getCrop() {
		return crop;
	}
}