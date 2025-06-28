package uku.java.OOP;

import java.util.Objects;

public class Magazine extends Publication {
    private int issueNumber;

    public Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }
    @Override
    public String getType() {
        return PublicationType.MAGAZINE.getDescription();
    }

    @Override
    public String toString() {
        return String.format("%s: %s, Issue Number: %d", getType(), super.toString(), issueNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Magazine magazine = (Magazine) obj;
        return issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(super.hashCode(), issueNumber);
    }

    @Override
    public void printDetails() {
        System.out.println(toString());
    }
}
