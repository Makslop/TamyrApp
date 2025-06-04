package com.example.tamyrv1.service;

import com.example.tamyrv1.dto.UserDto;
import com.example.tamyrv1.model.User;
import com.example.tamyrv1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public boolean existsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        System.out.println("Ищем email: " + email);
        System.out.println("Результат запроса findByEmail: " + user);

        return user.map(u -> true).orElse(false);
    }

    @Override
    public List<Long> getAllUserIds() {
        return userRepository.findAll().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " does not exist."));
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new UserDto(user.getEmail());
        }
        return null;
    }
}
