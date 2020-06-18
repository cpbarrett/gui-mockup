package View_Controller;

import Model.*;
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
    Inventory sampleInventory;
    private static final String mainScreen = "MainScreen.fxml";
    private static final String addPartScreen = "AddPart.fxml";
    private static final String modPartScreen = "ModPart.fxml";
    private static final String addProductScreen = "AddProduct.fxml";
    private static final String modProductScreen = "ModProduct.fxml";
    @FXML public TableView<Part> partsTable;
    @FXML private TableView<Product> productsTable;
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
    public void initialize(URL location, ResourceBundle resources) {
        createInventory();
    }

    private void createInventory() {
        sampleInventory = new Inventory();
        sampleInventory.addProduct(new Product(0, "sample", 1.00,1,1,1));
        sampleInventory.addPart(new InHouse(0, "inHouseSample", 1.00, 1, 1, 1, 1));
        sampleInventory.addPart(new Outsourced(1, "OutsourcedSample", 1.00, 1,1,1, "comp"));
        sampleInventory.lookupProduct(0).addAssociatedPart(sampleInventory.lookupPart(0));

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
    @FXML
    private void exitButtAction(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
        System.exit(0);
    }
    public void searchPartsButton(ActionEvent actionEvent) {
        partsTable.setItems(sampleInventory.lookupPart(searchParts.getText()));
    }
    public void addPartButtAction(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(addPartScreen));
        Parent partUI = loader.load();
        openNewWindow(actionEvent, partUI);
    }
    public void modPartButtAction(ActionEvent actionEvent) throws IOException {
        if (!partsTable.getSelectionModel().isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(modPartScreen));
            int id = partsTable.getSelectionModel().getSelectedItem().getId();
            Parent partUI = loader.load();
            PartController partController = loader.getController();
            partController.loadPartID(id);
            openNewWindow(actionEvent, partUI);
        }
    }
    public void delPartButtAction() throws IOException {
        sampleInventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
    }
    public void searchProductsButton(ActionEvent actionEvent) {
        productsTable.setItems(sampleInventory.lookupProduct(searchProducts.getText()));
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(addProductScreen));
        Parent productUI = loader.load();
        ProductController productController = loader.getController();
        //productController.loadPartsList(sampleInventory.getAllParts());
        openNewWindow(actionEvent, productUI);
    }
    public void modProductButtAction(ActionEvent actionEvent) throws IOException {
        if(!productsTable.getSelectionModel().isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(modProductScreen));
            int id = productsTable.getSelectionModel().getSelectedItem().getId();
            Parent productUI = loader.load();
            ProductController productController = loader.getController();
            productController.loadProductID(id);
            openNewWindow(actionEvent, productUI);
        }
    }
    public void delProductButtAction() throws IOException {
        sampleInventory.deleteProduct(productsTable.getSelectionModel().getSelectedItem());
    }
    private void openNewWindow(ActionEvent event, Parent newUI) throws IOException {
        Scene scene = new Scene(newUI);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
