import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.*;
/**
 *
 * @author HP
 */
public class Library
{

    private ArrayList<item> book_list;
    private List<borrower> borrowers;
    private List<item> borroweditems;

    public Library() {
        this.book_list = new ArrayList<>();
        this.borrowers = new ArrayList<>();
        this.borroweditems = new ArrayList<>();
    }


    public void addBook(item i)
    {
        book_list.add(i);
    }
    public void removeBook(item i)
    {
        book_list.remove(i);
    }

    public void display(item i) //
    {
        i.display();
    }
    public void add_in_arr(int i) throws ParseException
    {
        Scanner s = new Scanner(System.in);


        System.out.println("Enter title" );
        String t = s.nextLine();
        if(i==1)
        {
            System.out.println("Enter author" );
            String author = s.nextLine();
            System.out.println("Enter year" );
            int year = s.nextInt();
            System.out.println("Enter Popularity" );
            int popularity = s.nextInt();

            Book b = new Book(t,author,year,popularity);
            book_list.add(b);
        }

        if(i==3)
        {

            System.out.println("Enter Publishing date" );
            String year = s.nextLine();

            System.out.println("Enter Popularity" );
            int popularity = s.nextInt();

            System.out.println("Enter Publisher company" );
            String c = s.nextLine();
            newspaper n = new newspaper(t,year,popularity,c);
            book_list.add(n);


            // book_list.add(n);
        }


    }
    public void delete_in_arr(int i) {
        Iterator<item> iterator = book_list.iterator();

        while (iterator.hasNext()) {
            item item = iterator.next();
            if (item.getId() == i) {
                iterator.remove();
            }

        }
    }
    public void edit_in_arr(int i) throws ParseException
    {
        for (item item : book_list) {
            if (item.getId() == i)
            {
                if(item instanceof Book)
                {
                    Scanner s = new Scanner(System.in);
                    Book b =(Book) item;
                    System.out.println("Enter Details again: ");
                    System.out.println("Enter the title: ");
                    String t = s.nextLine();
                    b.setTitle(t);
                    System.out.println(" author:  ");
                    String a = s.nextLine();
                    b.setAuthor(a);
                    System.out.println(" year: ");
                    int y = s.nextInt();
                    b.setYear(y);
                    System.out.println(" popularity: ");
                    int p = s.nextInt();
                    b.setpopularity(p);

                    b.display();
                }
                if(item instanceof newspaper)
                {
                    Scanner s = new Scanner(System.in);
                    newspaper n =(newspaper) item;
                    System.out.println("Enter Details again: ");
                    System.out.println("Enter the title: ");
                    String t = s.nextLine();
                    n.setTitle(t);

                    System.out.println(" Publish year: ");
                    String y = s.nextLine();
                    n.setPublishYear(y);
                    System.out.println(" popularity: ");
                    int p = s.nextInt();
                    n.setpopularity(p);

                    System.out.println(  " Company: " );
                    s.nextLine();
                    String c = s.nextLine();
                    n.setCompany(c);
                    n.display();
                }

            }

        }

    }
    public void display_by_id(int i) //viewbyid
    {
        for (item item : book_list) {
            if (item.getId() == i)
            {
                item.display();
            }
        }

    }




    public void displayBooks() //viewall
    {
        //System.out.print("heeeeeeellllllllllooooooooo");
        for (item book : book_list) {
            book.display();


        }
    }

    public void borrowItem(borrower b, int id)
    {
//        for(item i : b.getBorrowedItems())
//        {
//            if(i.getId() == id)
//            {
//                System.out.println("Item is already borrowed by " + b.getName());
//                return false;
//            }
//        }

        for (item item : book_list)
        {
            if (item.getId() == id)
            {
                if (item.isBorrowed() == false && !borroweditems.contains(item))
                {
                    // System.out.println("borrow list me agaye");
                    borrowers.add(b);
                    borroweditems.add(item);
                    item.markAsBorrowed();
                    item.increasePopularityCount();
                }
                else
                {
                    System.out.println("Item is already borrowed by " + b.getName());
                }
            }
        }

    }


    public void hotpicks()
    {
        // Assuming you have a List<Item> book_list

        int n = book_list.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {

                if (book_list.get(j).getPopularityCount() < book_list.get(j + 1).getPopularityCount())
                {

                    item temp = book_list.get(j);
                    book_list.set(j, book_list.get(j + 1));
                    book_list.set(j + 1, temp);
                }
            }
        }
        for(item i:book_list)
        {
            i.display();
        }

    }

    public List<item> getBooks() {
        return book_list;
    }

}


