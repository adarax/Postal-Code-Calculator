/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import edu.vanier.models.PostalCode;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author adam8
 */
public class NearbyWindowController {
    
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
                String startPoint = pc_input_field.getText().toUpperCase();
                double radius = Double.parseDouble(radius_input_field.getText());
                HashMap<String, PostalCode> nearbyLocations = pcController.nearbyLocations(startPoint, radius);
                error_message.setText("");
                
                // TODO: implement TableView for search results
                // TODO: don't include itself in the search
                System.out.println(nearbyLocations + " in a radius of " + radius + " KM");
                
                
           }
           catch (NumberFormatException ex) {
               error_message.setText("Invalid radius value, please retry.");
           }
           catch (Exception ex) {
                error_message.setText("Invalid postal code, please retry."
                    + "\nTip: Enter only the first 3 characters of the postal code");
           }
        });
    }
}
