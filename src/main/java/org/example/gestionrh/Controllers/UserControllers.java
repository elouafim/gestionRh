package org.example.gestionrh.Controllers;

import org.example.gestionrh.Entites.DTO.UserCreateDTO;
import org.example.gestionrh.Entites.DTO.UserDto;
import org.example.gestionrh.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllers {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO user) {
        try {
            UserDto createdUser = userService.createUser(user);
            if (createdUser != null) {
                return ResponseEntity.ok(createdUser);
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        try {

            UserDto updatedUser = userService.UpdteUser(user);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {

            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {

            UserDto user = userService.getUser(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Page<UserDto> users = userService.getAllUsers(page,size);
        return users.isEmpty()!=true ? ResponseEntity.ok(users):ResponseEntity.notFound().build();
    }

    @GetMapping("/by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        UserDto user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

