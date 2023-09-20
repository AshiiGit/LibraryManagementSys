/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q2;
import java.util.*;
/**
 *
 * @author HP
 */
public class Book extends item
{
    private int popularity;
    private int price;
    private String author;
    private int year;
    Book(String t,String a,int y,int p,int price)
    {
        super(t);
        this.author = a;
        this.year = y;
        this.popularity = p;
        this.price = price;
    }
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
            
    
   
    @Override
   public void display()
    {
         super.display();
    System.out.println(" by " + author + " year: " + year + " popularity: " + popularity + " Price: " + price);
    }
}
