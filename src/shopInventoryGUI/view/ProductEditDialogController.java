package shopInventoryGUI.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private ComboBox<ProductType> productTypeComboBox;


    private Stage dialogStage;
    private Product product;
    private boolean okClicked =false;

    @FXML
    private void initialize(){

    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage=dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;
        productNameField.setText(product.getNameOfProduct());
        //TODO combobox
        quantityField.setText(Integer.toString(product.getQuantityOfProduct()));
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk(){
        product.setNameOfProduct(productNameField.getText());

        try{product.setQuantityOfProduct(Integer.parseInt((quantityField.getText())));
        }catch (NumberFormatException e){
            product.setQuantityOfProduct(0);
        }

        if(product.getQuantityOfProduct()<0)
            product.setQuantityOfProduct(0);
        //TODO combox


        okClicked=true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if(productNameField.getText()==null || productNameField.getText().length()==0)
            errorMessage+="No valid product name!\n";
        if(quantityField.getText()==null || quantityField.getText().length()==0)
            try{
            Integer.parseInt(quantityField.getText());
            }catch (NumberFormatException e){
            errorMessage+="Not an integer number!";
            }

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
