package com.library.app;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Connection dbConnection = DbConnection.connect();
        menu();
        if (dbConnection != null) {
            librarianMenu();
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
        String[] lines = art();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (String line : lines) {
                System.out.println(line);
                Thread.sleep(250); // Sleep for 500 milliseconds between lines
            }
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
            switch (choice) {
                case 1:
                    //

                    break;
                case 2:
                    //

                    break;
                case 3:
                    // Quit
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public int memberMenu() throws InterruptedException {
        String[] lines = art();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (String line : lines) {
                System.out.println(line);
                Thread.sleep(250); // Sleep for 500 milliseconds between lines
            }
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
            switch (choice) {
                case 1:
                    //

                    break;
                case 2:
                    //

                    break;
                case 3:
                    //

                    break;
                case 4:
                    //

                    break;
                case 5:
                    //

                    break;
                case 6:
                    // Quit
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void librarianMenu() throws InterruptedException {
        String[] lines = art();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (String line : lines) {
                System.out.println(line);
                Thread.sleep(250); // Sleep for 500 milliseconds between lines
            }
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
            switch (choice) {
                case 1:
                    // Add a new Book

                    break;
                case 2:
                    // Modify an existing Book

                    break;
                case 3:
                    // Delete a Book

                    break;
                case 4:
                    // Display List of borrowed Books

                    break;
                case 5:
                    // Generate Books Statistics report

                    break;
                case 6:
                    // Disconnect

                    break;
                case 7:
                    // Quit
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static String[] art() {
        String[] lines = {
                '\t'+"      ██╗   ██╗ ██████╗ ██╗   ██╗ ██████╗ ██████╗ ██████╗ ███████╗    ██╗     ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗",
                '\t'+"      ╚██╗ ██╔╝██╔═══██╗██║   ██║██╔════╝██╔═══██╗██╔══██╗██╔════╝    ██║     ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝",
                '\t'+"       ╚████╔╝ ██║   ██║██║   ██║██║     ██║   ██║██║  ██║█████╗      ██║     ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝ ",
                '\t'+"        ╚██╔╝  ██║   ██║██║   ██║██║     ██║   ██║██║  ██║██╔══╝      ██║     ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝  ",
                '\t'+"         ██║   ╚██████╔╝╚██████╔╝╚██████╗╚██████╔╝██████╔╝███████╗    ███████╗██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║   ",
                '\t'+"         ╚═╝    ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝    ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ",
        };
        return lines;
    }
}
