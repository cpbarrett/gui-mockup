package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler {
    Button button;

    @Override
    public void start(Stage primaryStage) {
        button = new Button();
        button.setText("Click Me Baby!");
        button.setOnAction(this::handle);

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == button) {
            System.out.println("One More Time!");
        }

    }
}