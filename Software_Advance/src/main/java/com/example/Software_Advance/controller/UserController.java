package com.example.Software_Advance.controller;
import ch.qos.logback.classic.Logger;
import com.example.Software_Advance.dto.CreateUserRequestDto;
import com.example.Software_Advance.dto.OrphanDto;
import com.example.Software_Advance.dto.UserDto;
import com.example.Software_Advance.models.Enums.UserType;
import com.example.Software_Advance.models.Tables.Orphan;
import com.example.Software_Advance.models.Tables.User;
import com.example.Software_Advance.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;
    Logger log;


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDto requestDTO) {
        User savedUser = userService.saveUser(requestDTO);
        return ResponseEntity.ok(savedUser);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found in the database.");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        List<User> users = userService.getUserByName(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There are no users with this name "+ name+ ".");        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/by-type/{type}")
    public ResponseEntity<?> getUserByType(@PathVariable UserType type){
        List <User> users =userService.getUserByType(type);
        if(users.isEmpty())
        {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("There are no users with this Type "+ type+ ".");
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id+ " was not found.");
        }
        else{
            return ResponseEntity.ok(userOptional);       }
    }



    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id + " was not found.");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been successfully deleted.");
    }




}