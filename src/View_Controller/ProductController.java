package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductController {
    @FXML VBox addProductScreen;
    @FXML VBox modProductScreen;
    public void cancelProductButtAction(ActionEvent actionEvent){
        exitWindow(addProductScreen);
    }
    public void cancelModProductButtAction(ActionEvent actionEvent){
        exitWindow(modProductScreen);
    }
    private void exitWindow(VBox screen){
        Stage window = (Stage) screen.getScene().getWindow();
        window.close();
    }
}
