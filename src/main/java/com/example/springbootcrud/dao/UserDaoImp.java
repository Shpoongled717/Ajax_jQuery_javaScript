package com.example.springbootcrud.dao;

import com.example.springbootcrud.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select distinct u FROM User u join fetch u.roleSet");
        return query.getResultList();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User where id=:id").setParameter("id", id).executeUpdate();
    }

    @Transactional
    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        User user = (User) sessionFactory.getCurrentSession().createQuery("FROM User u join fetch u.roleSet where u.id=:id").setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        User user = (User) sessionFactory.getCurrentSession().createQuery("FROM User u join fetch u.roleSet where u.username=:name").setParameter("name", username).getSingleResult();
        return user;
    }
}
