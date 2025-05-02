package com.ruslan.pastebin.Pastebin.service;

import com.ruslan.pastebin.Pastebin.dao.UserDAO;
import com.ruslan.pastebin.Pastebin.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User update(User updateRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = getCurrentUser(authentication);


        if (updateRequest.getUsername() != null && !updateRequest.getUsername().isBlank()) {
            currentUser.setUsername(updateRequest.getUsername());
        }

        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isBlank()) {
            currentUser.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }




        User updatedUser = userDAO.save(currentUser);


        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                updatedUser,                       // Обновленный пользователь
                updatedUser.getPassword(),         // Новый пароль (если менялся)
                authentication.getAuthorities()    // Роли/права
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return updatedUser;
    }


    public Optional<User> getById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }
    public List<User> getAllActive() {
        return userDAO.findAllActive();
    }
    public void hardDeleteById(Long id) {
        userDAO.deleteById(id);
    }

    public void deleteById(Long id) {
        userDAO.block(id);
    }
    @Transactional
    public void deleteCurrentUser() {
        // Получаем текущего авторизованного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = getCurrentUser(authentication);


        // Удаляем пользователя
        userDAO.block(currentUser.getId());

        // Очищаем контекст безопасности
        SecurityContextHolder.clearContext();
    }

    public User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return userDAO.findByUsername(username);
    }
}
