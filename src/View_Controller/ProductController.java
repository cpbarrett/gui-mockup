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

public class ProductController implements Initializable {
    @FXML private TableView<Part> availablePartsTable;
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> availablePartsID;
    @FXML private TableColumn<Part, String> availablePartsName;
    @FXML private TableColumn<Part, Integer> availablePartsInv;
    @FXML private TableColumn<Part, Double> availablePartsPrice;
    @FXML private TableColumn<Part, Integer> associatedPartID;
    @FXML private TableColumn<Part, String> associatedPartName;
    @FXML private TableColumn<Part, Integer> associatedPartInv;
    @FXML private TableColumn<Part, Double> associatedPartPrice;
    @FXML private TextField productId;
    @FXML private TextField productName;
    @FXML private TextField productInv;
    @FXML private TextField productPrice;
    @FXML private TextField productMin;
    @FXML private TextField productMax;
    @FXML private TextField searchAvailableParts;
    private Parent mainUI;
    private Controller mainController;
    private Product product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/MainScreen.fxml"));
        try {
            mainUI = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadPartsList();
        this.product = new Product(0,"",0,0,0,0);
    }
    public void loadProduct(Product selectedProduct){
        this.product = selectedProduct;
        productId.setText(selectedProduct.getId()+"");
        productName.setText(selectedProduct.getName());
        productInv.setText(selectedProduct.getStock()+"");
        productPrice.setText(selectedProduct.getPrice()+"");
        productMax.setText(selectedProduct.getMax()+"");
        productMin.setText(selectedProduct.getMin()+"");
        loadAssociatedParts(selectedProduct.getAllAssociatedParts());
    }
    private void loadAssociatedParts(ObservableList<Part> parts){
        associatedPartsTable.setItems(parts);
    }
    public void loadPartsList(){
        availablePartsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        availablePartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availablePartsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availablePartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        availablePartsTable.setItems(InventoryManager.getInventory().getAllParts());

        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void searchAvailablePartsButton(ActionEvent actionEvent) {
        try {
            ObservableList<Part> matchingParts = FXCollections.observableArrayList(InventoryManager.getInventory().lookupPart(Integer.parseInt(searchAvailableParts.getText())));
            availablePartsTable.setItems(matchingParts);
        } catch (NumberFormatException e){
            availablePartsTable.setItems(InventoryManager.getInventory().lookupPart(searchAvailableParts.getText()));
        }
    }
    public void newAssociatedPart(ActionEvent actionEvent){
        Part part = availablePartsTable.getSelectionModel().getSelectedItem();
        if (product.getAllAssociatedParts().contains(part)){
            AlertBox.display("Warning.", "Associated Parts cannot be duplicated.");
            return;
        }
        product.addAssociatedPart(part);
        loadAssociatedParts(product.getAllAssociatedParts());
    }
    public void delAssociatedPart(ActionEvent actionEvent){
        if (AlertBox.confirm("Delete")) {
            Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
            product.deleteAssociatedPart(part);
        }
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        if (!validateProduct()){
            return;
        }
        product.setId(InventoryManager.generateProductId());
        product.setName(productName.getText());
        product.setPrice(Double.parseDouble(productPrice.getText()));
        product.setStock(Integer.parseInt(productInv.getText()));
        product.setMin(Integer.parseInt(productMin.getText()));
        product.setMax(Integer.parseInt(productMax.getText()));

        InventoryManager.getInventory().addProduct(product);
        exitWindow(actionEvent);
    }
    public void updateProductButtAction(ActionEvent actionEvent) throws IOException {
        if (!validateProduct()){
            return;
        }
        product.setName(productName.getText());
        product.setPrice(Double.parseDouble(productPrice.getText()));
        product.setStock(Integer.parseInt(productInv.getText()));
        product.setMin(Integer.parseInt(productMin.getText()));
        product.setMax(Integer.parseInt(productMax.getText()));

        InventoryManager.getInventory().updateProduct(Integer.parseInt(productId.getText()),product);
        exitWindow(actionEvent);
    }
    public void cancelProduct(ActionEvent actionEvent) throws IOException {
        if (AlertBox.confirm("Cancel")) {
            exitWindow(actionEvent);
        }
    }
    private void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    private boolean validateProduct(){
        try {
            int id = Integer.parseInt(productId.getText());
            int stock = Integer.parseInt(productInv.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());
            Double price = Double.parseDouble(productPrice.getText());

            if (min >= max){
                AlertBox.display("Error", "Min must be less than Max!");
                return false;
            }
            if (stock > max){
                AlertBox.display("Error", "Inventory Stock cannot be more than Max Stock.");
                return false;
            }
            if (stock < min){
                AlertBox.display("Error", "Inventory Stock cannot be less than Min Stock.");
                return false;
            }
            if ((stock < 0) || (max < 1) || (min < 0)){
                AlertBox.display("Error", "Stock and Min must not be less than 0. Max must be at least 1.");
                return false;
            }
            if (price < InventoryManager.sumOfParts(id)){
                AlertBox.display("Error", "A product cannot cost less than the sum of it's parts.");
                return false;
            }
            if (associatedPartsTable.getItems().isEmpty()){
                AlertBox.display("No parts.", "A product must have at least 1 associated part.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            AlertBox.display("Uh-Oh", "Use only numbers for non-name fields.");
            e.printStackTrace();
            return false;
        } catch (NullPointerException e){
            AlertBox.display("Blank Field", "Please enter a value for all fields.");
            e.printStackTrace();
            return false;
        }
    }
}
