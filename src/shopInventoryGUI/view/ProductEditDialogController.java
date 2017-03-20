package shopInventoryGUI.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import shopInventoryGUI.model.Product;
import shopInventoryGUI.model.ProductType;

/**
 * Created by Wojciech on 20.03.2017.
 */
public class ProductEditDialogController {
    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<ProductType> productTypeComboBox =new ComboBox<>();
    @FXML
    private CheckBox inStockCheckBox = new CheckBox();

    private Stage dialogStage;
    private Product product;
    private boolean okClicked =false;

    @FXML
    private void initialize(){
        productTypeComboBox.setItems(FXCollections.observableArrayList(ProductType.values()));
        inStockCheckBox.setSelected(true);
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;
        productNameField.setText(product.getNameOfProduct());
        quantityField.setText(Integer.toString(product.getQuantityOfProduct()));
        productTypeComboBox.getSelectionModel().select(product.getTypeOfProduct());
        inStockCheckBox.setSelected(product.isProductInStockProperty().getValue());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            product.setNameOfProduct(productNameField.getText());

            try {
                product.setQuantityOfProduct(Integer.parseInt((quantityField.getText())));
            } catch (NumberFormatException e) {
                product.setQuantityOfProduct(0);
            }

            if (product.getQuantityOfProduct() < 0)
                product.setQuantityOfProduct(0);

            product.setTypeOfProduct(productTypeComboBox.getSelectionModel().getSelectedItem());
            product.setIsProductInStock(inStockCheckBox.isSelected() && product.getQuantityOfProduct() > 0 ? true : false);

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if(productNameField.getText()==null || productNameField.getText().length()==0)
            errorMessage+="No valid product name!\n";
        try{
            Integer.parseInt(quantityField.getText());
            }catch (NumberFormatException e) {
            errorMessage += "Not an integer number!\n";
        }
        if(productTypeComboBox.getSelectionModel().isEmpty())
            errorMessage+="No type selected!\n";

     if(errorMessage.length()==0) {
         return true;
     }
     else {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.initOwner(dialogStage);
         alert.setTitle("Invalid data");
         alert.setHeaderText("Please correct invalid fields");
         alert.setContentText(errorMessage);
         alert.showAndWait();
         return false;
     }
    }

}
