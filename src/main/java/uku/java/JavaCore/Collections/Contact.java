package uku.java.JavaCore.Collections;

import java.util.Objects;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private Group group;

    public Contact(String name, String phone, String email, Group group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return String.format(" %s | %s | %s", name, phone, email);
    }
    //Иван Иванов | 123456 | ivan@example.com

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return Objects.equals(contact.getName(), name) &&
                Objects.equals(contact.getPhone(), phone);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(name, phone);
    }
}
