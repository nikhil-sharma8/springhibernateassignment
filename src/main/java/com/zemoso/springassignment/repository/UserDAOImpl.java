package com.zemoso.springassignment.repository;

import com.zemoso.springassignment.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    SessionFactory sessionFactory;

    UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User order by username", User.class);

        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from User where id = :id");

        query.setParameter("id", id);

        query.executeUpdate();
    }
}
