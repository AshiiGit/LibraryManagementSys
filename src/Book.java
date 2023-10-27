import java.util.*;
/**
 *
 * @author HP
 */
public class Book extends item
{

    private int price;
    private String author;
    private int year;
    Book(String t,String a,int y,int p,int price)
    {
        super(t,p,0);
        this.author = a;
        this.year = y;

        this.price = price;
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
        System.out.println(" by " + author + " year: " + year +" Price: " + price);
    }
    @Override
    public int calculatecost()
    {
        double perc = 0.02 * this.price;
        int c = (int) (this.price * perc + 200);
        return c;

    }


}