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
    }
}
