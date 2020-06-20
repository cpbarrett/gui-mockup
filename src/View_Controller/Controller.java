package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final String mainScreen = "../Views/MainScreen.fxml";
    private static final String addPartScreen = "../Views/AddPart.fxml";
    private static final String modPartScreen = "../Views/ModPart.fxml";
    private static final String addProductScreen = "../Views/AddProduct.fxml";
    private static final String modProductScreen = "../Views/ModProduct.fxml";
    @FXML public TableView<Part> partsTable;
    @FXML public TableView<Product> productsTable;
    @FXML private TableColumn<Part, Integer> tablePartID;
    @FXML private TableColumn<Part, String> tablePartName;
    @FXML private TableColumn<Part, Integer> tablePartInv;
    @FXML private TableColumn<Part, Double> tablePartPrice;
    @FXML private TableColumn<Product, Integer> tableProductsID;
    @FXML private TableColumn<Product, String> tableProductsName;
    @FXML private TableColumn<Product, Integer> tableProductsInv;
    @FXML private TableColumn<Product, Double> tableProductsPrice;
    @FXML private TextField searchParts;
    @FXML private TextField searchProducts;

    @Override
    public void initialize(URL location, ResourceBundle resources) { createInventory(); }

    private void createInventory() {
        tablePartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablePartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tablePartPrice.setStyle("-fx-list-style-type: decimal");

        tableProductsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableProductsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableProductsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableProductsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        partsTable.setItems(InventoryManager.getInventory().getAllParts());
        productsTable.setItems(InventoryManager.getInventory().getAllProducts());
    }
    @FXML
    private void exitButtAction(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
        System.exit(0);
    }
    public void searchPartsButton(ActionEvent actionEvent) {
        try {
            ObservableList<Part> matchingParts = FXCollections.observableArrayList(InventoryManager.getInventory().lookupPart(new Integer(searchParts.getText())));
            partsTable.setItems(matchingParts);
        } catch (NumberFormatException e) {
            partsTable.setItems(InventoryManager.getInventory().lookupPart(searchParts.getText()));
        }
    }
    public void addPartButtAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(addPartScreen));
        Parent partUI = loader.load();
        openNewWindow(actionEvent, partUI);
    }
    public void modPartButtAction(ActionEvent actionEvent) throws IOException {
        if (!partsTable.getSelectionModel().isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(modPartScreen));
            Part part = partsTable.getSelectionModel().getSelectedItem();
            Parent partUI = loader.load();
            PartController partController = loader.getController();
            partController.loadPart(part);
            openNewWindow(actionEvent, partUI);
        }
    }
    public void delPartButtAction() throws IOException {
        if (AlertBox.confirm("Delete")) {
            InventoryManager.getInventory().deletePart(partsTable.getSelectionModel().getSelectedItem());
        }
    }
    public void searchProductsButton(ActionEvent actionEvent) {
        try {
            ObservableList<Product> matchingProducts = FXCollections.observableArrayList(InventoryManager.getInventory().lookupProduct(new Integer(searchProducts.getText())));
            productsTable.setItems(matchingProducts);
        } catch (NumberFormatException e) {
            productsTable.setItems(InventoryManager.getInventory().lookupProduct(searchProducts.getText()));
        }
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(addProductScreen));
        Parent productUI = loader.load();
        ProductController productController = loader.getController();
        productController.loadPartsList();
        openNewWindow(actionEvent, productUI);
    }
    public void modProductButtAction(ActionEvent actionEvent) throws IOException {
        if(!productsTable.getSelectionModel().isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(modProductScreen));
            Product product = productsTable.getSelectionModel().getSelectedItem();
            Parent productUI = loader.load();
            ProductController productController = loader.getController();
            productController.loadProduct(product);
            openNewWindow(actionEvent, productUI);
        }
    }
    public void delProductButtAction() throws IOException {
        if (AlertBox.confirm("Delete")) {
            InventoryManager.getInventory().deleteProduct(productsTable.getSelectionModel().getSelectedItem());
        }
    }
    private void openNewWindow(ActionEvent event, Parent newUI) throws IOException {
        Scene scene = new Scene(newUI);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
