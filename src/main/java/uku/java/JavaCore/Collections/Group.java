package uku.java.JavaCore.Collections;

public enum Group {
    //«Работа», «Семья», «Друзья
    WORK("Work"),
    FAMILY("Family"),
    FRIEND("Friend"),;

    private final String description;

    Group(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
