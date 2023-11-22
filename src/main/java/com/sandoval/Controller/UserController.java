package com.sandoval.Controller;

import com.sandoval.Entity.Users;
import com.sandoval.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Aquí se crea el usuario
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Aquí se obtiene el usuario por su id
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable int userId) {
        Users user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Aquí se actualiza el usuario
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable int userId, @RequestBody Users user) {
        Users updatedUser = userService.updateUser(userId, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Aquí se elimina el usuario
    @DeleteMapping("/{userId}")
    public ResponseEntity<Users> deleteUser(@PathVariable int userId) {
        Users deletedUser = userService.deleteUser(userId);
        if (deletedUser != null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Aquí se obtienen todos los usuarios
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}