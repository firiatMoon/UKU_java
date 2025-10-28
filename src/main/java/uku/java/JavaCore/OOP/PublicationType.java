package uku.java.JavaCore.OOP;

public enum PublicationType {
    BOOK("Book"),
    MAGAZINE("Magazine"),
    NEWSPAPER("Newspaper");

    private final String description;

    PublicationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
