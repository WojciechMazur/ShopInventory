package shopInventoryGUI.model;

import javafx.beans.property.*;

public class Product {
    private final StringProperty nameOfProduct;
    private final ObjectProperty<ProductType> typeOfProduct;
    private final IntegerProperty quantityOfProduct;
    private final BooleanProperty isProductInStock;

    public Product(String name, ProductType type, Integer quantity){
        this.nameOfProduct =new SimpleStringProperty(name);
        this.typeOfProduct = new SimpleObjectProperty<>(type);
        if(quantity==null || quantity<0)
            quantity=0;
        this.quantityOfProduct=new SimpleIntegerProperty(quantity);
        this.isProductInStock = new SimpleBooleanProperty((quantity==0)? false : true);
    }

    public Product(){
        this(null, null, null);
    }

    public String getNameOfProduct() {
        return nameOfProduct.get();
    }

    public ProductType getTypeOfProduct() {
        return typeOfProduct.get();
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct.get();
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct.set(quantityOfProduct);
    }

    public void setTypeOfProduct(ProductType typeOfProduct) {
        this.typeOfProduct.set(typeOfProduct);
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct.set(nameOfProduct);
    }

    public void setIsProductInStock(boolean isProductInStock) {
        this.isProductInStock.set(isProductInStock);
    }

    public StringProperty productNameProperty(){
        return nameOfProduct;
    }

    public ObjectProperty<ProductType> productTypeObjectProperty(){
        return typeOfProduct;
    }

    public IntegerProperty quantityOfProductProperty(){
        return quantityOfProduct;
    }

    public BooleanProperty isProductInStockProperty(){
        return isProductInStock;
    }
}
