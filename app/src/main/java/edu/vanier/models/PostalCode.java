/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.models;

/**
 *
 * @author 2125602
 */
public class PostalCode {
    private int id;
    private String country, postalCode, province;
    private double latitude, longitude;
    
    // Store all data for entry
    // City doesn't matter for bad data, just postal code & coords
    // Store all city data as one var (and display will just be a bit odd ig)

    public PostalCode(int id, String country, String postalCode, String province, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.postalCode = postalCode;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PostalCode{" + "id=" + id + ", country=" + country + ", postalCode=" + postalCode + ", province=" + province + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}
