package com.ruslan.pastebin.Pastebin.dao.database;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.status = 1", User.class)
                .getResultList();
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        User existing = entityManager.find(User.class, user.getId());
        if (existing == null) {
            return null;
        }

        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        return entityManager.merge(existing);
    }

    @Override
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setStatus(0);
        }
    }
}
