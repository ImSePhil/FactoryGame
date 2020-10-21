package framework;

import java.awt.image.BufferedImage;

public enum Material {
    Grass1(0, true), Grass2(1, true), Grass3(2, true), Grass4(3, true), Stone(4, true), OreCoal(5, true),
    OreIron(6, true), OreGold(7, true), MachineDrill(8, false, MachineType.drill),
    MachineDrillOn(9, false, MachineType.drill), Conveyor(10, true, MachineType.Conveyor),
    ConveyorExtractor(11, true, MachineType.ConveyorExtractor),
    ConveyorInserter(12, true, MachineType.ConveyorInserter), MachineSmelter(13, false, MachineType.Smelter),
    MachineSmelterOn(14, false, MachineType.Smelter);

    private int id;
    private boolean walkable;
    private MachineType machineType;

    private Material(int id, boolean walkable) {
	this.id = id;
	this.walkable = walkable;
    }

    private Material(int id, boolean walkable, MachineType machineType) {
	this.id = id;
	this.walkable = walkable;
	this.machineType = machineType;
    }

    public MachineType getMachineType() {
	return this.machineType;
    }

    public BufferedImage getSprite() {
	return Game.terrainSpriteSheet.getSprite(id);
    }

    public int getID() {
	return id;
    }

    public boolean isWalkable() {
	return walkable;
    }

}
