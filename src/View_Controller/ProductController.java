package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public TableView asociatedPartsTable;
    public TableColumn asociatedPartID;
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
    public void exitWindow(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(mainUI);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void searchPartsButton(ActionEvent actionEvent) {
    }

    public void delPartButtAction(ActionEvent actionEvent) {
    }

    public void modPartButtAction(ActionEvent actionEvent) {
    }

    public void addPartButtAction(ActionEvent actionEvent) {
    }
}
