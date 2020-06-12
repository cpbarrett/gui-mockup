package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    Stage window2;
    Button searchParts;
    Button addParts;
    Button modifyParts;
    Button deleteParts;
    Button searchProducts;
    Button addProducts;
    Button modifyProducts;
    Button deleteProducts;

    @Override
    public void start(Stage primaryStage) {
//        openPartWindow("mod");
        window = primaryStage;
        //Grid Layout
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25,25,25,25));
        Scene mainScreen = new Scene(layout,600,600);

        //Main Screen
        Text screenTitle = new Text("Inventory Management System");
        screenTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        layout.add(screenTitle, 0,0,4,1);

        //Parts List
        Text parts = new Text("Parts");
        parts.setFont(Font.font("Tahoma",FontWeight.BOLD, 14));
        layout.add(parts,0,1);

        //Search Button and Search Box
        searchParts = new Button();
        searchParts.setText("Search");
        //searchParts.setOnAction(this::handle);
        layout.add(searchParts, 3,1);
        TextField searchBox = new TextField();
        layout.add(searchBox,4,1);


        //Add/Modify/Delete Buttons
        addParts = new Button();
        addParts.setText("Add");
        addParts.setOnAction(event -> openPartWindow("add"));
        layout.add(addParts, 2,2);

        modifyParts = new Button();
        modifyParts.setText("Modify");
        modifyParts.setOnAction(event -> openPartWindow("mod"));
        layout.add(modifyParts, 3,2);

        deleteParts = new Button();
        deleteParts.setText("Delete");
        layout.add(deleteParts, 4,2);
        //deleteParts.setOnAction(this::handle);


        window.setScene(mainScreen);
        window.show();
    }
    public static void main(String args[]){
        launch(args);
    }

    public void openMainWindow(){

    }

    public void openPartWindow(String choice){
        window2 = new Stage();
        //Grid Layout
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25,25,25,25));

        Text screenTitle = new Text("Add Part");
        screenTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        layout.add(screenTitle, 0,0,3,1);

        if(choice.equalsIgnoreCase("mod")){
            screenTitle.setText("Modify Part");
        }

        //In-House or Outsourced
        ToggleGroup category = new ToggleGroup();
        RadioButton inHouse = new RadioButton("In-House");
        RadioButton outSourced = new RadioButton("Outsourced");
        inHouse.setToggleGroup(category);
        outSourced.setToggleGroup(category);
        layout.add(inHouse, 2, 0);
        layout.add(outSourced, 3, 0);

        //Add Part Fields
        // ID
        Label partID = new Label("ID");
        layout.add(partID, 1, 1);
        TextField partIDTextField = new TextField();
        layout.add(partIDTextField, 2, 1);
        partIDTextField.setPromptText("Auto Gen-Disabled");
        partIDTextField.setDisable(true);

        // Name
        Label partName = new Label("Name");
        layout.add(partName, 1, 2);
        TextField partNameTextField = new TextField();
        layout.add(partNameTextField, 2, 2);
        partNameTextField.setPromptText("Part Name");

        // Inv
        Label partInv = new Label("Inv");
        layout.add(partInv, 1, 3);
        TextField partInvTextField = new TextField();
        layout.add(partInvTextField, 2, 3);
        partInvTextField.setPromptText("Inv");

        // Price/Cost
        Label partCost = new Label("Price/Cost");
        layout.add(partCost, 1, 4);
        TextField partCostTextField = new TextField();
        layout.add(partCostTextField, 2, 4);
        partCostTextField.setPromptText("Price/Cost");

        // Max
        Label partMax = new Label("Max");
        layout.add(partMax, 1, 5);
        TextField partMaxTextField = new TextField();
        layout.add(partMaxTextField, 2, 5);
        partMaxTextField.setPromptText("Max");

        // Min
        Label partMin = new Label("Min");
        layout.add(partMin, 3, 5);
        TextField partMinTextField = new TextField();
        layout.add(partMinTextField, 4, 5);
        partMinTextField.setPromptText("Min");

        //Default In-House Part
        Label machID = new Label("Machine ID");
        layout.add(machID, 1, 6);
        TextField machIDTextField = new TextField();
        layout.add(machIDTextField, 2, 6);
        machIDTextField.setPromptText("Mach ID");

        inHouse.setOnAction(event -> {
           machID.setText("Machine ID");
           machIDTextField.setPromptText("MachID");
        });
        // Outsourced Company Part
        outSourced.setOnAction(event -> {
            machID.setText("Company Name");
            machIDTextField.setPromptText("Comp Nm");
        });


        //add part window save/cancel buttons
        Button saveAddedPart = new Button("Save");
        Button cancelAddedPart = new Button("Cancel");
        layout.add(saveAddedPart, 3, 7);
        layout.add(cancelAddedPart, 4, 7);
        saveAddedPart.setOnAction(event -> window2.close());
        cancelAddedPart.setOnAction(event -> window2.close());

        Scene scene = new Scene(layout,600,600);
        window2.setScene(scene);
        window2.show();

    }

    public void openProductWindow(String choice){
        window2 = new Stage();
        //Grid Layout
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25,25,25,25));

        Text screenTitle = new Text("Add Product");
        screenTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        layout.add(screenTitle, 0,0,3,1);

        if(choice.equalsIgnoreCase("mod")){
            screenTitle.setText("Modify Product");
        }

        //In-House or Outsourced
        ToggleGroup category = new ToggleGroup();
        RadioButton inHouse = new RadioButton("In-House");
        RadioButton outSourced = new RadioButton("Outsourced");
        inHouse.setToggleGroup(category);
        outSourced.setToggleGroup(category);
        layout.add(inHouse, 2, 0);
        layout.add(outSourced, 3, 0);

        //Add Part Fields
        // ID
        Label partID = new Label("ID");
        layout.add(partID, 1, 1);
        TextField partIDTextField = new TextField();
        layout.add(partIDTextField, 2, 1);
        partIDTextField.setPromptText("Auto Gen-Disabled");
        partIDTextField.setDisable(true);

        // Name
        Label partName = new Label("Name");
        layout.add(partName, 1, 2);
        TextField partNameTextField = new TextField();
        layout.add(partNameTextField, 2, 2);
        partNameTextField.setPromptText("Part Name");

        // Inv
        Label partInv = new Label("Inv");
        layout.add(partInv, 1, 3);
        TextField partInvTextField = new TextField();
        layout.add(partInvTextField, 2, 3);
        partInvTextField.setPromptText("Inv");

        // Price/Cost
        Label partCost = new Label("Price/Cost");
        layout.add(partCost, 1, 4);
        TextField partCostTextField = new TextField();
        layout.add(partCostTextField, 2, 4);
        partCostTextField.setPromptText("Price/Cost");

        // Max
        Label partMax = new Label("Max");
        layout.add(partMax, 1, 5);
        TextField partMaxTextField = new TextField();
        layout.add(partMaxTextField, 2, 5);
        partMaxTextField.setPromptText("Max");

        // Min
        Label partMin = new Label("Min");
        layout.add(partMin, 3, 5);
        TextField partMinTextField = new TextField();
        layout.add(partMinTextField, 4, 5);
        partMinTextField.setPromptText("Min");

        //Default In-House Part
        Label machID = new Label("Machine ID");
        layout.add(machID, 1, 6);
        TextField machIDTextField = new TextField();
        layout.add(machIDTextField, 2, 6);
        machIDTextField.setPromptText("Mach ID");

        inHouse.setOnAction(event -> {
            machID.setText("Machine ID");
            machIDTextField.setPromptText("MachID");
        });
        // Outsourced Company Part
        outSourced.setOnAction(event -> {
            machID.setText("Company Name");
            machIDTextField.setPromptText("Comp Nm");
        });


        //add part window save/cancel buttons
        Button saveAddedPart = new Button("Save");
        Button cancelAddedPart = new Button("Cancel");
        layout.add(saveAddedPart, 3, 7);
        layout.add(cancelAddedPart, 4, 7);
        saveAddedPart.setOnAction(event -> window2.close());
        cancelAddedPart.setOnAction(event -> window2.close());

        Scene scene = new Scene(layout,600,600);
        window2.setScene(scene);
        window2.show();
    }
}

//        searchProducts = new Button();
//        searchProducts.setText("Search");
//        searchProducts.setOnAction(this::handle);
//
//        addProducts = new Button();
//        addProducts.setText("Search");
//        addProducts.setOnAction(this::handle);
//
//        modifyProducts = new Button();
//        modifyProducts.setText("Search");
//        modifyProducts.setOnAction(this::handle);
//
//        deleteProducts = new Button();
//        deleteProducts.setText("Search");
//        deleteProducts.setOnAction(this::handle);