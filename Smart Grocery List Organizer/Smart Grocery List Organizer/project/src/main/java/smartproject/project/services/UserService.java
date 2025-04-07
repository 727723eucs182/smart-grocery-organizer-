package smartproject.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import smartproject.project.entities.User;
import smartproject.project.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean updateUserByName(String username, String email, String password) {
        int rowsUpdated = userRepository.updateUserByName(username, email, password);
        return rowsUpdated > 0;
    }

    public boolean deleteUserByPassword(String password) {
        int rowsDeleted = userRepository.deleteByPassword(password);
        return rowsDeleted > 0;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User addUsers(User user) {
        return userRepository.save(user);
    }

    public List<User> addUserArray(User[] users) {
        return userRepository.saveAll(List.of(users));
    }

    public User editUser(int userId, String userName, String email, String password) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(userName);
            existingUser.setEmail(email);
            existingUser.setPassword(password);
            return userRepository.save(existingUser);
        }
        return null;
    }

    public boolean deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return userRepository.findAll(sort);
    }

    public List<User> page(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable).getContent();
    }

    public List<User> pagesort(int pageSize, int pageNumber, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field));
        return userRepository.findAll(pageable).getContent();
    }
}
