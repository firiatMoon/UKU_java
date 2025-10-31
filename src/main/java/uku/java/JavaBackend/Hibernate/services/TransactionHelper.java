package uku.java.JavaBackend.Hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHelper {
    private final SessionFactory sessionFactory;


    public TransactionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void executeIdTransaction(Consumer<Session> action) {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            action.accept(session);

            session.getTransaction().commit();
        } catch (Exception ex) {
            if (!Objects.isNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw ex;
        }
    }

    public <T> T executeIdTransaction(Function<Session, T> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            T result = action.apply(session);

            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            if (!Objects.isNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw ex;
        }
    }
}

