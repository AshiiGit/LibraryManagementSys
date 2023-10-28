import java.util.*;
/**
 *
 * @author HP
 */
public class Book extends item
{


    private String author;
    private int year;
    Book(String t,String a,int y,int p)
    {
        super(t,p,0);
        this.author = a;
        this.year = y;


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
        System.out.println(" by " + author + " year: " + year +" Price: " );
    }



}