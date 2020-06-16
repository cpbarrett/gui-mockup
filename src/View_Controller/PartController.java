package View_Controller;

import Model.InHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PartController extends Controller implements Initializable {
    @FXML TextField addPartName;
    @FXML TextField addPartPrice;
    @FXML TextField addPartInv;
    @FXML TextField addPartMin;
    @FXML TextField addPartMax;
    @FXML TextField addPartMachID;

    public void initialize(URL location, ResourceBundle resources){

    }

    public void cancelPartButtAction(ActionEvent actionEvent){
        exitWindow(addPartScreen);
    }
    public void cancelModPartButtAction(ActionEvent actionEvent){
        exitWindow(modPartScreen);
    }
    public void saveButtAction(ActionEvent actionEvent) {
        System.out.println(addPartName.getText());
        System.out.println(sampleInventory.lookupProduct(0).getName());
//        sampleInventory.addPart(new InHouse(sampleInventory.getAllParts().size(),
//                addPartName.getText(),
//                Double.parseDouble(addPartPrice.toString()),
//                Integer.parseInt(addPartInv.getText()),
//                Integer.parseInt(addPartMin.getText()),
//                Integer.parseInt(addPartMax.getText()),
//                Integer.parseInt(addPartMachID.getText())));
//        exitWindow(addPartScreen);
    }
    private void exitWindow(VBox screen){
        Stage window = (Stage) screen.getScene().getWindow();
        window.close();
    }
}
