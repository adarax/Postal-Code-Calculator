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
                String startPoint = start_field.getText().toUpperCase().trim();
                String endPoint = end_field.getText().toUpperCase().trim();
                double distance = pcController.distanceTo(startPoint, endPoint);
                
                error_message.setText("");
                Alert resultDialog = new Alert(Alert.AlertType.INFORMATION);
                resultDialog.setTitle("Computed distance");
                resultDialog.setHeaderText("");
                resultDialog.setContentText("Distance is " + distance + " KM");
                resultDialog.showAndWait();
            }
            catch (Exception ex) {
                error_message.setText("Invalid postal code, please retry."
                    + "\nTip: Enter only the first 3 characters of the postal code");
            }
            
        });
    }
}
