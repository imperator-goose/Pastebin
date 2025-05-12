package com.ruslan.pastebin.Pastebin.service;

import com.ruslan.pastebin.Pastebin.dao.CommentDAO;
import com.ruslan.pastebin.Pastebin.dao.PostDAO;
import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.Comment;
import com.ruslan.pastebin.Pastebin.entity.Post;
import com.ruslan.pastebin.Pastebin.entity.Role;
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
public class CommentService {
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;

    public CommentService(CommentDAO commentDAO, UserDAO userDAO) {
        this.commentDAO = commentDAO;
        this.userDAO = userDAO;
    }

    public Optional<Comment> getById(Long id) {
        return commentDAO.findById(id);
    }

    public List<Comment> getAll() {
        return commentDAO.findAll();
    }

    public Comment save(Comment comment) throws Exception {
        User currentUser = getCurrentUser();
        if(comment.getId() == null){
            comment.setAuthor(currentUser);
            return commentDAO.save(comment);
        }else{
            if (!comment.getAuthor().getId().equals(currentUser.getId())) {
                throw new AccessDeniedException("You can only update your own comments");
            }

            comment.setText(comment.getText());
            return commentDAO.save(comment);
        }
    }

    public void deleteById(Long id) {
        User currentUser = getCurrentUser();
        Comment comment = commentDAO.findById(id).get();
        if (!comment.getAuthor().getId().equals(currentUser.getId())
                && !currentUser.getRole().equals(Role.ROLE_ADMIN.name())) {
            throw new AccessDeniedException("You don't have permission to delete this post");
        }else{
            commentDAO.block(id);
        }
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userDAO.findByUsername(username);
    }
}
