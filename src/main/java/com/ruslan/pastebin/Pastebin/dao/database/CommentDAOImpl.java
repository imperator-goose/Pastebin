package com.ruslan.pastebin.Pastebin.dao.database;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
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
public class CommentDAOImpl implements CommentDAO {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Comment> getAll() {
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.status = 1", Comment.class)
                .getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        Comment existing = entityManager.find(Comment.class, comment.getId());
        if (existing == null) {
            return null;
        }

        existing.setText(comment.getText());

        return entityManager.merge(existing);
    }

    @Override
    public Comment getById(Integer id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            comment.setStatus(0);
        }
    }
}
