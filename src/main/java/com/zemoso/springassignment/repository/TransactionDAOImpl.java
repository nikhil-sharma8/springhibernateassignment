package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionDAO{

    private final SessionFactory sessionFactory;

    public TransactionDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Transaction> getAllTransactions() {
        Session session = sessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("from Transaction order by transactionDate", Transaction.class);
        return query.getResultList();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }

    @Override
    public void deleteTransaction(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Transaction where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
