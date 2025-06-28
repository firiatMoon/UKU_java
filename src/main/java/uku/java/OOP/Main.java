package uku.java.OOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.printf("%nMenu items: %n" +
                    "1. Add new publication %n" +
                    "2. List all publication %n" +
                    "3. Search publication by author %n" +
                    "4. Display the total number of publications %n" +
                    "5. Delete publication %n" +
                    "0. Exit %n%n");

            String input = scanner.nextLine().trim();
            try {
                switch (Integer.parseInt(input)) {
                    case 0 -> exit = true;
                    case 1 -> {
                        System.out.println("Select the type of publication: 1 - book, 2 - magazine, 3 - newspaper");
                        String type = scanner.nextLine().trim();
                        switch (Integer.parseInt(type)) {
                            case 1 -> {
                                System.out.println("Enter title of book: ");
                                String title = scanner.nextLine().trim();
                                System.out.println("Enter author: ");
                                String author = scanner.nextLine().trim();
                                System.out.println("Enter year: ");
                                int year = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter ISBN: ");
                                String isbn = scanner.nextLine().trim();
                                Publication book = new Book(title, author, year, isbn);
                                library.addPublication(book);
                            }
                            case 2 -> {
                                System.out.println("Enter title of magazine: ");
                                String title = scanner.nextLine().trim();
                                System.out.println("Enter author: ");
                                String author = scanner.nextLine().trim();
                                System.out.println("Enter year: ");
                                int year = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter Issue Number: ");
                                int issueNumber = scanner.nextInt();
                                Publication magazine = new Magazine(title, author, year, issueNumber);
                                library.addPublication(magazine);
                            }
                            case 3 -> {
                                System.out.println("Enter title of newspaper: ");
                                String title = scanner.nextLine().trim();
                                System.out.println("Enter author: ");
                                String author = scanner.nextLine().trim();
                                System.out.println("Enter year: ");
                                int year = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Enter publication day: ");
                                String day = scanner.nextLine().trim();
                                Publication newspaper = new Newspaper(title, author, year, day);
                                library.addPublication(newspaper);
                            }
                            default -> System.out.println("Invalid input");
                        };
                    }
                    case 2 -> library.listPublications();
                    case 3 -> library.searchByAuthor(scanner);
                    case 4 -> {
                        int publicationCount = Publication.getPublicationCount();
                        System.out.println("Publication count: " + publicationCount);
                    }
                    case 5 -> library.deletePublication(scanner);
                    default -> System.out.println("Invalid input");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        scanner.close();
    }
}




