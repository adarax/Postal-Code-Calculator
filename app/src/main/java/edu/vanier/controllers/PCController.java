/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.controllers;

import java.util.HashMap;
import edu.vanier.models.PostalCode;
import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

/**
 *
 * @author 2125602
 */
public class PCController {
    private HashMap<String, PostalCode> postalCodes;
    private final String csvFilePath;
    
    
    public PCController(String filePath) { // Constructor
        this.csvFilePath = filePath;
    }
    
    
    // @return HashMap<String, PostalCode object>
    // The String key in the HashMap is the postal code its corresponding value
    public HashMap<String, PostalCode> parse() {
        
        try {
            String csvPath = getClass().getResource(csvFilePath).getPath();
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
        
      System.out.println(postalCodes.get("V4S"));
      System.out.println(postalCodes.get("E1G"));  

      return postalCodes;
    }
    
    
    // TODO: FIX HAVERSINE
    
    // Takes two postal codes, finds corresponding coords
    // Uses Haversine formula
    // @return distance (in KM)
    public double distanceTo(String from, String to) {
        
        int radiusOfEarth = 6371;
        
        double latitudeStart = Math.toRadians(postalCodes.get(from).getLatitude());
        double latitudeEnd = Math.toRadians(postalCodes.get(to).getLatitude());
        double longitudeStart = Math.toRadians(postalCodes.get(from).getLongitude());
        double longitudeEnd = Math.toRadians(postalCodes.get(to).getLongitude());
        
        double distanceBetweenPoints = 2 * radiusOfEarth
                * Math.asin(Math.sqrt(Math.pow(
                  Math.sin((latitudeEnd - latitudeStart) / 2), 2))
                + Math.cos(longitudeStart)
                * Math.cos(longitudeEnd)
                * Math.pow(Math.sin((longitudeEnd - longitudeStart) / 2), 2));
        System.out.println(distanceBetweenPoints);
        return distanceBetweenPoints; // Round?
    }
    
    public HashMap<String, PostalCode> nearbyLocations(String from) {
        
        return null;
    }
}