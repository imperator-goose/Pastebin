package com.ruslan.pastebin.Pastebin.dao.database;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    public EntityManager entityManager;

    @Override
    public List<Comment> getAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Comment> comments = session.createQuery("from Comment", Comment.class).getResultList();
        return comments;
    }

    @Override
    public void save(Comment comment) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(comment);
    }

    @Override
    public void update(Comment object) {

    }

    @Override
    public Comment getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
