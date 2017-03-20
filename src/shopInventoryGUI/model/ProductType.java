package shopInventoryGUI.model;

public enum ProductType {
    CPU{
        @Override
        public String toString() {
            return "CPU";
        }
    },
    RAM{
        @Override
        public String toString() {
            return "RAM";
        }
    },
    GPU{
        @Override
        public String toString() {
            return "Graphics card";
        }
    },
    MOTHERBOARD{
        @Override
        public String toString() {
            return "Motherboard";
        }
    },
    DISPLAY{
        @Override
        public String toString() {
            return "Display";
        }
    },
    POWERSUPPLY{
        @Override
        public String toString() {
            return "Power supply";
        }
    },
    OTHER{
        @Override
        public String toString() {
            return "Other";
        }
    }

}
