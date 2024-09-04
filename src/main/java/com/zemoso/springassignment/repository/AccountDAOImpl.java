package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private final SessionFactory sessionFactory;

    public AccountDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Account> getAllAccounts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account order by accountNumber", Account.class);
        return query.getResultList();
    }

    @Override
    public void saveAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(account);
    }

    @Override
    public Account getAccountById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    @Override
    public void deleteAccount(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Account where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
