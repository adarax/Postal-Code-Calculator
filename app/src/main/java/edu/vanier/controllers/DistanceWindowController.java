/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author adam8
 */
public class DistanceWindowController extends Stage {
    
    @FXML
    Button compute_button;
    
    @FXML
    TextField start_field, end_field;
    
    @FXML
    Text error_message;
    
    @FXML
    public void initialize() {
        compute_button.setOnAction((e) -> {
            PCController pcController = new PCController("/data/zipcodes.csv");
            
            try {
                //-- Collect then cleanse data to avoid errors with HashMap
                String startPoint = start_field.getText().toUpperCase().trim();
                String endPoint = end_field.getText().toUpperCase().trim();
                
                //-- Compute distance
                double distance = pcController.distanceTo(startPoint, endPoint);
                
                //-- Erase any previous error messages
                error_message.setText("");
                
                //-- Format Alert dialog box
                Alert resultDialog = new Alert(Alert.AlertType.INFORMATION);
                resultDialog.setTitle("Result");
                resultDialog.setHeaderText("");
                resultDialog.setContentText("Distance is " + distance + " KM");
                resultDialog.showAndWait();
            }
            catch (Exception ex) {
                //-- Display error message in the dialog box
                error_message.setText("One or both of these postal codes is invalid, please retry."
                    + "\nTip: Enter only the first 3 characters of the postal code");
            }
            
        });
    }
}
