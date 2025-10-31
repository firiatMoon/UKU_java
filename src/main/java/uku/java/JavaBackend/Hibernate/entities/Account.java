package uku.java.JavaBackend.Hibernate.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "money_amount")
    private BigDecimal moneyAmount;

    public Account() {
    }

    public Account(User user, BigDecimal moneyAmount) {
        this.user = user;
        this.moneyAmount = moneyAmount;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + user +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
