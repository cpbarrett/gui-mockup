package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductController {
    @FXML VBox addProductScreen;
    @FXML VBox modProductScreen;
    public void cancelProductButtAction(){
        Stage window = (Stage) addProductScreen.getScene().getWindow();
        window.close();
    }
    public void cancelModProductButtAction(){
        Stage window = (Stage) modProductScreen.getScene().getWindow();
        window.close();
    }
}
