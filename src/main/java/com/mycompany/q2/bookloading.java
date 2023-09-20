/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.q2;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
/**
 *
 * @author HP
 */
public class bookloading {
    public void loadbooks(String filePath, Library library) throws ParseException {
        try 
        {
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while(s.hasNextLine())
            {
                String detail = s.nextLine();
                String[] specific = detail.split(",");
                if ("1".equals(specific[0]))
                {
                    String title = specific[1].trim();
                    String author = specific[2].trim();
                    String y = specific[3].trim();
                    int year = Integer.parseInt(y);
                    String p = specific[4].trim();
                    int popularity = Integer.parseInt(p);
                    String pr = specific[5].trim();
                    int price = Integer.parseInt(pr);
                    Book book = new Book(title,author,year,popularity,price);
                    library.addBook(book);   
                }
                if("2".equals(specific[0]))
                {
                    
                    List<String> authors = new ArrayList<String>();
                    int ss= specific.length -3;
                     int count =1;
                    
                  
                    for(int i= 2;i<ss;i++)
                    {
                        authors.add(specific[i]);
                        count++;
                    }
                    System.out.println(count);
                   String title = specific[1].trim();
                    
                   String c = specific[count+1].trim();
                   String p = specific[count+2].trim();
                   int popularity = Integer.parseInt(p);
                   String pr = specific[count+3].trim();
                    int price = Integer.parseInt(pr);
                   magzine m = new magzine(title,authors,c,popularity,price);//,authors,c,popularity,price);
                    library.addBook(m);  
                }
                
                
                if ("3".equals(specific[0]))
                {
                    String title = specific[1].trim();
                    String author = specific[2].trim();
                    String p = specific[3].trim();
                    int popularity = Integer.parseInt(p);
                    String y = specific[4].trim();
                    
                    
                    newspaper n = new newspaper(title,y,popularity,author);
                    library.addBook(n);   
                }
             
            }
            s.close();
            
        } 
        catch (FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
}
    

