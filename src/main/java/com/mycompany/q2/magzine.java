/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q2;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author HP
 */
public class magzine extends item
{
    private int popularity;
    private int price;
    private String c;
    private List<String> author;
    
    magzine(String t,List<String> author,String c,int p,int price)
    {
        super(t);
        
        this.author = author;
       
        this.popularity = p;
        this.price = price;
        this.c =c;
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

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }
    
   
    @Override
    public void display()
    {
         super.display();
    System.out.println( " written by " + author +" Publised by: " + c + " popularity: " + popularity + " Price: " + price);
    }
    
}
