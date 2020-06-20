package Main;

import Model.InventoryManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        InventoryManager.createInventory();
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../Views/MainScreen.fxml"));
        window.setTitle("Main Screen");
        window.setScene(new Scene(root, 1280, 720));
        window.show();

    }
    public static void main(String[] args) {launch(args);}
}