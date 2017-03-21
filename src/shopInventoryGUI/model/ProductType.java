package shopInventoryGUI.model;

import java.awt.*;

public enum ProductType {

    CPU("Processor"),
    RAM("Memory"),
    GPU("Graphics Card"),
    MOTHERBOARD("Motherboard"),
    DISPLAY("Display"),
    POWERSUPPLY("Power supply"),
    OTHER("Other");

    private String label;
    ProductType(String label){
        this.label= label;
    }

    @Override
    public String toString() {
        return label;
    }
}
