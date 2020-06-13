package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML private VBox mainScreen;

    public void exitButtAction(){
        Stage window = (Stage) mainScreen.getScene().getWindow();
        window.close();
        System.exit(0);
    }
    public void addPartButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        window.setTitle("AddPart");
        window.setScene(new Scene(root, 720, 720));
        window.show();
    }
    public void modPartButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModPart.fxml"));
        window.setTitle("ModPart");
        window.setScene(new Scene(root, 720, 720));
        window.show();
    }
    public void delPartButtAction() throws IOException {
    }
    public void addProductButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        window.setTitle("AddProduct");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
    public void modProductButtAction() throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModProduct.fxml"));
        window.setTitle("ModProduct");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }
    public void delProductButtAction() throws IOException {
    }
}
