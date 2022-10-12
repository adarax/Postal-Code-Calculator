/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ui;

import edu.vanier.controllers.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author 2125602
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_window.fxml"));
        MainWindowController controller = new MainWindowController();
        loader.setController(controller);
        Pane root = loader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Postal Code Calculator");
        stage.sizeToScene();
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
