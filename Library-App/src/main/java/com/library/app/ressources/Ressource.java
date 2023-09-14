package com.library.app.ressources;

import com.library.app.domain.Book;
import com.library.app.domain.BorrowedBook;
import com.library.app.domain.LostBook;
import com.library.app.domain.User;
import com.library.app.domain.enums.Colors;
import com.library.app.services.BookService;
import com.library.app.services.MemberService;
import com.library.app.services.UserService;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.library.app.Main.menu;
import static com.library.app.Main.librarianMenu;
import static com.library.app.Main.memberMenu;

public class Ressource {
    public static void printUserMenu() throws InterruptedException {
        art();
        System.out.print("\n\n\n");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╔═░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░  ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░░ ░░ ░░ ░░═╗");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                Welcome to YouCode's                                 ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                   Library System                                    ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (1) - Login.                                                                        ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (2) - Register.                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (3) - Quit.                                                                         ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╚═░░ ░░ ░░ ░░ ░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░═╝");
    }

    public static void printMemberMenu() throws InterruptedException {
        System.out.print("\n\n\n");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╔═░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░  ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░░ ░░ ░░ ░░═╗");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                Welcome to YouCode's                                 ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                   Library System                                    ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (1) - Display the list of Available Books.                                          ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (2) - Search for a Book.                                                            ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (3) - Borrow a Book.                                                                ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (4) - Return a Borrowed Book.                                                       ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (5) - Disconnect.                                                                   ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (6) - Quit.                                                                         ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╚═░░ ░░ ░░ ░░ ░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░═╝");
    }

    public static void printLibrarianMenu() throws InterruptedException {
        System.out.print("\n\n\n");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╔═░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░  ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░░ ░░ ░░ ░░═╗");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                Welcome to YouCode's                                 ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                   Library System                                    ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (1) - Add a new Book.                                                               ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (2) - Modify an existing Book.                                                      ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (3) - Delete a Book.                                                                ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (4) - Display List of borrowed Books.                                               ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (5) - Display List of Lost Books.                                                   ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (6) - Generate Books Statistics report.                                             ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (7) - Disconnect.                                                                   ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░ (8) - Quit.                                                                         ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ░░                                                                                     ░░");
        System.out.println('\t'+ Colors.RESET.getColor() +"\t                                            ╚═░░ ░░ ░░ ░░ ░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░ ░░═╝");
    }
    public static void art() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.BLUE.getColor() +"\t                                ██╗   ██╗ ██████╗ ██╗   ██╗ ██████╗ ██████╗ ██████╗ ███████╗    ██╗     ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗",
                '\t'+ Colors.BLUE.getColor() +"\t                                ╚██╗ ██╔╝██╔═══██╗██║   ██║██╔════╝██╔═══██╗██╔══██╗██╔════╝    ██║     ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝",
                '\t'+ Colors.BLUE.getColor() +"\t                                 ╚████╔╝ ██║   ██║██║   ██║██║     ██║   ██║██║  ██║█████╗      ██║     ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝ ",
                '\t'+ Colors.BLUE.getColor() +"\t                                  ╚██╔╝  ██║   ██║██║   ██║██║     ██║   ██║██║  ██║██╔══╝      ██║     ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝  ",
                '\t'+ Colors.BLUE.getColor() +"\t                                   ██║   ╚██████╔╝╚██████╔╝╚██████╗╚██████╔╝██████╔╝███████╗    ███████╗██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║   ",
                '\t'+ Colors.BLUE.getColor() +"\t                                   ╚═╝    ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝    ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
    }

    public static void quitApp() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.GREEN.getColor() +"                                                                 ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗    ██╗",
                '\t'+ Colors.GREEN.getColor() +"                                                                ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝    ██║",
                '\t'+ Colors.GREEN.getColor() +"                                                                ██║  ███╗██║   ██║██║   ██║██║  ██║██████╔╝ ╚████╔╝ █████╗      ██║",
                '\t'+ Colors.GREEN.getColor() +"                                                                ██║   ██║██║   ██║██║   ██║██║  ██║██╔══██╗  ╚██╔╝  ██╔══╝      ╚═╝",
                '\t'+ Colors.GREEN.getColor() +"                                                                ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗    ██╗",
                '\t'+ Colors.GREEN.getColor() +"                                                                 ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝    ╚═╝",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
        System.exit(0);
    }
    public static void logout() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.RED.getColor() +"                                                       █████                                              █████                         █████   ",
                '\t'+ Colors.RED.getColor() +"                                                       ░░███                                              ░░███                         ░░███    ",
                '\t'+ Colors.RED.getColor() +"                                                        ░███         ██████   ███████  ███████  ██████   ███████      ██████ █████ ████ ███████  ",
                '\t'+ Colors.RED.getColor() +"                                                        ░███        ███░░███ ███░░███ ███░░███ ███░░███ ███░░███     ███░░███░░███ ░███ ░░░███░   ",
                '\t'+ Colors.RED.getColor() +"                                                        ░███       ░███ ░███░███ ░███░███ ░███░███████ ░███ ░███    ░███ ░███ ░███ ░███   ░███    ",
                '\t'+ Colors.RED.getColor() +"                                                        ░███      █░███ ░███░███ ░███░███ ░███░███░░░  ░███ ░███    ░███ ░███ ░███ ░███   ░███ ███",
                '\t'+ Colors.RED.getColor() +"                                                        ███████████░░██████ ░░███████░░███████░░██████░░████████    ░░██████ ░░████████   ░░█████ ",
                '\t'+ Colors.RED.getColor() +"                                                        ░░░░░░░░░░░  ░░░░░░  ░░░░░███ ░░░░░███  ░░░░░░  ░░░░░░░░     ░░░░░░    ░░░░░░░░    ░░░░░  ",
                '\t'+ Colors.RED.getColor() +"                                                                             ███ ░███ ███ ░███                                                    ",
                '\t'+ Colors.RED.getColor() +"                                                                             ░░██████ ░░██████                                                     ",
                '\t'+ Colors.RED.getColor() +"                                                                               ░░░░░░   ░░░░░░                                                      ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220);
        }
    }

    public static void welcome() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.BROWN.getColor() +"                                 █████   ███   █████          ████                                                ███████████                     █████     ",
                '\t'+ Colors.BROWN.getColor() +"                                 ░░███   ░███  ░░███          ░░███                                               ░░███░░░░░███                   ░░███      ",
                '\t'+ Colors.BROWN.getColor() +"                                 ░███   ░███   ░███   ██████  ░███   ██████   ██████  █████████████    ██████     ░███    ░███  ██████    ██████  ░███ █████",
                '\t'+ Colors.BROWN.getColor() +"                                 ░███   ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███    ░██████████  ░░░░░███  ███░░███ ░███░░███ ",
                '\t'+ Colors.BROWN.getColor() +"                                 ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████     ░███░░░░░███  ███████ ░███ ░░░  ░██████░  ",
                '\t'+ Colors.BROWN.getColor() +"                                  ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░      ░███    ░███ ███░░███ ░███  ███ ░███░░███ ",
                '\t'+ Colors.BROWN.getColor() +"                                    ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████     ███████████ ░░████████░░██████  ████ █████",
                '\t'+ Colors.BROWN.getColor() +"                                      ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░     ░░░░░░░░░░░   ░░░░░░░░  ░░░░░░  ░░░░ ░░░░░ ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220);
        }
    }
    public static void waitForEnter() {
        System.out.println(Colors.RESET.getColor() + "\nPress Enter to continue...\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    public static void clearTerminal() {
        System. out.print("\033[H\033[2J");
        System. out.flush();
    }

    public static void availableBooksTable(List<Book> books) {
        System.out.print("\n\n");
        if (books.isEmpty()) {
            System.out.println("There is 0 Books Available.\n");
        } else {
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═════════════════════════════════════════════════════╗");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
            for (Book book : books) {
                System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %35s "+ Colors.CYAN.getColor() +"║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
            }
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
    }

    public static void searchedBookTable(List<Book> searchedBooks) {
        System.out.print("\n\n");
        if (searchedBooks.isEmpty()) {
            System.out.println("0 Books Found.\n");
        } else {
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════════════════════════════╗");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║            Quantity                 ║");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════════════════════════════╣");
            for (Book book : searchedBooks) {
                System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %35s "+ Colors.CYAN.getColor() +"║%n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getYear(), book.getQuantity());
            }
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            waitForEnter();
        }
        waitForEnter();
        clearTerminal();
    }

    public static void borrowedBookTable(BorrowedBook borrowedBook) {
        System.out.print("\n\n");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ║           Book ID              ║            Title             ║            ISBN             ║         Borrow_Date         ║            Due_Date                    ║");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬════════════════════════════════════════╣");
        System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %38s "+ Colors.CYAN.getColor() +"║%n", borrowedBook.getId(), borrowedBook.getBookTitle(), borrowedBook.getBookIsbn(), borrowedBook.getBorrowDate(), borrowedBook.getDueDate());
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        waitForEnter();
        clearTerminal();
    }

    public static void returnBookTable(BorrowedBook returnedBook) {
        LocalDate todayDate = LocalDate.now();
        Date returnedDate = Date.valueOf(todayDate);
        System.out.print("\n\n");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Book ID             ║            Title             ║            ISBN             ║        Returned_Date        ║");
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
        System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║%n", returnedBook.getId(), returnedBook.getBookTitle(), returnedBook.getBookIsbn(), returnedDate);
        System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        waitForEnter();
        clearTerminal();
    }

    public  static void displayBorrowedBooksTable(List<BorrowedBook> borrowedBooks) {
        System.out.print("\n\n");
        if (borrowedBooks.isEmpty()) {
            System.out.println("0 Books Found.\n");
        } else {
            System.out.println(Colors.CYAN.getColor() +"╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╦═════════════╦════════════════════════════════════════════════════════╗");
            System.out.println(Colors.CYAN.getColor() +"║            Title               ║            Author            ║            ISBN             ║            Year             ║         Borrow_Date         ║               Due_Date                 ║");
            System.out.println(Colors.CYAN.getColor() +"╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╬══════════════════╬═════════════════════╣");
            for (BorrowedBook book : borrowedBooks) {
                System.out.printf(Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %38s "+ Colors.CYAN.getColor() +"║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear(), book.getBorrowDate(), book.getDueDate());
            }
            System.out.println(Colors.CYAN.getColor() +"╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
        waitForEnter();
        clearTerminal();
    }

    public static void displayLostBooksTable(List<LostBook> lostBooks) {
        System.out.print("\n\n");
        if (lostBooks.isEmpty()) {
            System.out.println("0 Books Found.\n");
        } else {
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╔════════════════════════════════╦══════════════════════════════╦═════════════╦═══════════════╦═════════════╦═══════════════╗");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ║            Title               ║            Author            ║            ISBN             ║            Year             ║");
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╠══════════════╬═════════════════╬══════════════╬═══════════════╬═════════════╬═══════════════╬═════════════╬═══════════════╣");
            for (LostBook book : lostBooks) {
                System.out.printf('\t'+ Colors.CYAN.getColor() +"        ║"+ Colors.YELLOW.getColor() +" %30s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %28s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║"+ Colors.YELLOW.getColor() +" %27s "+ Colors.CYAN.getColor() +"║%n", book.getBookTitle(), book.getBookAuthor(), book.getBookIsbn(), book.getBookYear());
            }
            System.out.println('\t'+ Colors.CYAN.getColor() +"        ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
        waitForEnter();
        clearTerminal();
    }

    public static void generateStatistiquesTable(int[] counts) {
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
        waitForEnter();
        clearTerminal();
    }

    public static void login(Scanner scan, Console console, UserService userService, MemberService memberService) throws InterruptedException {
        System.out.print(Colors.PURPLE.getColor() +"\n\nEnter Email : " + Colors.RESET.getColor());
        String email = scan.nextLine();
        char[] passwordArray = console.readPassword(Colors.PURPLE.getColor() + "Enter Password : "+Colors.RESET.getColor());
        String password = new String(passwordArray);
        User loggedInUser = userService.login(email, password);
        if (loggedInUser != null) {
            int user_id = loggedInUser.getId();
            if (memberService.checkMember(user_id)) {
                Ressource.clearTerminal();
                System.out.print("\n\n\n");
                welcome();
                memberMenu(user_id);
            } else {
                Ressource.clearTerminal();
                System.out.print("\n\n\n");
                welcome();
                librarianMenu(user_id);
            }
        } else {
            System.out.println(Colors.RED.getColor() +"\n\nWrong Credentials, Try Again.");
            Ressource.waitForEnter();
            menu();
        }
    }

    public static void register(Scanner scan1, UserService userService) throws InterruptedException {
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
    }

    public static void addBook(Scanner scan, BookService bookService) {
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
        waitForEnter();
        clearTerminal();
    }

    public static void updateBook(Scanner scan1, BookService bookService, Book bookMaster) {
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
        waitForEnter();
        clearTerminal();
    }

    public static void deleteBook(Scanner scan2, BookService bookService) {
        System.out.print(Colors.PURPLE.getColor() +"Enter Book ISBN : " + Colors.RESET.getColor());
        String delIsbn = scan2.nextLine();
        System.out.printf("%d Copies exist of this Book, How many do you want to delete : ", bookService.getBookQuantity(delIsbn));
        int number = scan2.nextInt();
        scan2.nextLine();
        bookService.deleteBook(delIsbn, number);
        waitForEnter();
        clearTerminal();
    }

}
