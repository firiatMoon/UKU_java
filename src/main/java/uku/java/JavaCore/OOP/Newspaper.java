package uku.java.JavaCore.OOP;

import java.util.Objects;

public class Newspaper extends Publication{
    private String publicationDay;

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        this.publicationDay = publicationDay;
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    @Override
    public String getType() {
        return PublicationType.NEWSPAPER.getDescription();
    }

    @Override
    public String toString() {
        return String.format("%s: %s, Publication day: %s", getType(), super.toString(), publicationDay);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Newspaper newspaper = (Newspaper) obj;
        return Objects.equals(newspaper.publicationDay, publicationDay);
    }

    @Override
    public int hashCode(){
        return 31 * Objects.hash(super.hashCode(), publicationDay);
    }

    @Override
    public void printDetails() {
        System.out.println(toString());
    }
}
