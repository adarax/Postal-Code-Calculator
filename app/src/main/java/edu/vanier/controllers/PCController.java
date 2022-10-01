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
import java.util.Map;

/**
 *
 * @author 2125602
 */
public class PCController {
    private HashMap<String, PostalCode> postalCodes;
    private final String csvFilePath;
    
    //-- Constructor
    public PCController(String filePath) {
        this.csvFilePath = filePath;
        parse();
    }
    
   /** 
    * The key in the HashMap is the postal code of its value
    * @return HashMap<String, PostalCode object>
    */
    public final HashMap<String, PostalCode> parse() {
        
        try {
            String csvPath = getClass().getResource(csvFilePath).getPath();
            
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
        
      return postalCodes;
    }
    
    
   /** 
    * Takes two postal codes, finds corresponding coordinates, converts to rads
    * Uses Haversine formula
    * @param from
    * @param to
    * @return dist : distance between two points in km
    */
    public final double distanceTo(String from, String to) {
        
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
        return (double) Math.round(dist * 100) / 100;
    }
    
    /**
     * @param from : first 3 characters of postal code
     * @param radius : radius (KM) in which to search
     * @return HashMap<String, PostalCode> : HashMap of nearby locations
     */
    public final HashMap<String, PostalCode> nearbyLocations(String from, double radius) {
        //-- Nearby PostalCode objects (within 100km)
        HashMap<String, PostalCode> nearbyLocations = new HashMap<>();
        
        for (Map.Entry<String, PostalCode> entry : postalCodes.entrySet()) {
            if (distanceTo(from, entry.getValue().getPostalCode()) <= radius) {
                if (entry.getValue() != postalCodes.get(from)) {
                    nearbyLocations.put(entry.getKey(), entry.getValue());
                }
            }
        }
        
        return nearbyLocations;
    }
    
}