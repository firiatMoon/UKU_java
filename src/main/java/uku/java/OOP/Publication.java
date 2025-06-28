package uku.java.OOP;

import java.util.Objects;

public abstract class Publication implements Printable{
    private static int publicationCount = 0;

    private String title;
    private String author;
    private int year;

    public Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        publicationCount++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int getPublicationCount() {
        return publicationCount;
    }

    public abstract String getType();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication publication = (Publication) obj;
        return Objects.equals(publication.title, title) &&
                Objects.equals(publication.author, author) &&
                publication.year == year;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(title, author, year);
    }

    @Override
    public String toString() {
        return String.format("title: %s, author: %s, year: %s", getTitle(), getAuthor(), getYear());
    }
}
