/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ui;

import edu.vanier.controllers.PCController;

/**
 *
 * @author 2125602
 */
public class MainApp {
    
    public static void main(String[] args) {
        // Test things here for now, should be testing in Driver.java --> TODO
        PCController controller = new PCController("/data/zipcodes.csv");
        controller.parse();
        controller.distanceTo("V4S", "E1G");
    }
    
}
