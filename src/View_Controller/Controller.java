package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private VBox mainScreen;
    @FXML private TableView partsTable;
    @FXML private TableView productsTable;
    @FXML private TableColumn tablePartID;
    @FXML private TableColumn tablePartName;
    @FXML private TableColumn tablePartInv;
    @FXML private TableColumn tablePartPrice;
    @FXML private TableColumn tableProductsID;
    @FXML private TableColumn tableProductsName;
    @FXML private TableColumn tableProductsInv;
    @FXML private TableColumn tableProductsPrice;
    protected Inventory sampleInventory;

    public void initialize() {
        sampleInventory = new Inventory();
        sampleInventory.addProduct(new Product(1, "sample", 1.00,1,1,1));
        sampleInventory.addPart(new InHouse(1, "sample", 1.00, 1, 1, 1, 1));
        partsTable.setItems(sampleInventory.getAllParts());
        tablePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(sampleInventory.getAllParts());
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
    public void addPartButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        window.setTitle("AddPart");
        window.setScene(new Scene(root, 720, 720));
        window.show();
    }
    public void modPartButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModPart.fxml"));
        window.setTitle("ModPart");
        window.setScene(new Scene(root, 720, 720));
        window.show();
    }
    public void delPartButtAction() throws IOException {
    }
    public void addProductButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        window.setTitle("AddProduct");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
    public void modProductButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModProduct.fxml"));
        window.setTitle("ModProduct");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
    public void delProductButtAction() throws IOException {
    }
}
