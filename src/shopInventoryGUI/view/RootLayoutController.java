package shopInventoryGUI.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import shopInventoryGUI.MainApp;

import java.io.File;

/**
 * Created by Wojciech on 21.03.2017.
 */
public class RootLayoutController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNew() {
        mainApp.getProductData().clear();
        mainApp.setProductFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)",
                "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadProductDataFromFile(file);
        }
    }

    @FXML
    private void handleSave(){
        File productFile = mainApp.getProductFilePath();
        if(productFile!=null){
            mainApp.saveProductDataToFile(productFile);
        }else{
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)",
                "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if(file!=null){
            if(!file.getPath().endsWith(".xml")){
                file = new File(file.getPath()+".xml");
            }
            mainApp.saveProductDataToFile(file);
        }
    }

    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inventor");
        alert.setHeaderText("About");
        alert.setContentText("Author: Wojciech Mazur\n email: wmazur@student.agh.edu.pl");
        alert.showAndWait();
    }

    @FXML
    private void handleExit(){
        System.exit(1);
    }
}
