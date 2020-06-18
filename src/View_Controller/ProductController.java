package View_Controller;

import Model.Part;
import Model.Product;
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
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        try {
            mainUI = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainController = loader.getController();
        product = new Product(0,"",0,0,0,0);
    }
    public void loadProductID(int id){
        Product selectedProduct = mainController.sampleInventory.lookupProduct(id);
        loadProduct(selectedProduct);
        System.out.println(selectedProduct.getAllAssociatedParts().size());
        loadAssociatedParts(selectedProduct.getAllAssociatedParts());
    }
    private void loadProduct(Product selectedProduct){
        productId.setText(selectedProduct.getId()+"");
        productName.setText(selectedProduct.getName());
        productInv.setText(selectedProduct.getStock()+"");
        productPrice.setText(selectedProduct.getPrice()+"");
        productMax.setText(selectedProduct.getMax()+"");
        productMin.setText(selectedProduct.getMin()+"");
    }
    private void loadAssociatedParts(ObservableList<Part> parts){
        System.out.println(parts.toString());
        associatedPartsTable.setItems(parts);
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        associatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
//    public void loadPartsList(ObservableList<Part> allParts){
//        availablePartsTable.setItems(allParts);
//        availablePartsID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        availablePartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        availablePartsInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        availablePartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//    }

    @FXML
    private void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void searchAvailablePartsButton(ActionEvent actionEvent) {
        availablePartsTable.setItems(mainController.sampleInventory.lookupPart(searchAvailableParts.getText()));
    }
    public void newAssociatedPart(ActionEvent actionEvent){
        product.addAssociatedPart(availablePartsTable.getSelectionModel().getSelectedItem());
    }
    public void delAssociatedPart(ActionEvent actionEvent){
        product.deleteAssociatedPart(associatedPartsTable.getSelectionModel().getSelectedItem());
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        product.setId(mainController.sampleInventory.getAllProducts().size());
        product.setName(productName.getText());
        product.setPrice(Double.parseDouble(productPrice.getText()));
        product.setStock(Integer.parseInt(productInv.getText()));
        product.setMin(Integer.parseInt(productMin.getText()));
        product.setMax(Integer.parseInt(productMax.getText()));

        mainController.sampleInventory.addProduct(product);
        exitWindow(actionEvent);
    }
}
