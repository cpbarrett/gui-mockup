package View_Controller;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    private TableView associatedPartsTable;
    //public TableColumn associatedPartID;
    @FXML private TextField productId;
    @FXML private TextField productName;
    @FXML private TextField productInv;
    @FXML private TextField productPrice;
    @FXML private TextField productMin;
    @FXML private TextField productMax;
    private Parent mainUI;
    private Controller mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        try {
            mainUI = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController = loader.getController();
    }
    public void setAssociatedParts(int id){
        associatedPartsTable.setItems(mainController.sampleInventory.lookupProduct(id).getAllAssociatedParts());
    }
    public void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void searchPartsButton(ActionEvent actionEvent) {
    }
    public void addProductButtAction(ActionEvent actionEvent) throws IOException {
        mainController.sampleInventory.addProduct(new Product(
                mainController.sampleInventory.getAllProducts().size(),
                productName.getText(),
                Double.parseDouble(productPrice.getText()),
                Integer.parseInt(productInv.getText()),
                Integer.parseInt(productMin.getText()),
                Integer.parseInt(productMax.getText())
        ));
        exitWindow(actionEvent);
    }
    public void delPartButtAction(ActionEvent actionEvent) {
    }

    public void modPartButtAction(ActionEvent actionEvent) {
    }


}
