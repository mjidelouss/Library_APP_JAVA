package com.library.app;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Connection dbConnection = DbConnection.connect();
        menu();
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
            art();
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
            User user = new User();
            Member member = new Member();
            Librarian librarian = new Librarian();
            switch (choice) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter Email : ");
                    String email = scan.nextLine();
                    System.out.print("Enter Password : ");
                    String password = scan.nextLine();
                    User loggedInUser = user.login(dbConnection, email, password);
                    if (loggedInUser != null) {
                        int user_id = loggedInUser.getId();
                        if (member.checkMember(dbConnection, user_id)) {
                            memberMenu(user_id);
                        } else {
                            librarianMenu(user_id);
                        }
                    } else {
                        System.out.println("Wrong Credentials, Try Again.");
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
                    String oldPassword = scan1.nextLine();
                    System.out.print("Confirm Password : ");
                    String newPassword = scan1.nextLine();
                    User loggedUser = user.register(dbConnection, name, newEmail, newPassword, telephone, adresse);
                    if (loggedUser != null) {
                        int user_id = loggedUser.getId();
                        memberMenu(user_id);
                    }
                    break;
                case 3:
                    quitApp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static int memberMenu(int user_id) throws InterruptedException {
        clearTerminal();
        Connection dbConnection = DbConnection.connect();
        Book bookManager = new Book();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            art();
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
            User user = new User();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    bookManager.displayBooks(dbConnection, "Available");
                    break;
                case 2:
                    System.out.print("Search Term : ");
                    String searchTerm = scanner.nextLine();
                    scanner.nextLine();
                    bookManager.searchBook(dbConnection, searchTerm);
                    break;
                case 3:
                    System.out.print("Enter ISBN : ");
                    String borrowIsbn = scanner.nextLine();
                    scanner.nextLine();
                    bookManager.borrowBook(dbConnection, user_id, borrowIsbn);
                    break;
                case 4:
                    System.out.print("Enter ISBN : ");
                    String returnIsbn = scanner.nextLine();
                    scanner.nextLine();
                    bookManager.returnBook(dbConnection, user_id, returnIsbn);
                    break;
                case 5:
                    user_id = 0;
                    logout();
                    menu();
                    break;
                case 6:
                    quitApp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void librarianMenu(int user_id) throws InterruptedException {
        clearTerminal();
        Connection dbConnection = DbConnection.connect();
        Book bookMaster = new Book();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            art();
            System.out.println('\t'+"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗                     Welcome to YouCode's                      ██");
            System.out.println('\t'+"                         ██╗                        Library System                         ██");
            System.out.println('\t'+"                         ██╗                                                               ██");
            System.out.println('\t'+"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
            System.out.println('\t'+"                         ██╗                                                              ═██");
            System.out.println('\t'+"                         ██╗ 1- Add a new Book or a Copy.                                 ═██");
            System.out.println('\t'+"                         ██╗ 2- Modify an existing Book.                                  ═██");
            System.out.println('\t'+"                         ██╗ 3- Delete a Book.                                            ═██");
            System.out.println('\t'+"                         ██╗ 4- Display List of borrowed Books.                           ═██");
            System.out.println('\t'+"                         ██╗ 5- Generate Books Statistics report.                         ═██");
            System.out.println('\t'+"                         ██╗ 6- Disconnect.                                               ═██");
            System.out.println('\t'+"                         ██╗ 7- Quit.                                                     ═██");
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
                    System.out.print("Category : ");
                    String category = scan.nextLine();
                    System.out.print("Year : ");
                    int year = scan.nextInt();
                    Book bookManager = new Book(title, author, isbn, quantity, category, year, "Available");
                    bookManager.addBook(dbConnection);
                    break;
                case 2:
                    Scanner scan1 = new Scanner(System.in);
                    bookMaster.displayBooks(dbConnection, "");
                    System.out.print("Enter Book ISBN : ");
                    String oldIsbn = scan1.nextLine();
                    System.out.print("New Title : ");
                    String newTitle = scan1.nextLine();
                    System.out.print("New Author : ");
                    String newAuthor = scan1.nextLine();
                    System.out.print("New ISBN : ");
                    String newIsbn = scan1.nextLine();
                    System.out.print("New Quantity : ");
                    int newQuantity = scan1.nextInt();
                    System.out.print("New Category : ");
                    String newCategory = scan1.nextLine();
                    System.out.print("New Year : ");
                    int newYear = scan1.nextInt();
                    System.out.print("New Status : ");
                    String newStatus = scan1.nextLine();
                    if (newIsbn == "") {
                        newIsbn = oldIsbn;
                    }
                    bookMaster.updateBook(dbConnection, oldIsbn, newIsbn, newTitle, newAuthor, newQuantity, newCategory, newYear, newStatus);
                    break;
                case 3:
                    Scanner scan2 = new Scanner(System.in);
                    bookMaster.displayBooks(dbConnection, "");
                    System.out.print("Enter Book ISBN : ");
                    String delIsbn = scan2.nextLine();
                    bookMaster.deleteBook(dbConnection, delIsbn);
                    break;
                case 4:
                    bookMaster.displayBooks(dbConnection, "Checked Out");
                    break;
                case 5:
                    // Generate Books Statistics report

                    break;
                case 6:
                    user_id = 0;
                    logout();
                    menu();
                    break;
                case 7:
                    quitApp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
    }
    public static void clearTerminal() {
        for(int clear = 0; clear < 20; clear++) {
            System.out.println("\b") ;
        }
    }
}
