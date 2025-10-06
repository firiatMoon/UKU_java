package uku.java.SpringCore.entities;

import java.util.List;

public class User {
    private Long id;
    private String login;
    private List<Account> accountList;

    public User(Long id, String login, List<Account> accountList) {
        this.id = id;
        this.login = login;
        this.accountList = accountList;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAccountList() {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "User created: User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
