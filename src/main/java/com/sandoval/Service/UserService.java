package com.sandoval.Service;

import com.sandoval.Entity.Users;
import com.sandoval.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Users saveUser(Users user) {
        // Aquí puedes agregar validaciones, como asegurarte de que el nombre de usuario o el correo no estén duplicados
        return userRepo.save(user);
    }

    public Users getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public List<Users> getAllUsers() {
        return (List<Users>) userRepo.findAll();
    }

    public Users updateUser(int userId, Users user) {
        Users existingUser = userRepo.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setCelNumber(user.getCelNumber());
            return userRepo.save(existingUser);
        } else {
            return null;
        }
    }

    public Users deleteUser(int userId) {
        Users existingUser = userRepo.findById(userId).orElse(null);
        if (existingUser != null) {
            userRepo.delete(existingUser);
            return existingUser;
        } else {
            return null;
        }
    }
}
