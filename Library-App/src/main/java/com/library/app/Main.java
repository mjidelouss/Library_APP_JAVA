package com.library.app;
import com.library.app.domain.*;
import com.library.app.domain.enums.Colors;
import com.library.app.infrastructure.DbConnection;
import com.library.app.repository.*;
import com.library.app.ressources.Ressource;
import com.library.app.services.*;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Connection dbConnection = DbConnection.connect();
        if (dbConnection != null) {
            menu();
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Colors.RED.getColor() + "\nFailed to connect to the database.");
        }
    }

    public static int menu() throws InterruptedException {
        Ressource.clearTerminal();
        Connection dbConnection = DbConnection.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Ressource.printUserMenu();
            System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Your Choice : " + Colors.RESET.getColor());
            int choice = scanner.nextInt();
            MemberRepository memberRepository = new MemberRepository(dbConnection);
            MemberService memberService = new MemberService(memberRepository);
            UserRepository userRepository = new UserRepository(dbConnection);
            UserService userService = new UserService(userRepository);
            Console console = System.console();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Email : " + Colors.RESET.getColor());
                    String email = scan.nextLine();
                    char[] passwordArray = console.readPassword(Colors.PURPLE.getColor() + "Enter Password : "+Colors.RESET.getColor());
                    String password = new String(passwordArray);
                    User loggedInUser = userService.login(email, password);
                    if (loggedInUser != null) {
                        int user_id = loggedInUser.getId();
                        if (memberService.checkMember(user_id)) {
                            Ressource.clearTerminal();
                            memberMenu(user_id);
                        } else {
                            Ressource.clearTerminal();
                            librarianMenu(user_id);
                        }
                    } else {
                        System.out.println(Colors.RED.getColor() +"\n\nWrong Credentials, Try Again.");
                        Ressource.waitForEnter();
                        menu();
                    }
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    System.out.print(Colors.PURPLE.getColor() +"Name : " + Colors.RESET.getColor());
                    String name = scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Telephone : " + Colors.RESET.getColor());
                    String telephone = scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Adresse : " + Colors.RESET.getColor());
                    String adresse = scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Email : " + Colors.RESET.getColor());
                    String newEmail = scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Password : " + Colors.RESET.getColor());
                    String newPassword = scan1.nextLine();
                    if (userService.isValidRegisterData(name, telephone, adresse, newEmail, newPassword)) {
                        User loggedUser = userService.register(name, newEmail, newPassword, telephone, adresse);
                        if (loggedUser != null) {
                            int user_id = loggedUser.getId();
                            Ressource.clearTerminal();
                            memberMenu(user_id);
                        }
                    } else {
                        System.out.println(Colors.RED.getColor() +"\n\nFill out all the fields");
                        Ressource.waitForEnter();
                        menu();
                    }
                    break;
                case 3:
                    Ressource.quitApp();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"\n\nInvalid choice. Please try again.");
                    Ressource.waitForEnter();
            }
        }
    }

    public static int memberMenu(int user_id) throws InterruptedException {
        Connection dbConnection = DbConnection.connect();
        BookRepository bookRepository = new BookRepository(dbConnection);
        BookService bookService = new BookService(bookRepository);
        BorrowedBooksRepository borrowedBooksRepository = new BorrowedBooksRepository(dbConnection);
        BorrowedBooksService borrowedBooksService = new BorrowedBooksService(borrowedBooksRepository);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Ressource.printMemberMenu();
            System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Your Choice : " + Colors.RESET.getColor());
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    List<Book> books = bookService.displayBooks();
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═════════════════════════════════════════════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
                    for (Book book : books) {
                        System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %35s "+ Colors.CYAN.getColor() +"║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
                    }
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 2:
                    System.out.print(Colors.PURPLE.getColor() +"Search Term : " + Colors.RESET.getColor());
                    String searchTerm = scanner.nextLine();
                    List<Book> searchedBooks = bookService.searchBook(searchTerm);
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════════════════════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
                    for (Book book : searchedBooks) {
                        System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %35s "+ Colors.CYAN.getColor() +"║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
                    }
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 3:
                    System.out.print(Colors.PURPLE.getColor() +"Enter ISBN : " + Colors.RESET.getColor());
                    String borrowIsbn = scanner.nextLine();
                    BorrowedBook borrowedBook = borrowedBooksService.borrowBook(user_id, borrowIsbn);
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ║           Book ID              ║            Title             ║            ISBN             ║         Borrow_Date         ║            Due_Date                    ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬════════════════════════════════════════╣");
                    System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %38s "+ Colors.CYAN.getColor() +"║%n", borrowedBook.getId(), borrowedBook.getBookTitle(), borrowedBook.getBookIsbn(), borrowedBook.getBorrowDate(), borrowedBook.getDueDate());
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 4:
                    System.out.print(Colors.PURPLE.getColor() +"Enter ISBN : " + Colors.RESET.getColor());
                    String returnIsbn = scanner.nextLine();
                    BorrowedBook returnedBook = borrowedBooksService.returnBook(user_id, returnIsbn);
                    LocalDate todayDate = LocalDate.now();
                    Date returnedDate = Date.valueOf(todayDate);
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Book ID             ║            Title             ║            ISBN             ║        Returned_Date        ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
                    System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║%n", returnedBook.getId(), returnedBook.getBookTitle(), returnedBook.getBookIsbn(), returnedDate);
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 5:
                    user_id = 0;
                    Ressource.clearTerminal();
                    Ressource.logout();
                    Ressource.waitForEnter();
                    menu();
                    break;
                case 6:
                    Ressource.clearTerminal();
                    Ressource.quitApp();
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"Invalid choice. Please try again.");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
            }
        }
    }

    public static void librarianMenu(int user_id) throws InterruptedException {
        Ressource.clearTerminal();
        Connection dbConnection = DbConnection.connect();
        Book bookMaster = new Book();
        BookRepository bookRepository = new BookRepository(dbConnection);
        BookService bookService = new BookService(bookRepository);
        BorrowedBooksRepository borrowedBooksRepository = new BorrowedBooksRepository(dbConnection);
        BorrowedBooksService borrowedBooksService = new BorrowedBooksService(borrowedBooksRepository);
        LostBooksRepository lostBooksRepository = new LostBooksRepository(dbConnection);
        LostBooksService lostBooksService = new LostBooksService(lostBooksRepository);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Ressource.printLibrarianMenu();
            System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Your Choice : " + Colors.RESET.getColor());
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.print(Colors.PURPLE.getColor() +"\n\nTitle : " + Colors.RESET.getColor());
                    String title = scan.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Author : " + Colors.RESET.getColor());
                    String author = scan.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"ISBN : " + Colors.RESET.getColor());
                    String isbn = scan.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Quantity : " + Colors.RESET.getColor());
                    int quantity = scan.nextInt();
                    scan.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Category : " + Colors.RESET.getColor());
                    String category = scan.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"Year : " + Colors.RESET.getColor());
                    int year = scan.nextInt();
                    scan.nextLine();
                    if (bookService.isValidBookData(title, author, isbn, category, year, quantity)) {
                        Book bookManager = new Book(title, author, isbn, quantity, category, year);
                        bookService.addBook(bookManager);
                    } else {
                        System.out.println(Colors.RED.getColor() +"\n\nFill out all the fields");
                    }
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    bookService.displayBooks();
                    System.out.print(Colors.PURPLE.getColor() +"Enter Book ISBN : " + Colors.RESET.getColor());
                    String oldIsbn = scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"New Title : " + Colors.RESET.getColor());
                    bookMaster.setTitle(scan1.nextLine());
                    System.out.print(Colors.PURPLE.getColor() +"New Author : " + Colors.RESET.getColor());
                    bookMaster.setAuthor(scan1.nextLine());
                    System.out.print(Colors.PURPLE.getColor() +"New ISBN : " + Colors.RESET.getColor());
                    bookMaster.setIsbn(scan1.nextLine());
                    System.out.print(Colors.PURPLE.getColor() +"New Quantity : " + Colors.RESET.getColor());
                    bookMaster.setQuantity(scan1.nextInt());
                    scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"New Category : " + Colors.RESET.getColor());
                    bookMaster.setCategory(scan1.nextLine());
                    scan1.nextLine();
                    System.out.print(Colors.PURPLE.getColor() +"New Year : " + Colors.RESET.getColor());
                    bookMaster.setYear(scan1.nextInt());
                    scan1.nextLine();
                    if (bookMaster.getIsbn().isEmpty()) {
                        bookMaster.setIsbn(oldIsbn);
                    }
                    bookService.updateBook(oldIsbn, bookMaster);
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 3:
                    Scanner scan2 = new Scanner(System.in);
                    bookService.displayBooks();
                    System.out.print(Colors.PURPLE.getColor() +"Enter Book ISBN : " + Colors.RESET.getColor());
                    String delIsbn = scan2.nextLine();
                    System.out.printf("%d Copies exist of this Book, How many do you want to delete : ", bookService.getBookQuantity(delIsbn));
                    int number = scan2.nextInt();
                    scan2.nextLine();
                    bookService.deleteBook(delIsbn, number);
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 4:
                    List<BorrowedBook> borrowedBooks = borrowedBooksService.displayBorrowedBooks();
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"║            Title               ║            Author            ║            ISBN             ║            Year             ║         Borrow_Date         ║               Due_Date                 ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬══════════════════╬═════════════════════╣");
                    for (BorrowedBook book : borrowedBooks) {
                        System.out.printf('\t'+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %38s "+ Colors.CYAN.getColor() +"║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear(), book.getBorrowDate(), book.getDueDate());
                    }
                    System.out.println('\t'+ Colors.CYAN.getColor() +"╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 5:
                    List<LostBook> lostBooks = lostBooksService.displayLostBooks();
                    System.out.print("\n\n");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║");
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
                    for (LostBook book : lostBooks) {
                        System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %38s "+ Colors.CYAN.getColor() +"║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear());
                    }
                    System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 6:
                    int[] counts = bookService.bookStatistics();
                    int availableBooksCount = counts[0];
                    int borrowedBooksCount = counts[1];
                    int lostBooksCount = counts[2];
                    try (FileWriter writer = new FileWriter("./statistiques.txt")) {
                        writer.write("\t        ╔══════════════════════╦══════════════════╦══════════════════════╦══════════════════╦═════════════════════╦═══════════════╗\n");
                        writer.write("\t        ║            Total Available Books        ║            Total Borrowed Books         ║             Total Lost Books        ║\n");
                        writer.write("\t        ╠══════════════════════╬══════════════════╬══════════════════════╬══════════════════╬═════════════════════╬═══════════════╣\n");
                        writer.write(String.format("\t        ║ %39s ║ %39s ║ %35s ║%n", availableBooksCount, borrowedBooksCount, lostBooksCount));
                        writer.write("\t        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");

                        System.out.println(Colors.GREEN.getColor() +"Statistics saved to " + "./statistiques.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                case 7:
                    user_id = 0;
                    Ressource.clearTerminal();
                    Ressource.logout();
                    Ressource.waitForEnter();
                    menu();
                    break;
                case 8:
                    Ressource.clearTerminal();
                    Ressource.quitApp();
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"\nInvalid choice. Please try again.");
                    Ressource.waitForEnter();
                    Ressource.clearTerminal();
            }
        }
    }
}
