package uku.java.Collections;


import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        try (Scanner scanner = new Scanner(System.in)) {

            boolean exit = false;
            while (!exit) {
                printMenu();
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();
                try {
                    switch (Integer.parseInt(choice)) {
                        case 1 -> addContact(scanner, contactBook);
                        case 2 -> deleteContact(scanner, contactBook);
                        case 3 -> contactBook.viewAllContacts();
                        case 4 -> findContactByName(scanner, contactBook);
                        case 5 -> viewContactByGroup(scanner, contactBook);
                        case 0 -> exit = true;
                        default -> System.out.println("Invalid choice");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.printf("%nMenu items: %n" +
                "1. Add a contact %n" +
                "2. Delete a contact %n" +
                "3. View all contacts %n" +
                "4. Find a contact %n" +
                "5. View contacts by group %n" +
                "0. Exit %n%n");
    }

    public static void addContact(Scanner scanner, ContactBook contactBook) {
        System.out.print("Enter a contact name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter a contact phone number (example: +71234567890): ");
        String phone = scanner.nextLine().trim();

        phone = validationPhoneNumber(scanner, phone);

        System.out.print("Enter a contact email (example: example@example.com): ");
        String email = scanner.nextLine().trim();

        email = validationEmail(scanner, email);

        System.out.print("Select group: 1 - work, 2 - family, 3 - friend: ");
        String group = scanner.nextLine().trim();
        contactBook.addContact(new Contact(name, phone, email, choiceOfGroup(group)));
    }

    public static void deleteContact(Scanner scanner, ContactBook contactBook) {
        System.out.print("Enter a contact name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter a contact phone number (example: +71234567890): ");
        String phone = scanner.nextLine().trim();

        phone = validationPhoneNumber(scanner, phone);
        try {
            contactBook.deleteContact(name, phone);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String validationPhoneNumber(Scanner scanner, String phone) {
        Pattern pattern = Pattern.compile("^\\+7\\d{10}");

        while (!pattern.matcher(phone).matches()) {
            System.out.println("Invalid phone number");
            System.out.print("Enter a contact phone number (example: +71234567890): ");
            phone = scanner.nextLine().trim();
        }
        return phone;
    }

    private static String validationEmail(Scanner scanner, String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        while (!pattern.matcher(email).matches()) {
            System.out.println("Invalid phone number");
            System.out.print("Enter a contact phone number (example: +71234567890): ");
            email = scanner.nextLine().trim();
        }
        return email;
    }

    public static void findContactByName (Scanner scanner, ContactBook contactBook) {
        System.out.print("Enter a contact name: ");
        String name = scanner.nextLine().trim();

        try {
            contactBook.findContact(name);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void viewContactByGroup(Scanner scanner, ContactBook contactBook) {
        System.out.print("Enter a contact by group: 1 - work, 2 - family, 3 - friend: ");
        String group = scanner.nextLine().trim();

        try {
            contactBook.viewContactByGroup(choiceOfGroup(group));
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static Group choiceOfGroup(String choice) {
        Group group = null;
        try{
            switch (Integer.parseInt(choice)){
                case 1 -> group = Group.WORK;
                case 2 -> group = Group.FAMILY;
                case 3 -> group = Group.FRIEND;
                default -> System.out.println("Invalid group");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number.");
        }
        return group;
    }
}
