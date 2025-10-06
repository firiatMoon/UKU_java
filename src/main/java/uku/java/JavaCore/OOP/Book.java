package uku.java.JavaCore.OOP;

import java.util.Objects;

public class Book extends Publication {
    private String ISBN;

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        this.ISBN = ISBN;
    }

    private String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String getType() {
        return PublicationType.BOOK.getDescription();
    }

    @Override
    public String toString(){
        return String.format("%s: %s, ISBN: %s", getType(), super.toString(), ISBN);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(book.ISBN, ISBN);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(super.hashCode(), ISBN);
    }

    @Override
    public void printDetails() {
        System.out.println(toString());
    }
}
