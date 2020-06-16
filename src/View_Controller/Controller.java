package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Inventory sampleInventory;
    private final String mainScreen = "MainScreen.fxml";
    private final String addPartScreen = "AddPart.fxml";
    private final String modPartScreen = "ModPart.fxml";
    private final String addProductScreen = "AddProduct.fxml";
    private final String modProductScreen = "ModProduct.fxml";

    @FXML private TableView<Part> partsTable;
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Part, Integer> tablePartID;
    @FXML private TableColumn<Part, String> tablePartName;
    @FXML private TableColumn<Part, Integer> tablePartInv;
    @FXML private TableColumn<Part, Double> tablePartPrice;
    @FXML private TableColumn<Product, Integer> tableProductsID;
    @FXML private TableColumn<Product, String> tableProductsName;
    @FXML private TableColumn<Product, Integer> tableProductsInv;
    @FXML private TableColumn<Product, Double> tableProductsPrice;
    @FXML TextField addPartName;
    @FXML TextField addPartPrice;
    @FXML TextField addPartInv;
    @FXML TextField addPartMin;
    @FXML TextField addPartMax;
    @FXML TextField addPartMachID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createInventory();
    }

    private void createInventory() {
        sampleInventory = new Inventory();
        sampleInventory.addProduct(new Product(0, "sample", 1.00,1,1,1));
        sampleInventory.addPart(new InHouse(0, "sample", 1.00, 1, 1, 1, 1));

        partsTable.setItems(sampleInventory.getAllParts());
        productsTable.setItems(sampleInventory.getAllProducts());

        tablePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableProductsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableProductsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableProductsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableProductsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    public void exitButtAction(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
        System.exit(0);
    }
    public void addPartButtAction(ActionEvent actionEvent) throws IOException{
        openNewWindow(actionEvent, addPartScreen);
    }
    public void modPartButtAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(modPartScreen));
        Parent productUI = loader.load();
        PartController partController = loader.getController();
        partController.loadPart(partsTable.getSelectionModel().getSelectedItem());
        openNewWindow(actionEvent, modPartScreen);
    }
    public void delPartButtAction() throws IOException {
        sampleInventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        openNewWindow(actionEvent, addProductScreen);
    }
    public void modProductButtAction(ActionEvent actionEvent) throws IOException {
        openNewWindow(actionEvent, modProductScreen);
    }
    public void delProductButtAction() throws IOException {
        sampleInventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
    }
    private void openNewWindow(ActionEvent event, String screen) throws IOException {
        Parent productUI = FXMLLoader.load(getClass().getResource(screen));
        Scene scene = new Scene(productUI);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    private void exitWindow(VBox screen){
        Stage window = (Stage) screen.getScene().getWindow();
        window.close();
    }
}
