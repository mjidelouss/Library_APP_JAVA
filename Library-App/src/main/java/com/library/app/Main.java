package com.library.app;
import com.library.app.domain.*;
import com.library.app.infrastructure.DbConnection;
import com.library.app.repository.*;
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
        art();
        if (dbConnection != null) {
            menu();
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    public static int menu() throws InterruptedException {
        clearTerminal();
        Connection dbConnection = DbConnection.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗                     Welcome to YouCode's                      ██");
            System.out.println('\t'+"                         ██╗                        Library System                         ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██╗ 1- Login.                                                    ═██");
            System.out.println('\t'+"                         ██╗ 2- Register.                                                 ═██");
            System.out.println('\t'+"                         ██╗ 3- Quit.                                                     ═██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            int choice = scanner.nextInt();
            MemberRepository memberRepository = new MemberRepository(dbConnection);
            MemberService memberService = new MemberService(memberRepository);
            UserRepository userRepository = new UserRepository(dbConnection);
            UserService userService = new UserService(userRepository);
            Console console = System.console();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter Email : ");
                    String email = scan.nextLine();
                    char[] passwordArray = console.readPassword("Enter Password : ");
                    String password = new String(passwordArray);
                    User loggedInUser = userService.login(email, password);
                    if (loggedInUser != null) {
                        int user_id = loggedInUser.getId();
                        if (memberService.checkMember(user_id)) {
                            clearTerminal();
                            memberMenu(user_id);
                        } else {
                            clearTerminal();
                            librarianMenu(user_id);
                        }
                    } else {
                        System.out.println("Wrong Credentials, Try Again.");
                        waitForEnter();
                        menu();
                    }
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    System.out.print("Name : ");
                    String name = scan1.nextLine();
                    System.out.print("Telephone : ");
                    String telephone = scan1.nextLine();
                    System.out.print("Adresse : ");
                    String adresse = scan1.nextLine();
                    System.out.print("Email : ");
                    String newEmail = scan1.nextLine();
                    System.out.print("Password : ");
                    String newPassword = scan1.nextLine();
                    User loggedUser = userService.register(name, newEmail, newPassword, telephone, adresse);
                    if (loggedUser != null) {
                        int user_id = loggedUser.getId();
                        clearTerminal();
                        memberMenu(user_id);
                    }
                    break;
                case 3:
                    quitApp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗                     Welcome to YouCode's                      ██");
            System.out.println('\t'+"                         ██╗                        Library System                         ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██╗ 1- Display the list of Available Books.                      ═██");
            System.out.println('\t'+"                         ██╗ 2- Search for a Book.                                        ═██");
            System.out.println('\t'+"                         ██╗ 3- Borrow a Book.                                            ═██");
            System.out.println('\t'+"                         ██╗ 4- Return a Borrowed Book.                                   ═██");
            System.out.println('\t'+"                         ██╗ 5- Disconnect.                                               ═██");
            System.out.println('\t'+"                         ██╗ 6- Quit.                                                     ═██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    List<Book> books = bookService.displayBooks();
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═════════════════════════════════════════════════════╗");
                    System.out.println("\t        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
                    for (Book book : books) {
                        System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║ %35s ║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
                    }
                    System.out.println("\t        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
                    break;
                case 2:
                    System.out.print("Search Term : ");
                    String searchTerm = scanner.nextLine();
                    List<Book> searchedBooks = bookService.searchBook(searchTerm);
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════════════════════════════╗");
                    System.out.println("\t        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
                    for (Book book : searchedBooks) {
                        System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║ %35s ║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
                    }
                    System.out.println("\t        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
                    break;
                case 3:
                    System.out.print("Enter ISBN : ");
                    String borrowIsbn = scanner.nextLine();
                    BorrowedBook borrowedBook = borrowedBooksService.borrowBook(user_id, borrowIsbn);
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
                    System.out.println("\t        ║           Book ID              ║            Title             ║            ISBN             ║         Borrow_Date         ║            Due_Date                    ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬════════════════════════════════════════╣");
                        System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║ %38s ║%n", borrowedBook.getId(), borrowedBook.getBookTitle(), borrowedBook.getBookIsbn(), borrowedBook.getBorrowDate(), borrowedBook.getDueDate());
                    System.out.println("\t        ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
                    break;
                case 4:
                    System.out.print("Enter ISBN : ");
                    String returnIsbn = scanner.nextLine();
                    BorrowedBook returnedBook = borrowedBooksService.returnBook(user_id, returnIsbn);
                    LocalDate todayDate = LocalDate.now();
                    Date returnedDate = Date.valueOf(todayDate);
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
                    System.out.println("\t        ║            Book ID             ║            Title             ║            ISBN             ║        Returned_Date        ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
                    System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║%n", returnedBook.getId(), returnedBook.getBookTitle(), returnedBook.getBookIsbn(), returnedDate);
                    System.out.println("\t        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
                    break;
                case 5:
                    user_id = 0;
                    clearTerminal();
                    logout();
                    waitForEnter();
                    menu();
                    break;
                case 6:
                    clearTerminal();
                    quitApp();
                    waitForEnter();
                    clearTerminal();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    waitForEnter();
                    clearTerminal();
            }
        }
    }

    public static void librarianMenu(int user_id) throws InterruptedException {
        clearTerminal();
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
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗                     Welcome to YouCode's                      ██");
            System.out.println('\t'+"                         ██╗                        Library System                         ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██╗ 1- Add a new Book          .                                 ═██");
            System.out.println('\t'+"                         ██╗ 2- Modify an existing Book.                                  ═██");
            System.out.println('\t'+"                         ██╗ 3- Delete a Book.                                            ═██");
            System.out.println('\t'+"                         ██╗ 4- Display List of borrowed Books.                           ═██");
            System.out.println('\t'+"                         ██╗ 5- Display List of Lost Books.                               ═██");
            System.out.println('\t'+"                         ██╗ 6- Generate Books Statistics report.                         ═██");
            System.out.println('\t'+"                         ██╗ 7- Disconnect.                                               ═██");
            System.out.println('\t'+"                         ██╗ 8- Quit.                                                     ═██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Title : ");
                    String title = scan.nextLine();
                    System.out.print("Author : ");
                    String author = scan.nextLine();
                    System.out.print("ISBN : ");
                    String isbn = scan.nextLine();
                    System.out.print("Quantity : ");
                    int quantity = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Category : ");
                    String category = scan.nextLine();
                    System.out.print("Year : ");
                    int year = scan.nextInt();
                    scan.nextLine();
                    Book bookManager = new Book(title, author, isbn, quantity, category, year);
                    bookService.addBook(bookManager);
                    waitForEnter();
                    clearTerminal();
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    bookService.displayBooks();
                    System.out.print("Enter Book ISBN : ");
                    String oldIsbn = scan1.nextLine();
                    System.out.print("New Title : ");
                    bookMaster.setTitle(scan1.nextLine());
                    System.out.print("New Author : ");
                    bookMaster.setAuthor(scan1.nextLine());
                    System.out.print("New ISBN : ");
                    bookMaster.setIsbn(scan1.nextLine());
                    System.out.print("New Quantity : ");
                    bookMaster.setQuantity(scan1.nextInt());
                    scan1.nextLine();
                    System.out.print("New Category : ");
                    bookMaster.setCategory(scan1.nextLine());
                    scan1.nextLine();
                    System.out.print("New Year : ");
                    bookMaster.setYear(scan1.nextInt());
                    scan1.nextLine();
                    if (bookMaster.getIsbn().isEmpty()) {
                        bookMaster.setIsbn(oldIsbn);
                    }
                    bookService.updateBook(oldIsbn, bookMaster);
                    waitForEnter();
                    clearTerminal();
                    break;
                case 3:
                    Scanner scan2 = new Scanner(System.in);
                    bookService.displayBooks();
                    System.out.print("Enter Book ISBN : ");
                    String delIsbn = scan2.nextLine();
                    System.out.printf("%d Copies exist of this Book, How many do you want to delete : ", bookService.getBookQuantity(delIsbn));
                    int number = scan2.nextInt();
                    scan2.nextLine();
                    bookService.deleteBook(delIsbn, number);
                    waitForEnter();
                    clearTerminal();
                    break;
                case 4:
                    List<BorrowedBook> borrowedBooks = borrowedBooksService.displayBorrowedBooks();
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
                    System.out.println("\t        ║            Title               ║            Author            ║            ISBN             ║            Year             ║         Borrow_Date         ║               Due_Date                 ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬══════════════════╬═════════════════════╣");
                    for (BorrowedBook book : borrowedBooks) {
                        System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║ %27s ║ %38s ║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear(), book.getBorrowDate(), book.getDueDate());
                    }
                    System.out.println("\t        ╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
                    break;
                case 5:
                    List<LostBook> lostBooks = lostBooksService.displayLostBooks();
                    System.out.println("\t        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
                    System.out.println("\t        ║            Title               ║            Author            ║            ISBN             ║            Year             ║");
                    System.out.println("\t        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
                    for (LostBook book : lostBooks) {
                        System.out.printf("\t        ║ %30s ║ %28s ║ %27s ║ %27s ║ %27s ║ %38s ║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear());
                    }
                    System.out.println("\t        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
                    waitForEnter();
                    clearTerminal();
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

                        System.out.println("Statistics saved to " + "./statistiques.txt");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    waitForEnter();
                    clearTerminal();
                    break;
                case 7:
                    user_id = 0;
                    clearTerminal();
                    logout();
                    waitForEnter();
                    menu();
                    break;
                case 8:
                    clearTerminal();
                    quitApp();
                    waitForEnter();
                    clearTerminal();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    waitForEnter();
                    clearTerminal();
            }
        }
    }
    public static void art() throws InterruptedException {
        String[] lines = {
                '\t'+"      ██╗   ██╗ ██████╗ ██╗   ██╗ ██████╗ ██████╗ ██████╗ ███████╗    ██╗     ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗",
                '\t'+"      ╚██╗ ██╔╝██╔═══██╗██║   ██║██╔════╝██╔═══██╗██╔══██╗██╔════╝    ██║     ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝",
                '\t'+"       ╚████╔╝ ██║   ██║██║   ██║██║     ██║   ██║██║  ██║█████╗      ██║     ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝ ",
                '\t'+"        ╚██╔╝  ██║   ██║██║   ██║██║     ██║   ██║██║  ██║██╔══╝      ██║     ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝  ",
                '\t'+"         ██║   ╚██████╔╝╚██████╔╝╚██████╗╚██████╔╝██████╔╝███████╗    ███████╗██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║   ",
                '\t'+"         ╚═╝    ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝    ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
    }

    public static void quitApp() throws InterruptedException {
        String[] lines = {
                '\t'+"                          ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗    ██╗",
                '\t'+"                         ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝    ██║",
                '\t'+"                         ██║  ███╗██║   ██║██║   ██║██║  ██║██████╔╝ ╚████╔╝ █████╗      ██║",
                '\t'+"                         ██║   ██║██║   ██║██║   ██║██║  ██║██╔══██╗  ╚██╔╝  ██╔══╝      ╚═╝",
                '\t'+"                         ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗    ██╗",
                '\t'+"                          ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝    ╚═╝",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
        System.exit(0);
    }
    public static void logout() throws InterruptedException {
        String[] lines = {
                '\t'+"               █████                                              █████                         █████   ",
                '\t'+"               ░░███                                              ░░███                         ░░███    ",
                '\t'+"                ░███         ██████   ███████  ███████  ██████   ███████      ██████ █████ ████ ███████  ",
                '\t'+"                ░███        ███░░███ ███░░███ ███░░███ ███░░███ ███░░███     ███░░███░░███ ░███ ░░░███░   ",
                '\t'+"                ░███       ░███ ░███░███ ░███░███ ░███░███████ ░███ ░███    ░███ ░███ ░███ ░███   ░███    ",
                '\t'+"                ░███      █░███ ░███░███ ░███░███ ░███░███░░░  ░███ ░███    ░███ ░███ ░███ ░███   ░███ ███",
                '\t'+"                ███████████░░██████ ░░███████░░███████░░██████░░████████    ░░██████ ░░████████   ░░█████ ",
                '\t'+"                ░░░░░░░░░░░  ░░░░░░  ░░░░░███ ░░░░░███  ░░░░░░  ░░░░░░░░     ░░░░░░    ░░░░░░░░    ░░░░░  ",
                '\t'+"                                     ███ ░███ ███ ░███                                                    ",
                '\t'+"                                     ░░██████ ░░██████                                                     ",
                '\t'+"                                       ░░░░░░   ░░░░░░                                                      ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220);
        }
    }
    public static void waitForEnter() {
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    public static void clearTerminal() {
        System. out.print("\033[H\033[2J");
        System. out.flush();
    }
}
