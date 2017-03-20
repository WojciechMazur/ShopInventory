package shopInventoryGUI.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import shopInventoryGUI.MainApp;
import shopInventoryGUI.model.Product;
import shopInventoryGUI.model.ProductType;


/**
 * Created by Wojciech on 19.03.2017.
 */
public class ProductOverviewController {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, ProductType> productTypeColumn;

    @FXML
    private Label nameProductLabel;
    @FXML
    private Label typeProductLabel;
    @FXML
    private Label quantintyProductLabel;
    @FXML
    private Label inStockLabel;

    private MainApp mainApp;

    public ProductOverviewController(){
    }

    @FXML
    private void initialize(){
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productTypeColumn.setCellValueFactory(cellData -> cellData.getValue().productTypeObjectProperty());

        showProductDetails(null);
        productTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showProductDetails(newValue)));
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp= mainApp;
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product product){
        if(product!=null){
            nameProductLabel.setText(product.getNameOfProduct());
            typeProductLabel.setText(product.getTypeOfProduct().toString());
            quantintyProductLabel.setText(Integer.toString(product.getQuantityOfProduct()));
            inStockLabel.setText(product.isProductInStockProperty().toString());
        }
        else{
            nameProductLabel.setText("");
            typeProductLabel.setText("");
            quantintyProductLabel.setText("");
            inStockLabel.setText("");
        }
    }
    @FXML
    private void handleDeleteProduct(){
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0)
            productTable.getItems().remove(selectedIndex);
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Not selected");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product and try again");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewProduct(){
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
        if(okClicked){
            mainApp.getProductData().add(tempProduct);
        }
    }

    @FXML
    private void handleEditProduct(){
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if(selectedProduct!=null) {
            boolean okClicked = mainApp.showProductEditDialog(selectedProduct);
            if(okClicked)
                showProductDetails(selectedProduct);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a person and try again");
            alert.showAndWait();
        }

    }
}
