import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
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