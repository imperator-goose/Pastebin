package com.ruslan.pastebin.Pastebin.dao.database;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    public EntityManager entityManager;

    @Override
    public List<User> getAll() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users = session.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public void save(User user) {
        if(user.getId() == 0) {
            Session session = entityManager.unwrap(Session.class);
            session.save(user);
        }
    }

    @Override
    public void update(User user) {
        if(user.getId() != 0) {
            Session session = entityManager.unwrap(Session.class);
            session.update(user);
        }
    }

    @Override
    public User getById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public void deleteById(Integer integer) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, integer);
        user.setStatus(0);
        session.update(user);
    }
}
