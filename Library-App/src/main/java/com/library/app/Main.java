package com.library.app;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = DbConnection.connect();

        if (dbConnection != null) {
            Book bookManager = new Book();
            bookManager.borrowBook(dbConnection, 2, "E141439518");
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    public static int menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("---- Welcome to Library App Management System ----");
        System.out.println("1- Login.");
        System.out.println("2- Register.");
        System.out.println("3- Quit.");
        int choice = input.nextInt();
        return choice;
    }

    public int memberMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose the operation you want to effect :");
        System.out.println("1- Display the list of Available Books.");
        System.out.println("2- Search for a Book.");
        System.out.println("3- Borrow a Book.");
        System.out.println("3- Return a Borrowed Book.");
        System.out.println("4- Disconnect.");
        System.out.println("5- Quit.");
        int choice = input.nextInt();
        return choice;
    }

    public int librarianMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose the operation you want to effect :");
        System.out.println("1- Add a new Book.");
        System.out.println("2- Modify an existing Book.");
        System.out.println("3- Delete a Book.");
        System.out.println("4- Display List of borrowed Books.");
        System.out.println("5- Generate Books Statistiques report.");
        System.out.println("6- Disconnect.");
        System.out.println("7- Quit.");
        int choice = input.nextInt();
        return choice;
    }

    public static void quitApp() {
        System.exit(0);
    }
}
