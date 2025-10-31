package uku.java.JavaBackend.Hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uku.java.JavaBackend.Hibernate.entities.Account;
import uku.java.JavaBackend.Hibernate.entities.User;
import uku.java.JavaBackend.Hibernate.exceptions.UserException;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final TransactionHelper transactionHelper;
    private final AccountService accountService;
    private final SessionFactory sessionFactory;


    public UserService(TransactionHelper transactionHelper,AccountService accountService, SessionFactory sessionFactory) {
        this.transactionHelper = transactionHelper;
        this.accountService = accountService;
        this.sessionFactory = sessionFactory;
    }

    public User getById(Long id){
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public User createNewUser(String login){
       return transactionHelper.executeIdTransaction(session -> {
            User newUser = new User(login);
            Account account = accountService.createNewAccount(newUser);
            session.persist(account);
            session.persist(newUser);
            return newUser;
        });
    }

    public List<User> showAllUsers() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery(
                    """
                    SELECT u FROM User u
                    LEFT JOIN fetch u.accountList a
                    """, User.class)
                    .list();
        }
    }

    public void createAccount(Long userId){
        transactionHelper.executeIdTransaction(session -> {
            User user = getById(userId);

            if(Objects.isNull(user)){
                throw new UserException("User not found");
            }

            Account account = accountService.createAnotherAccount(user);
            session.persist(account);
        });
    }
}
