package com.library.app;
import com.library.app.domain.*;
import com.library.app.domain.enums.Colors;
import com.library.app.infrastructure.DbConnection;
import com.library.app.repository.*;
import com.library.app.ressources.Ressource;
import com.library.app.services.*;
import java.io.Console;
import java.sql.*;
import java.util.Scanner;
import java.util.List;

import static com.library.app.ressources.Ressource.clearTerminal;
import static com.library.app.ressources.Ressource.waitForEnter;

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
        clearTerminal();
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
                    Ressource.login(scan, console, userService, memberService);
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    Ressource.register(scan1, userService);
                    break;
                case 3:
                    Ressource.quitApp();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"\n\nInvalid choice. Please try again.");
                    waitForEnter();
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
                    Ressource.availableBooksTable(books);
                    waitForEnter();
                    clearTerminal();
                    break;
                case 2:
                    List<Book> books1 = bookService.displayBooks();
                    Ressource.availableBooksTable(books1);
                    System.out.print(Colors.PURPLE.getColor() +"\nSearch Term : " + Colors.RESET.getColor());
                    String searchTerm = scanner.nextLine();
                    List<Book> searchedBooks = bookService.searchBook(searchTerm);
                    Ressource.searchedBookTable(searchedBooks);
                    break;
                case 3:
                    List<Book> books2 = bookService.displayBooks();
                    Ressource.availableBooksTable(books2);
                    System.out.print(Colors.PURPLE.getColor() +"\nEnter ISBN : " + Colors.RESET.getColor());
                    String borrowIsbn = scanner.nextLine();
                    BorrowedBook borrowedBook = borrowedBooksService.borrowBook(user_id, borrowIsbn);
                    Ressource.borrowedBookTable(borrowedBook);
                    break;
                case 4:
                    List<Book> books3 = bookService.displayBooks();
                    Ressource.availableBooksTable(books3);
                    System.out.print(Colors.PURPLE.getColor() +"\nEnter ISBN : " + Colors.RESET.getColor());
                    String returnIsbn = scanner.nextLine();
                    BorrowedBook returnedBook = borrowedBooksService.returnBook(user_id, returnIsbn);
                    Ressource.returnBookTable(returnedBook);
                    break;
                case 5:
                    user_id = 0;
                    clearTerminal();
                    Ressource.logout();
                    waitForEnter();
                    menu();
                    break;
                case 6:
                    Ressource.quitApp();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"Invalid choice. Please try again.");
                    waitForEnter();
                    clearTerminal();
            }
        }
    }

    public static void librarianMenu(int user_id) throws InterruptedException {
        Connection dbConnection = DbConnection.connect();
        Book bookMaster = new Book();
        BookRepository bookRepository = new BookRepository(dbConnection);
        BookService bookService = new BookService(bookRepository);
        BorrowedBooksRepository borrowedBooksRepository = new BorrowedBooksRepository(dbConnection);
        BorrowedBooksService borrowedBooksService = new BorrowedBooksService(borrowedBooksRepository);
        LostBooksRepository lostBooksRepository = new LostBooksRepository(dbConnection);
        LostBooksService lostBooksService = new LostBooksService(lostBooksRepository);
        lostBooksService.insertLostBooks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Ressource.printLibrarianMenu();
            System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Your Choice : " + Colors.RESET.getColor());
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    Ressource.addBook(scan, bookService);
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    bookService.displayBooks();
                    Ressource.updateBook(scan1, bookService, bookMaster);
                    break;
                case 3:
                    Scanner scan2 = new Scanner(System.in);
                    bookService.displayBooks();
                    Ressource.deleteBook(scan2, bookService);
                    break;
                case 4:
                    List<BorrowedBook> borrowedBooks = borrowedBooksService.displayBorrowedBooks();
                    Ressource.displayBorrowedBooksTable(borrowedBooks);
                    break;
                case 5:
                    List<LostBook> lostBooks = lostBooksService.displayLostBooks();
                    Ressource.displayLostBooksTable(lostBooks);
                    break;
                case 6:
                    int[] counts = bookService.bookStatistics();
                    Ressource.generateStatistiquesTable(counts);
                    break;
                case 7:
                    user_id = 0;
                    clearTerminal();
                    Ressource.logout();
                    waitForEnter();
                    menu();
                    break;
                case 8:
                    Ressource.quitApp();
                    break;
                default:
                    System.out.println(Colors.RED.getColor() +"\nInvalid choice. Please try again.");
                    waitForEnter();
                    clearTerminal();
            }
        }
    }
}
