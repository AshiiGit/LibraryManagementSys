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


                    Book book = new Book(title,author,year,popularity);
                    library.addBook(book);
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