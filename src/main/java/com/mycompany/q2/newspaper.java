/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author HP
 */
public class newspaper extends item {
     private int popularity;
    private String company;
    private Date publish_year; // Change the data type to Date
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    newspaper(String t, String d, int p, String c) throws ParseException {
        super(t);
        this.popularity = p;
        this.publish_year = sdf.parse(d); // Parse the date within the constructor
        this.company = c;
    }
     public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getPublishYear() {
        return publish_year;
    }

    public void setPublishYear(String publishYear) throws ParseException {
        this.publish_year = sdf.parse(publishYear);
    }

    @Override
    public void display() {
        super.display();
        System.out.println( """
                            
                            by """ + " "+ company + " Published year is: " + sdf.format(publish_year) + " popularity: " + popularity);
    }
    
}
