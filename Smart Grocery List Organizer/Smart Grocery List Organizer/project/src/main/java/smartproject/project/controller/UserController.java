package smartproject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import smartproject.project.entities.User;
import smartproject.project.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    // ✅ Get User by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = service.getUserByEmail(email);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ✅ Update User by Name
    @PutMapping("/updateByName/{username}")
    public ResponseEntity<Void> updateUserByName(@PathVariable String username, @RequestBody User updatedUser) {
        boolean isUpdated = service.updateUserByName(username, updatedUser.getEmail(), updatedUser.getPassword());
        return isUpdated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ✅ Delete User by Password
    @DeleteMapping("/deleteByPassword/{password}")
    public ResponseEntity<Void> deleteUserByPassword(@PathVariable String password) {
        boolean isDeleted = service.deleteUserByPassword(password);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/userpost")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUsers(user), HttpStatus.CREATED);
    }

    @PostMapping("/listpostUser")
    public ResponseEntity<List<User>> addUserList(@RequestBody List<User> userList) {
        List<User> savedUsers = service.addUserArray(userList.toArray(new User[0]));
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = service.getUserById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<User>> sortUsers(@PathVariable String field) {
        List<User> sortedUsers = service.sort(field);
        return new ResponseEntity<>(sortedUsers, HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pagesize}")
    public ResponseEntity<List<User>> getUsersPaginated(@PathVariable int offset, @PathVariable int pagesize) {
        List<User> paginatedUsers = service.page(pagesize, offset);
        return new ResponseEntity<>(paginatedUsers, HttpStatus.OK);
    }

    @GetMapping("/page/{offset}/{pagesize}/sort/{field}")
    public ResponseEntity<List<User>> getUsersPaginatedSorted(
            @PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        List<User> sortedPaginatedUsers = service.pagesort(pagesize, offset, field);
        return new ResponseEntity<>(sortedPaginatedUsers, HttpStatus.OK);
    }

    @PutMapping("/putuserbyid/{id}")
    public ResponseEntity<User> editUser(@PathVariable int id, @RequestBody User updateUser) {
        User updatedUser = service.editUser(id, updateUser.getUsername(), updateUser.getEmail(), updateUser.getPassword());
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deluserby/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean isDeleted = service.deleteUser(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
