package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartController {
    @FXML VBox addPartScreen;
    @FXML VBox modPartScreen;
    public void cancelPartButtAction(){
        Stage window = (Stage) addPartScreen.getScene().getWindow();
        window.close();
    }
    public void cancelModPartButtAction(){
        Stage window = (Stage) modPartScreen.getScene().getWindow();
        window.close();
    }
    public void saveButtAction(ActionEvent actionEvent) {
    }
}
