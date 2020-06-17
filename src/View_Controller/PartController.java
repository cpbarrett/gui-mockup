package View_Controller;

import Model.InHouse;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    @FXML private TextField partId;
    @FXML private TextField partName;
    @FXML private TextField partPrice;
    @FXML private TextField partInv;
    @FXML private TextField partMin;
    @FXML private TextField partMax;
    @FXML private TextField partMachID;
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
    public void loadPart(Part modPart) {
        System.out.println(modPart.getClass().toString());
//        partId.setText(modPart.getId() + "");
//        partName.setText(modPart.getName());
//        partPrice.setText(modPart.getPrice() + "");
//        partInv.setText(modPart.getStock() + "");
//        partMax.setText(modPart.getMax() + "");
//        partMin.setText(modPart.getMin() + "");
//        partMachID.setText(modPart.getMachineId());
    }
    public void saveButtAction(ActionEvent actionEvent) throws IOException {
        mainController.sampleInventory.addPart(new InHouse(
                mainController.sampleInventory.getAllParts().size(),
                partName.getText(),
                Double.parseDouble(partPrice.getText()),
                Integer.parseInt(partInv.getText()),
                Integer.parseInt(partMin.getText()),
                Integer.parseInt(partMax.getText()),
                Integer.parseInt(partMachID.getText())
        ));
        exitWindow(actionEvent);
    }
    public void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
