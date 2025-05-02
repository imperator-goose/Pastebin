package com.ruslan.pastebin.Pastebin.service;

import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostDAO postDAO;
    private final UserDAO userDAO;

    public PostService(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public Optional<Post> getById(Long id) {
        return postDAO.findById(id);
    }

    public List<Post> getAll() {
        return postDAO.findAll();
    }

    public Post save(Post post) throws Exception {
        User currentUser = getCurrentUser();
        if(post.getId() == null){
            post.setAuthor(currentUser);
            return postDAO.save(post);
        }else{
            // Проверяем, что текущий пользователь - автор поста
            if (!post.getAuthor().getId().equals(currentUser.getId())) {
                throw new AccessDeniedException("You can only update your own posts");
            }

            // Обновляем поля
            post.setTitle(post.getTitle());
            post.setContent(post.getContent());

            return postDAO.save(post);
        }
    }
    public void hardDeleteById(Long id) {
        postDAO.deleteById(id);
    }
    public void deleteById(Long id) {
        User currentUser = getCurrentUser();
        Post post = postDAO.findById(id).get();
        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You can only delete your own posts");
        }else {
            postDAO.block(id);
        }
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userDAO.findByUsername(username);
    }
}
