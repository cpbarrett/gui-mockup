package View_Controller;

import Model.*;
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
    Part selectedPart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/MainScreen.fxml"));
        try {
            mainUI = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outSourcedType.selectedProperty().addListener((v, oldValue, newValue) -> changeType("Company Name", "Company Nm"));
        inHouseType.selectedProperty().addListener((v, oldValue, newValue) -> changeType("Machine ID", "Mach ID"));
    }
    public void loadPart(Part part) {
        this.selectedPart = part;
        partId.setText(selectedPart.getId()+"");
        partName.setText(selectedPart.getName());
        partInv.setText(selectedPart.getStock()+"");
        partPrice.setText(selectedPart.getPrice()+"");
        partMax.setText(selectedPart.getMax()+"");
        partMin.setText(selectedPart.getMin()+"");
        if(selectedPart instanceof InHouse){
            partLastField.setText(((InHouse) selectedPart).getMachineID()+"");
        }
        if(selectedPart instanceof Outsourced){
            changeType("Company Name", "Company Nm");
            partLastField.setText(((Outsourced) selectedPart).getCompanyName());
            outSourcedType.setSelected(true);
        }
    }

    private void changeType(String label, String prompt){
        partLastField.setPromptText(prompt);
        partLastLabel.setText(label);
    }
    private boolean validatePart(){
        try {
            int stock = Integer.parseInt(partInv.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());
            Double price = Double.parseDouble(partPrice.getText());

            if (min >= max){
                AlertBox.display("Error", "Min must be less than Max!");
                return false;
            }
            if (stock > max){
                AlertBox.display("Error", "Inventory Stock cannot be more than Max Parts.");
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
            if (price < 0){
                AlertBox.display("Error", "Price cannot be less than $0.00.");
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
    private Part createInHouse(int id){
        return new InHouse(id,partName.getText(),Double.parseDouble(partPrice.getText()),Integer.parseInt(partInv.getText()),Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),Integer.parseInt(partLastField.getText()));
    }
    private Part createOutsourced(int id){
        return new Outsourced(id,partName.getText(),Double.parseDouble(partPrice.getText()),Integer.parseInt(partInv.getText()),Integer.parseInt(partMin.getText()),Integer.parseInt(partMax.getText()),partLastField.getText());
    }
    private void addNewPart(){
        if (inHouseType.isSelected()) {
            InventoryManager.getInventory().addPart(createInHouse(InventoryManager.generatePartId()));
        }
        if (outSourcedType.isSelected()) {
            InventoryManager.getInventory().addPart(createOutsourced(InventoryManager.generatePartId()));
        }
    }
    @FXML
    private void saveButtAction(ActionEvent actionEvent) throws IOException {
        if (!validatePart()){
            return;
        }
        addNewPart();
        exitWindow(actionEvent);
    }
    @FXML
    private void updateButtAction(ActionEvent actionEvent) throws IOException {
        if (!validatePart()){
            return;
        }
        try {
            if (inHouseType.isSelected()) {
                int id = Integer.parseInt(partId.getText());
                InventoryManager.getInventory().updatePart(id, createInHouse(id));
            }
            if (outSourcedType.isSelected()) {
                int id = Integer.parseInt(partId.getText());
                InventoryManager.getInventory().updatePart(id, createOutsourced(id));
            }
        } catch (IndexOutOfBoundsException e){
            InventoryManager.getInventory().deletePart(selectedPart);
            addNewPart();
        }
        exitWindow(actionEvent);
    }
    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {
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
}
