/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author adam8
 */
public class MainWindowController extends Stage {
    
    @FXML
    Button compute_distance;
    @FXML
    Button find_locations;
    
    
    @FXML
    public void initialize() {
        //-- Call computeDistanceDialog() upon clicking "compute distance"
        compute_distance.setOnAction((e) -> {
            try {
                computeDistanceDialog();
            } catch (IOException ex) {
                System.out.println("Error opening dialog box.");
            }
        });

        //-- Call findNearbyDialog() upon clicking "find locations"
        find_locations.setOnAction((e) -> {
            try {
                findNearbyDialog();
            } catch (IOException ex) {
                System.out.println("Error opening dialog box.");
            }
        });
    }
    
    //-- Dialog for user to compute distance
    public void computeDistanceDialog() throws IOException {
        Stage computeDistanceStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/compute_distance_window.fxml"));
        DistanceWindowController controller = new DistanceWindowController();
        loader.setController(controller);
        Pane root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        computeDistanceStage.initModality(Modality.APPLICATION_MODAL);
        computeDistanceStage.setScene(scene);
        computeDistanceStage.setTitle("Compute distance");
        computeDistanceStage.sizeToScene();
        computeDistanceStage.showAndWait();
    }
    
    //-- Dialog for user to find nearby locations
    public void findNearbyDialog() throws IOException {
        Stage findNearbyStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/list_nearby_window.fxml"));
        NearbyWindowController controller = new NearbyWindowController();
        loader.setController(controller);
        Pane root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        findNearbyStage.initModality(Modality.APPLICATION_MODAL);
        findNearbyStage.setScene(scene);
        findNearbyStage.setTitle("Find nearby locations");
        findNearbyStage.sizeToScene();
        findNearbyStage.showAndWait();
    }
}
