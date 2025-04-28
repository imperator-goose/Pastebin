package com.ruslan.pastebin.Pastebin.dao.database;

import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class PostDAOImpl implements PostDAO {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Post> getAll() {
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.status = 1", Post.class)
                .getResultList();
    }

    @Override
    public Post save(Post post) {
        entityManager.persist(post);
        return post;
    }

    @Override
    public Post update(Post post) {
        Post existing = entityManager.find(Post.class, post.getId());
        if (existing == null) {
            return null;
        }

        existing.setTitle(post.getTitle());
        existing.setContent(post.getContent());

        return entityManager.merge(existing);
    }

    @Override
    public Post getById(Integer id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Post post = entityManager.find(Post.class, id);
        if (post != null) {
            post.setStatus(0);
        }
    }
}
