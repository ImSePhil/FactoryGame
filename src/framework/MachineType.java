package framework;

public enum MachineType {
    drill(1, Drill.class), Conveyor(1, Conveyor.class), ConveyorExtractor(1, ConveyorExtractor.class),
    ConveyorInserter(1, ConveyorInserter.class), Smelter(1, Smelter.class);

    public int inventorySize;
    public Class<?> class_;

    private MachineType(int inventorySize, Class<?> class_) {
	this.inventorySize = inventorySize;
	this.class_ = class_;
    }

}
