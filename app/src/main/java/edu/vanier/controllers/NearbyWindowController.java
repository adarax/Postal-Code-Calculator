/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import edu.vanier.models.PostalCode;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author adam8
 */
public class NearbyWindowController extends Stage {
    
    @FXML
    Button find_button;
    
    @FXML
    TextField pc_input_field, radius_input_field;
    
    @FXML
    Text error_message;
    
    @FXML
    public void initialize() {
        find_button.setOnAction((e) -> {
            PCController pcController = new PCController("/data/zipcodes.csv");
            
            // TODO: issue with using the method multiple times without restarting application
            
            try {
                String startPoint = pc_input_field.getText().toUpperCase().trim();
                double radius = Double.parseDouble(radius_input_field.getText());
                if (radius <= 0) {
                    throw new NumberFormatException();
                }
                HashMap<String, PostalCode> nearbyLocations = pcController.nearbyLocations(startPoint, radius);
                error_message.setText("");
                
                displayTableView(nearbyLocations);
                
                System.out.println(nearbyLocations + " in a radius of " + radius + " KM");
                
           }
           catch (NumberFormatException ex) {
               error_message.setText("Invalid radius value, please retry.");
           }
           catch (Exception ex) {
                error_message.setText("Invalid postal code or radius value, please retry."
                    + "\nTip: Enter only the first 3 characters of the postal code");
           }
        });
    }
    
    public void displayTableView(HashMap<String, PostalCode> searchResults) {
        // TODO: implement TableView for search results
        
        //-- Table implemented
        //-- Now need to add the search results line by line into the table
        
        // Add city column?

        TableView table = new TableView();

        Scene scene = new Scene(new Group());
        this.setTitle("Nearby locations");
        this.setWidth(300);
        this.setHeight(500);

        Label label = new Label("Found locations");
        label.setFont(new Font(20));

        TableColumn<PostalCode, String> country = new TableColumn<>("Country");
        country.setCellValueFactory(new PropertyValueFactory("country"));
        TableColumn postalCode = new TableColumn("Postal Code");
        TableColumn province = new TableColumn("Province");
        TableColumn latitude = new TableColumn("Latitude");
        TableColumn longitude = new TableColumn("Longitude");

        table.getColumns().addAll(country, province, postalCode, latitude, longitude);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        this.sizeToScene();
        this.setResizable(false);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.show();
    }
}
