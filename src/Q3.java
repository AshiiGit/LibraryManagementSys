import java.text.ParseException;
import java.util.*;
/**
 *
 * @author HP
 */

public class Q3 {

    public static void main(String[] args) throws ParseException
    {
        Scanner s = new Scanner(System.in);

        Library l = new Library();
        bookloading b = new bookloading();
        b.loadbooks("D:\\LibraryManagmentSystem\\src\\books.txt", l);
        boolean end = false;
        while(!end)
        {


            System.out.println("""
                           Library Management System Menu: 
                           1. ADD Book
                           2. Edit Book
                           3. Delete Book
                           4. View all Books  
                           5. View Book by ID
                           6. Borrow an item
                           7. View borrowed item list
                           8. Hot Picks
                           9. EXIT"""
            );
            System.out.println("Enter your option: ");
            int input = s.nextInt();
            switch(input)
            {
                case 1:
                {
                    System.out.println
                            (" 1.Book "
                                    + " 2. Magzine "
                                    + " 3. Newspaper ");
                    int i = s.nextInt();
                    l.add_in_arr(i);
                    l.displayBooks();
                    break;

                }
                case 2:
                {
                    System.out.println("Enter id of the book that you want to edit: ");
                    int i = s.nextInt();
                    l.edit_in_arr(i);
                    break;
                }
                case 3:
                {
                    System.out.println("Enter id of the book that you want to delete: ");
                    int i = s.nextInt();
                    l.delete_in_arr(i);
                    l.displayBooks();
                    break;
                }
                case 4:
                {
                    l.displayBooks();
                    break;
                }
                case 5:
                {
                    System.out.println("Enter id of the book that you want to view: ");
                    int i = s.nextInt();
                    l.display_by_id(i);
                    break;
                }
                case 6:
                {
                    System.out.println("Enter your name: ");
                    s.nextLine();
                    String name = s.nextLine();
                    System.out.println("Enter ID of the item that you want to borrow: ");
                    int id = s.nextInt();
                    s.nextLine();
                    borrower br = new borrower(name);
                    l.borrowItem(br, id);
                    break;
                }
                case 7:
                {
                    l.db();
                    break;
                }
                case 8:
                {
                    l.hotpicks();
                    break;
                }
                case 9:
                {
                    end = true;
                    //System.out.println("System has terminted");
                    System.exit(0);
                }
                default:
                {
                    System.out.println("The choice you entered don't match the menu options");
                }
            }

        }
    }
}