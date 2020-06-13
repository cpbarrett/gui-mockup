package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage window;
    Stage window2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Inventory sampleInventory = new Inventory();

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Main Screen.fxml"));
        window.setTitle("Main Screen");
        window.setScene(new Scene(root, 1280, 720));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void openPartWindow() throws IOException {


    }

    public void openProductWindow() throws IOException {
        window2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        window2.setTitle("AddProduct");
        window2.setScene(new Scene(root, 720, 720));
        window2.show();
    }
}