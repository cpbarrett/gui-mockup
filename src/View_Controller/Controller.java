package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Inventory sampleInventory;
    @FXML VBox mainScreen;
    @FXML VBox addPartScreen;
    @FXML VBox modPartScreen;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createInventory();

        System.out.println("Part Count" + sampleInventory.getAllParts().size());
        System.out.println("Prod Count:" + sampleInventory.getAllProducts().size());
        System.out.println(productsTable.getItems().isEmpty());
        System.out.println(partsTable.getItems().isEmpty());
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

    public void exitButtAction(){
        Stage window = (Stage) mainScreen.getScene().getWindow();
        window.close();
        System.exit(0);
    }
    public void addPartButtAction() throws IOException{
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        window.setTitle("Add New Part");
        window.setScene(new Scene(root, 640, 480));
        window.show();
    }
    public void modPartButtAction() throws IOException {

    }
    public void delPartButtAction() throws IOException {
    }
    public void addProductButtAction() throws IOException {

    }
    public void modProductButtAction() throws IOException {

    }
    public void delProductButtAction() throws IOException {
    }
}
