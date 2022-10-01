/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.tests;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import edu.vanier.controllers.PCController;
import edu.vanier.models.PostalCode;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2125602
 */
public class Driver {
    
    private PCController pcController;
    private HashMap<String, PostalCode> postalCodes;
    
    public void main() {
        testParse("/data/zipcodes.csv");
//        testDistanceTo("H4R", "H3X");
        pcController = new PCController("/data/zipcodes.csv");
        pcController.parse(); // To fill the postalCodes HashMap
                              // so that testNearbyLocations can be used
        testNearbyLocations("H3X", 100);
    }
    
    public void testParse(String filePath) {
        try {
            String csvPath = getClass().getResource(filePath).getPath();
            
            //-- Fix for paths where the name of one of the directories
            //-- contains a "space" character
            if (csvPath.contains("%20")) {
                csvPath = csvPath.replace("%20", " ");
            }

            CSVReader reader = new CSVReaderBuilder(new FileReader(csvPath)).build();
            String[] nextLine;
            postalCodes = new HashMap<>();
            
            //-- nextLine is a line of the CSV file in the form of a String[]
            while ((nextLine = reader.readNext()) != null) {
                
                int id = Integer.parseInt(nextLine[0]);
                String country = nextLine[1];
                String postalCode = nextLine[2];
                String province = nextLine[nextLine.length - 3];
                double latitude = Double.parseDouble(nextLine[nextLine.length - 2]);
                double longitude = Double.parseDouble(nextLine[nextLine.length -1]);
                
                PostalCode postalCodeObject = new PostalCode(id, country, postalCode, province, latitude, longitude);
                postalCodes.put(postalCode, postalCodeObject);
                
            }
            
        }
        catch (CsvValidationException | IOException e) {
            System.out.println("""
                               Something went wrong with the CSV file.
                               Suggestion: Check the filePath.
                               """);
        }
         
    }
    
    //-- @return distance between points in kilometers, double
    public void testDistanceTo(String from, String to) {
        
        int radiusOfEarth = 6371;
        
        double lat1 = Math.toRadians(postalCodes.get(from).getLatitude()),
               lat2 = Math.toRadians(postalCodes.get(to).getLatitude()),
               long1 = Math.toRadians(postalCodes.get(from).getLongitude()),
               long2 = Math.toRadians(postalCodes.get(to).getLongitude());
        
        double dist = 2 * radiusOfEarth * 
                Math.asin(Math.sqrt(Math.pow(Math.sin((lat2 - lat1) / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin((long2 - long1) / 2), 2)));
        
       /**
        * Rounded dist to 2 decimal places   
        * To round: Math.round(dist * 10^n) / 10^n where "n" is the
          number of decimal places.
        * Type cast to double to keep the decimals.
        */
        System.out.println((double) Math.round(dist * 100) / 100 + "km");
    }
    
    
    //-- @return nearby locations, HashMap<String, PostalCode>
    public void testNearbyLocations(String from, double radius) {
        
        //-- Nearby PostalCode objects (within 100km)
        HashMap<String, PostalCode> nearbyLocations = new HashMap<>();
        
        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
            if (pcController.distanceTo(from, entry.getValue().getPostalCode()) <= radius) {
                nearbyLocations.put(entry.getKey(), entry.getValue());
            }
        }
        
        System.out.println(nearbyLocations);
    }
    
}