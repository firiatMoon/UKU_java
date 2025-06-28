package uku.java.OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private final List<Publication> publications = new ArrayList<>();

    public void addPublication(Publication pub){
        publications.add(pub);
        System.out.println("Publication added successfully!");
    }

    public void listPublications(){
        if (publications.isEmpty()){
            System.out.println("There are no publications.");
        } else {
            publications.forEach(System.out::println);
        }
    }

    public void searchByAuthor(Scanner scanner) {
        if (publications.isEmpty()){
            System.out.println("There are no publications.");
            return;
        }
        System.out.println("Enter publication by author: ");
        String author = scanner.nextLine();
        List<Publication> pub = publications.stream()
                    .filter(publication -> publication.getAuthor().equalsIgnoreCase(author))
                    .toList();
        if (pub.isEmpty()){
            System.out.println("Publications not found.");
        } else {
            pub.forEach(Publication::printDetails);
        }
    }

    public void deletePublication(Scanner scanner) {
        if (publications.isEmpty()){
            System.out.println("There are no publications.");
            return;
        }

        System.out.println("Enter title of publications: ");
        String title = scanner.nextLine().trim();

        for(Publication pub : publications) {
            if (pub.getTitle().equalsIgnoreCase(title)) {
                publications.remove(pub);
                System.out.println("Publication deleted successfully!");
                return;
            } else {
                System.out.println("The specified publication does not exist in the collection.");
            }
        }
    }
}
