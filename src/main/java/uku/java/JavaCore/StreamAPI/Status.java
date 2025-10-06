package uku.java.JavaCore.StreamAPI;

public enum Status {
    ACCEPTED("Принят"),
    IN_PROGRESS("В работе"),
    IN_PREPARATION("Готовится"),
    READY("Готов"),
    ISSUED("Выдан");

    private final String description;
    Status(String description) {
        this.description = toString();
    }

    public String getDescription() {
        return description;
    }
}
