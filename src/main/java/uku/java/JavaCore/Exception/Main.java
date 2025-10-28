package uku.java.JavaCore.Exception;

import uku.java.JavaCore.Exception.exceptions.NoAvailableCopiesException;
import uku.java.JavaCore.Exception.exceptions.ItemNotFoundException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        try (Scanner scanner = new Scanner(System.in)){
            boolean exit = false;
            while (!exit) {
                System.out.printf("%nMenu items: %n" +
                        "1. View a list of all available books %n" +
                        "2. Rent out a book if it is available %n" +
                        "3. Return the book %n" +
                        "4. Add a new book %n" +
                        "5. Exit %n%n");

                String input = scanner.nextLine();
                try {
                    switch (Integer.parseInt(input)){
                        case 1 -> {
                            List<Book> catalog = library.getAllBooks();
                            catalog.forEach(System.out::println);
                        }
                        case 2 -> {
                            try {
                                library.takeBook(scanner);
                            } catch (NoAvailableCopiesException | ItemNotFoundException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        case 3 -> {
                            try {
                                library.returnBook(scanner);
                            } catch (ItemNotFoundException ex) {
                                System.out.println(ex.getMessage());
                            }

                        }
                        case 4 -> library.addBook(scanner);
                        case 5 -> exit = true;
                        default -> System.out.println("Invalid input");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
    }
}
