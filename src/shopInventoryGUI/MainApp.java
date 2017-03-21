package shopInventoryGUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shopInventoryGUI.model.*;
import shopInventoryGUI.model.Product;
import shopInventoryGUI.view.ProductEditDialogController;
import shopInventoryGUI.view.ProductOverviewController;
import shopInventoryGUI.view.RootLayoutController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;


    private ObservableList<shopInventoryGUI.model.Product> productData = FXCollections.observableArrayList();

    /**
     * Constructor, adds sample data
     */
    public MainApp() {
        addSampleData();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("InventoryApp");

        initRootLayout();
        showProductOverview();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout= loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller =loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = getProductFilePath();
        if (file!=null){
            loadProductDataFromFile(file);
        }
    }

    public void showProductOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            SplitPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);

            ProductOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean showProductEditDialog(Product product){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditDialog.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ProductEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Product> getProductData() {
        return productData;
    }

    public File getProductFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if(filePath!=null){
            return new File(filePath);
        }
        else{
            return null;
        }
    }

    public void setProductFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if(file!=null){
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("Inventor - "+file.getName());
        }
        else{
            prefs.remove("filePath");
            primaryStage.setTitle("Inventor");
        }
    }

    public void loadProductDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(ProductListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ProductListWrapper wrapper = (ProductListWrapper) unmarshaller.unmarshal(file);

            productData.clear();
            productData.addAll(wrapper.getProductList());
            setProductFilePath(file);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void saveProductDataToFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(ProductListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ProductListWrapper wrapper = new ProductListWrapper();
            wrapper.setProductList(productData);

            marshaller.marshal(wrapper, file);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void addSampleData() {
        productData.add(new Product("GeForce GTX 1080", ProductType.GPU, 5));
        productData.add(new Product("Intel i7 3960K", ProductType.CPU, 3));
        productData.add(new Product("Kingstone DDR4 8GB", ProductType.RAM, 1));
        productData.add(new Product("AMD Ryzen", ProductType.CPU, -1));
        productData.add(new Product("Dell 27'", ProductType.DISPLAY, 3));
        productData.add(new Product("Asus ROG 12XF300", ProductType.MOTHERBOARD,2));
        productData.add(new Product("Logitech G120", ProductType.OTHER, 9));
        productData.add(new Product("OCZ Golden 900W", ProductType.POWERSUPPLY, 0));
    }

}