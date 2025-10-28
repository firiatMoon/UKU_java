package uku.java.JavaCore.Basics;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactAccountingSystem {
    private static final int MAX_CONTACT = 100;
    private static final String[] names = new String[MAX_CONTACT];
    private static final String[] phoneNumbers = new String[MAX_CONTACT];
    private static int countContact = 0;

    public static void main(String[] args) {
        Scanner scanner = new
                Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.printf("%nMenu items: %n" +
                    "1. Add a contact %n" +
                    "2. View contacts %n" +
                    "3. Find a contact %n" +
                    "4. Delete a contact %n" +
                    "5. Exit %n%n");

            String input = scanner.nextLine().trim();
            try {
                    switch (Integer.parseInt(input)) {
                        case 1 -> addContact(scanner);
                        case 2 -> getContacts();
                        case 3 -> findContact(scanner);
                        case 4 -> deleteContact(scanner);
                        case 5 -> exit = true;
                        default -> System.out.println("Invalid input");
                    }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }

    public static void addContact(Scanner scanner) {
        if (countContact >= MAX_CONTACT) {
            System.out.println("Maximum number of contacts reached.");
            return;
        }

        System.out.println("Enter the name of the contact you would like to add:");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Contact name cannot be empty.");
        }

        System.out.println("Enter the phone number of the contact you would like to add (example: +71234567890):");
        String phoneNumber = scanner.nextLine().trim();

        if (phoneNumber.isEmpty()){
            System.out.println("Phone number cannot be empty");
            return;
        }

        if (validationPhoneNumber(phoneNumber)) {
            phoneNumbers[countContact] = phoneNumber;
            names[countContact] = name;
        } else {
            System.out.println("Invalid phone number. We cannot save your data. Try again.");
            return;
        }
        countContact++;
    }

    private static boolean validationPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+7\\d{10}");
        return pattern.matcher(phoneNumber).matches();
    }

    public static void getContacts() {
        if (countContact <= 0){
            System.out.println("The contact list is empty");
            return;
        }

        for (int i = 0; i < countContact; i++) {
            System.out.printf("Name: %s, phone number: %s%n", names[i], phoneNumbers[i]);
        }
    }

    public static void findContact(Scanner scanner) {
        if (countContact <= 0){
            System.out.println("The contact list is empty");
            return;
        }

        System.out.println("Enter the name of the contact you would like to search:");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Contact name cannot be empty");
            return;
        }

        boolean found = false;
        for (int i = 0; i < countContact; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                System.out.printf("Name: %s, phone number: %s", names[i], phoneNumbers[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found");
        }
    }

    public static void deleteContact(Scanner scanner) {
        if (countContact <= 0){
            System.out.println("The contact list is empty");
            return;
        }

        System.out.println("Enter the name of the contact you would like to delete:");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Contact name cannot be empty");
            return;
        }

        int indexDeleted = -1;
        for (int i = 0; i < countContact; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                names[i] = null;
                phoneNumbers[i] = null;
                indexDeleted = i;
                break;
            }
        }

        for (int i = indexDeleted; i < countContact - 1; i++) {
            names[i] = names[i + 1];
            phoneNumbers[i] = phoneNumbers[i + 1];
        }

        countContact--;

        if (indexDeleted < 0){
            System.out.println("The name not found");
        }
    }
}
