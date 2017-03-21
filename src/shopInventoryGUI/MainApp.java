package shopInventoryGUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    import java.io.IOException;

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
            rootLayout= (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProductOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            SplitPane personOverview = (SplitPane) loader.load();

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
            GridPane page = (GridPane) loader.load();

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