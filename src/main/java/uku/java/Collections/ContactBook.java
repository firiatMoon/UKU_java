package uku.java.Collections;

import uku.java.Exception.exceptions.ItemNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class ContactBook {
    private final Set<Contact> contactBook = new LinkedHashSet<>();
    private Map<Group, List<Contact>> groupByContacts = new HashMap<>();

    {
        contactBook.add(new Contact("Mary", "+71234567890", "mary@mail.ru", Group.FAMILY));
        contactBook.add(new Contact("Peter", "+70987654321", "peter@mail.ru", Group.FRIEND));
        contactBook.add(new Contact("Ada", "+71029384756", "ada@mail.ru", Group.WORK));
        contactBook.add(new Contact("Lise", "+79867564534", "lise@mail.ru", Group.WORK));
        contactBook.add(new Contact("Mark", "+79867567543", "mark@mail.ru", Group.WORK));
        contactBook.add(new Contact("Ann", "+73456543456", "ann@mail.ru", Group.FRIEND));

        groupByContacts = contactBook.stream()
                .collect(Collectors.groupingBy(Contact::getGroup));
    }

    public void addContact(Contact contact) {
        if (contactBook.contains(contact)) {
            System.out.println("Such a contact already exists!");
            return;
        }

        contactBook.add(contact);
        groupByContacts.computeIfAbsent(contact.getGroup(), k -> new ArrayList<>()).add(contact);
        System.out.println("Contact added!");
    }

    public void deleteContact (String name, String phone) {
        boolean found = false;
        for (Contact contact : contactBook) {
            if (contact.getName().equalsIgnoreCase(name) && contact.getPhone().equalsIgnoreCase(phone)) {
                contactBook.remove(contact);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new ItemNotFoundException("Contact not found");
        }

        for (Map.Entry<Group, List<Contact>> entry : groupByContacts.entrySet()) {
            List<Contact> value = entry.getValue();

            for (Contact item : value) {
                if (item.getName().equalsIgnoreCase(name) && item.getPhone().equalsIgnoreCase(phone)) {
                    value.remove(item);
                    break;
                }
            }
        }
        System.out.println("Contact deleted!");
    }

    public void viewAllContacts() {
        if (contactBook.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        contactBook.forEach(System.out::println);
    }

    public void findContact(String name) {
        boolean found = false;
        for (Contact contact : contactBook) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            throw new ItemNotFoundException("Contact not found.");
        }
    }

    public void viewContactByGroup(Group group) {
        if (groupByContacts.containsKey(group)) {
            System.out.printf("--- Contacts in the group '%s': ---%n", group);
            groupByContacts.get(group).forEach(System.out::println);
        } else {
            throw new ItemNotFoundException("Contact not found.");
        }
    }
}
