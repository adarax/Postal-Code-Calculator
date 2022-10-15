/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import edu.vanier.models.PostalCode;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private boolean modalityInitialized = false;

    public boolean isModalityInitialized() {
        return modalityInitialized;
    }

    public void setModalityInitialized(boolean modalityInitialized) {
        this.modalityInitialized = modalityInitialized;
    }
    
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
            
            try {
                //-- Collect then cleanse data to avoid errors with the HashMap
                String startPoint = pc_input_field.getText().toUpperCase().trim();
                double radius = Double.parseDouble(radius_input_field.getText());
                if (radius <= 0) {
                    throw new NumberFormatException();
                }
                HashMap<String, PostalCode> nearbyLocations = pcController.nearbyLocations(startPoint, radius);
                error_message.setText("");

                displayTableView(nearbyLocations, radius, startPoint);

            } catch (NumberFormatException ex) {
                error_message.setText("Invalid radius value, please retry.");
            } catch (Exception ex) {
                error_message.setText("Invalid postal code or radius value, please retry."
                    + "\nTip: Enter only the first 3 characters of the postal code");
            }
        });
    }
    
    /**
     * Handles the display of nearby locations using TableView
     * @param searchResults
     * @param radius
     * @param startPoint 
     */
    public void displayTableView(HashMap<String, PostalCode> searchResults, double radius, String startPoint) {
        
        //-- TableView and Labels setup
        TableView<PostalCode> table = new TableView<>();
        
        Label label = new Label(searchResults.size() + " locations found within "
            + radius + " km of " + startPoint + ":");
        label.setFont(new Font(18));
        
        Label placeholder = new Label();
        placeholder.setText("No results found within this radius");
        table.setPlaceholder(placeholder);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<PostalCode, String> country = new TableColumn<>("Country");
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        TableColumn<PostalCode, String> postalCode = new TableColumn<>("Postal Code");
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        
        TableColumn<PostalCode, String> province = new TableColumn<>("Province");
        province.setCellValueFactory(new PropertyValueFactory<>("province"));
        
        TableColumn<PostalCode, String> latitude = new TableColumn<>("Latitude");
        latitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        
        TableColumn<PostalCode, String> longitude = new TableColumn<>("Longitude");
        longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));

        ObservableList<PostalCode> ob = FXCollections.observableArrayList(searchResults.values());
        table.setItems(ob);
        
        table.getColumns().addAll(country, postalCode, province, latitude, longitude);  
        
        //-- Scene setup
        Scene scene = new Scene(new Group());
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        //-- Stage setup
        this.setTitle("Nearby locations");
        this.setWidth(300);
        this.setHeight(500);
        
        //-- Using initModality() after calling this.show() causes an exception
        if (!isModalityInitialized()) {
            this.initModality(Modality.APPLICATION_MODAL);
            setModalityInitialized(true);
        }
        
        this.sizeToScene();
        this.setResizable(false);
        this.setScene(scene);
        this.show();
        
        //-- Part of the requirements was to display the nearby locations as log messages
        System.out.println(searchResults.size() + " locations found within "
            + radius + " km of " + startPoint + ":");
        
        for (Map.Entry<String, PostalCode> entry : searchResults.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
