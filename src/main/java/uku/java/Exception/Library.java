package uku.java.Exception;

import uku.java.Exception.exceptions.ItemNotFoundException;
import uku.java.Exception.exceptions.NoAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private final List<Book> catalog = new ArrayList<>();

    {
        catalog.add(new Book("The Master and Margarita", "M.Bulgakov", 5));
        catalog.add(new Book("The Little Prince", "Antoine de Saint-Exupery", 1));
        catalog.add(new Book("Alice’s Adventures in Wonderland", "Lewis Carroll", 8));
    }

    public void addBook(Scanner scanner) {
        System.out.print("Enter the name of the book: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter the number of copies of the book: ");
        int copies = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book item : catalog) {
            if (title.equals(item.getTitle()) && author.equals(item.getAuthor())) {
                item.setAvailableCopies(item.getAvailableCopies() + copies);
                found = true;
                break;
            }
        }

        if (!found) {
            catalog.add(new Book(title, author, copies));
        }
        System.out.println("Book added successfully!");
    }

    public void takeBook(Scanner scanner) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title is not be empty!");
            return;
        }

        boolean found = false;
        for (Book book : catalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getAvailableCopies() == 0) {
                    throw new NoAvailableCopiesException("No available copies.");
                } else {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    found = true;
                    System.out.printf("The book %s was taken", book.getTitle());
                    break;
                }
            }
        }

        if (!found) {
            throw new ItemNotFoundException("The book does not exist in our library.");
        }
    }

    public void returnBook(Scanner scanner){
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title is not be empty!");
            return;
        }

        boolean found = false;
        for (Book book : catalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                found = true;
                System.out.printf("The book %s was returned", book.getTitle());
                break;
            }
        }
        if (!found) {
            throw new ItemNotFoundException("The book does not exist in our library.");
        }
    }

    public List<Book> getAllBooks(){
        return catalog;
    }
}
