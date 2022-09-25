/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author adam8
 */
public class MainWindowController {
    
    @FXML
    Button compute_distance;
    @FXML
    Button find_locations;
    
    
    @FXML
    public void initialize() {
        compute_distance.setOnAction((e) -> {
            //load compute distance window
            System.out.println("Clicked compute distance");
        });
        
        find_locations.setOnAction((e) -> {
            // load find locations window
        });
    }
    
    /*
    public void saveFile() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("FXML test");
        alertDialog.setHeaderText("Would you like to save?");
        alertDialog.setContentText("Choose an option:");
        alertDialog.showAndWait();
        if (alertDialog.getResult() == ButtonType.OK) {
            System.out.println("Save successful!");
        } else {
            System.out.println("Save cancelled.");
        }
    }
    */
}
