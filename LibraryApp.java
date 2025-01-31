import java.sql.SQLOutput;
import java.util.*;

class Book{
    private String title;
    private String author;
    private boolean isBorrowed;

    Book(String title , String author){
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isBorrowed(){
        return isBorrowed;
    }
    public void borrowBook(){
        if(!isBorrowed){
            isBorrowed = true;
            System.out.println("Successfully borrowed..");
        }else{
            System.out.println("Already borrowed...");
        }
    }
    public void returnBook(){
        if(isBorrowed){
            isBorrowed = false;
            System.out.println("You returned the book..");
        }else{
            System.out.println("Book are not borrowed...");
        }
    }
    @Override
    public String toString(){
        return "Title:"+title+ " , Author:"+author+ " , Status:"+(isBorrowed ? "Borrowed" : "Free");
    }
}
class Library{
    private ArrayList<Book> books;
    Library(){
        books = new ArrayList<>();
    }
    public void addBooks(String title,String author){
        books.add(new Book(title,author));
        System.out.println("You have successfully added the book");
    }
    public boolean removeBook(String title){
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed());
    }
    public void searchBooks(String keyword){
        boolean found = false;
        for(Book book : books){
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ){
                System.out.println(book);
                found = true;
            }
        }
        if(!found){
            System.out.println("No matching with your keywords");
        }
    }
    public void viewBooks(){
        if(books.isEmpty()){
            System.out.println("No books available...");
        }else{
            for(Book book : books){
                System.out.println(book);
            }
        }
    }
    public void borrowedBooks(String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                book.borrowBook();
                return;
            }
        }
        System.out.println("Mismatching...Please enter another Book Title");
    }
    public void returnedBooks(String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                book.returnBook();
                return;
            }
        }
        System.out.println("Mismatching...Please enter another Book Title");
    }
}
public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lb = new Library();
        while(true){
            System.out.println("--Library Operations--");
            System.out.println("1. Add Books");
            System.out.println("2. Remove Books");
            System.out.println("3. View books");
            System.out.println("4. Searching for a book");
            System.out.println("5. Borrow a book");
            System.out.println("6. Return a book");
            System.out.println("7. Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch(ch){
                case 1:
                    System.out.print("Enter the Title:");
                    String title = sc.nextLine();
                    System.out.print("Enter the Author name:");
                    String author = sc.nextLine();
                    lb.addBooks(title , author);
                    break;
                case 2:
                    System.out.println("Enter the title of book:");
                    String t = sc.nextLine();
                    boolean res = lb.removeBook(t);
                    if(!res){
                        System.out.println("Book are not matched / It is already borrowed...");
                    }
                    break;
                case 3:
                    lb.viewBooks();
                    break;
                case 4:
                    System.out.println("Enter title/author of the book:");
                    String keyword = sc.nextLine();
                    lb.searchBooks(keyword);
                    break;
                case 5:
                    System.out.println("Enter title:");
                    String title1 = sc.nextLine();
                    lb.borrowedBooks(title1);
                    break;
                case 6:
                    System.out.println("Enter title:");
                    String title2 = sc.nextLine();
                    lb.returnedBooks(title2);
                    break;
                case 7:
                    System.out.println("...Exiting...");
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;

            }
        }

    }
}