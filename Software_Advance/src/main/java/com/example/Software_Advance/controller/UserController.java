/*package com.example.Software_Advance.controller;
import ch.qos.logback.classic.Logger;
import com.example.Software_Advance.dto.CreateUserRequestDTO;
import com.example.Software_Advance.models.Enums.userType;
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
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDTO requestDTO) {
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
    public ResponseEntity<?> getUserByType(@PathVariable userType type){
        List <User> users =userService.getUserByType(type);
        if(users.isEmpty())
        {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There are no users with this Type "+ type+ ".");
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(value.toString())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User with ID " + id + " was not found."));
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody CreateUserRequestDTO requestDTO) {
        try {
            User updatedUser = userService.updateUser(id, requestDTO.getUser());
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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




}*/

package com.example.Software_Advance.controller;
import ch.qos.logback.classic.Logger;
import com.example.Software_Advance.dto.CreateUserRequestDTO;
import com.example.Software_Advance.models.Enums.UserType;
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
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDTO requestDTO) {
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
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + id+ " was not found.");
        }
        else{
            return ResponseEntity.ok(user.get().toString());       }
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