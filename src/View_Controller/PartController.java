package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController implements Initializable {
    @FXML private RadioButton outSourcedType;
    @FXML private RadioButton inHouseType;
    @FXML private TextField partId;
    @FXML private TextField partName;
    @FXML private TextField partPrice;
    @FXML private TextField partInv;
    @FXML private TextField partMin;
    @FXML private TextField partMax;
    @FXML private TextField partLastField;
    @FXML private Label partLastLabel;
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
        outSourcedType.selectedProperty().addListener((v, oldValue, newValue) -> changeType("Company Name", "Company Nm"));
        inHouseType.selectedProperty().addListener((v, oldValue, newValue) -> changeType("Machine ID", "Mach ID"));
    }
    public void loadPartID(int id){
        loadPart(mainController.sampleInventory.lookupPart(id));
    }
    private void loadPart(Part selectedPart) {
        partId.setText(selectedPart.getId()+"");
        partName.setText(selectedPart.getName());
        partInv.setText(selectedPart.getStock()+"");
        partPrice.setText(selectedPart.getPrice()+"");
        partMax.setText(selectedPart.getMax()+"");
        partMin.setText(selectedPart.getMin()+"");
        if(selectedPart.getClass().toString().matches("class Model.InHouse")){
            partLastField.setText(((InHouse) selectedPart).getMachineID()+"");
        }
        if(selectedPart.getClass().toString().matches("class Model.Outsourced")){
            changeType("Company Name", "Company Nm");
            partLastField.setText(((Outsourced) selectedPart).getCompanyName());
            outSourcedType.setSelected(true);
        }
    }

    private void changeType(String label, String prompt){
        partLastField.setPromptText(prompt);
        partLastLabel.setText(label);
    }
    private Part createInHouse(){
        return new InHouse(mainController.sampleInventory.getAllParts().size()-1,partName.getText(),Double.parseDouble(partPrice.getText()),Integer.parseInt(partInv.getText()),Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),Integer.parseInt(partLastField.getText()));
    }
    private Part createOutsourced(){
        return new Outsourced(mainController.sampleInventory.getAllParts().size()-1,partName.getText(),Double.parseDouble(partPrice.getText()),Integer.parseInt(partInv.getText()),Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),partLastField.getText());
    }
    private void addNewPart(Part part){
        mainController.sampleInventory.addPart(part);
    }
    @FXML
    private void saveButtAction(ActionEvent actionEvent) throws IOException {
        if (inHouseType.isSelected()) {
            addNewPart(createInHouse());
        }
        if (outSourcedType.isSelected()) {
            addNewPart(createOutsourced());
        }
        exitWindow(actionEvent);
    }
    @FXML
    private void updateButtAction(ActionEvent actionEvent) throws IOException {
        int id = Integer.parseInt(partId.getText());
        mainController.sampleInventory.updatePart(id, mainController.sampleInventory.lookupPart(id));
        exitWindow(actionEvent);
    }
    @FXML
    private void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
