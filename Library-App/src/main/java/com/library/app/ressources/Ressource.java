package com.library.app.ressources;

import com.library.app.domain.enums.Colors;

import java.util.Scanner;

public class Ressource {
    public static void printUserMenu() throws InterruptedException {
        art();
        System.out.print("\n\n\n");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                     Welcome to YouCode's                      ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                        Library System                         ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 1- Login.                                                    ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 2- Register.                                                 ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 3- Quit.                                                     ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
    }

    public static void printMemberMenu() throws InterruptedException {
        System.out.print("\n\n\n");
        welcome();
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                     Welcome to YouCode's                      ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                        Library System                         ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 1- Display the list of Available Books.                      ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 2- Search for a Book.                                        ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 3- Borrow a Book.                                            ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 4- Return a Borrowed Book.                                   ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 5- Disconnect.                                               ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 6- Quit.                                                     ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
    }

    public static void printLibrarianMenu() throws InterruptedException {
        System.out.print("\n\n\n");
        welcome();
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                     Welcome to YouCode's                      ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                        Library System                         ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                               ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ █ ██ ██ ██ ██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 1- Add a new Book          .                                 ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 2- Modify an existing Book.                                  ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 3- Delete a Book.                                            ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 4- Display List of borrowed Books.                           ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 5- Display List of Lost Books.                               ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 6- Generate Books Statistics report.                         ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 7- Disconnect.                                               ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗ 8- Quit.                                                     ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██╗                                                              ═██");
        System.out.println('\t'+ Colors.RESET.getColor() +"                         ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██ ██");
    }
    public static void art() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.BLUE.getColor() +"                ██╗   ██╗ ██████╗ ██╗   ██╗ ██████╗ ██████╗ ██████╗ ███████╗    ██╗     ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗",
                '\t'+ Colors.BLUE.getColor() +"                ╚██╗ ██╔╝██╔═══██╗██║   ██║██╔════╝██╔═══██╗██╔══██╗██╔════╝    ██║     ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝",
                '\t'+ Colors.BLUE.getColor() +"                 ╚████╔╝ ██║   ██║██║   ██║██║     ██║   ██║██║  ██║█████╗      ██║     ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝ ",
                '\t'+ Colors.BLUE.getColor() +"                  ╚██╔╝  ██║   ██║██║   ██║██║     ██║   ██║██║  ██║██╔══╝      ██║     ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝  ",
                '\t'+ Colors.BLUE.getColor() +"                   ██║   ╚██████╔╝╚██████╔╝╚██████╗╚██████╔╝██████╔╝███████╗    ███████╗██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║   ",
                '\t'+ Colors.BLUE.getColor() +"                   ╚═╝    ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝    ╚══════╝╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220); // Sleep for 500 milliseconds between lines
        }
    }

    public static void quitApp() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.GREEN.getColor() +"                                    ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗    ██╗",
                '\t'+ Colors.GREEN.getColor() +"                                   ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝    ██║",
                '\t'+ Colors.GREEN.getColor() +"                                   ██║  ███╗██║   ██║██║   ██║██║  ██║██████╔╝ ╚████╔╝ █████╗      ██║",
                '\t'+ Colors.GREEN.getColor() +"                                   ██║   ██║██║   ██║██║   ██║██║  ██║██╔══██╗  ╚██╔╝  ██╔══╝      ╚═╝",
                '\t'+ Colors.GREEN.getColor() +"                                   ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗    ██╗",
                '\t'+ Colors.GREEN.getColor() +"                                    ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝    ╚═╝",
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
                '\t'+ Colors.RED.getColor() +"                   █████                                              █████                         █████   ",
                '\t'+ Colors.RED.getColor() +"                   ░░███                                              ░░███                         ░░███    ",
                '\t'+ Colors.RED.getColor() +"                    ░███         ██████   ███████  ███████  ██████   ███████      ██████ █████ ████ ███████  ",
                '\t'+ Colors.RED.getColor() +"                    ░███        ███░░███ ███░░███ ███░░███ ███░░███ ███░░███     ███░░███░░███ ░███ ░░░███░   ",
                '\t'+ Colors.RED.getColor() +"                    ░███       ░███ ░███░███ ░███░███ ░███░███████ ░███ ░███    ░███ ░███ ░███ ░███   ░███    ",
                '\t'+ Colors.RED.getColor() +"                    ░███      █░███ ░███░███ ░███░███ ░███░███░░░  ░███ ░███    ░███ ░███ ░███ ░███   ░███ ███",
                '\t'+ Colors.RED.getColor() +"                    ███████████░░██████ ░░███████░░███████░░██████░░████████    ░░██████ ░░████████   ░░█████ ",
                '\t'+ Colors.RED.getColor() +"                    ░░░░░░░░░░░  ░░░░░░  ░░░░░███ ░░░░░███  ░░░░░░  ░░░░░░░░     ░░░░░░    ░░░░░░░░    ░░░░░  ",
                '\t'+ Colors.RED.getColor() +"                                         ███ ░███ ███ ░███                                                    ",
                '\t'+ Colors.RED.getColor() +"                                         ░░██████ ░░██████                                                     ",
                '\t'+ Colors.RED.getColor() +"                                           ░░░░░░   ░░░░░░                                                      ",
        };
        for (String line : lines) {
            System.out.println(line);
            Thread.sleep(220);
        }
    }

    public static void welcome() throws InterruptedException {
        String[] lines = {
                "\n\n\n\n\n\n",
                '\t'+ Colors.BROWN.getColor() +"           █████   ███   █████          ████                                                ███████████                     █████     ",
                '\t'+ Colors.BROWN.getColor() +"          ░░███   ░███  ░░███          ░░███                                               ░░███░░░░░███                   ░░███      ",
                '\t'+ Colors.BROWN.getColor() +"           ░███   ░███   ░███   ██████  ░███   ██████   ██████  █████████████    ██████     ░███    ░███  ██████    ██████  ░███ █████",
                '\t'+ Colors.BROWN.getColor() +"           ░███   ░███   ░███  ███░░███ ░███  ███░░███ ███░░███░░███░░███░░███  ███░░███    ░██████████  ░░░░░███  ███░░███ ░███░░███ ",
                '\t'+ Colors.BROWN.getColor() +"           ░░███  █████  ███  ░███████  ░███ ░███ ░░░ ░███ ░███ ░███ ░███ ░███ ░███████     ░███░░░░░███  ███████ ░███ ░░░  ░██████░  ",
                '\t'+ Colors.BROWN.getColor() +"            ░░░█████░█████░   ░███░░░   ░███ ░███  ███░███ ░███ ░███ ░███ ░███ ░███░░░      ░███    ░███ ███░░███ ░███  ███ ░███░░███ ",
                '\t'+ Colors.BROWN.getColor() +"              ░░███ ░░███     ░░██████  █████░░██████ ░░██████  █████░███ █████░░██████     ███████████ ░░████████░░██████  ████ █████",
                '\t'+ Colors.BROWN.getColor() +"               ░░░   ░░░       ░░░░░░  ░░░░░  ░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░  ░░░░░░     ░░░░░░░░░░░   ░░░░░░░░  ░░░░░░  ░░░░ ░░░░░ ",
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
}
