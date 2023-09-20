/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q2;

/**
 *
 * @author HP
 */
public class item implements configurration
{
    static int nextId =1; 
    int id;
    String title;
   
    item(String t)
    {
        id = nextId++; 
        title = t;
        
    }
   public int getId()
   {
        return id;
    }
   public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public void display()
    {
        System.out.println(" ID: " + id + "\n" + " Title: " + title);
    }
}
