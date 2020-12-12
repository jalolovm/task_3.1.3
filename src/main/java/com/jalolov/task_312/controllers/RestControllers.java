package com.jalolov.task_312.controllers;

import com.jalolov.task_312.model.User;
import com.jalolov.task_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rest")
public class RestControllers {

    private final UserService userService;

    @Autowired
    public RestControllers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) throws Exception {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        final List<User> clients = userService.getAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        final User user = userService.getById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete (@RequestBody User user){
        userService.delete(user.getId());
    return new ResponseEntity<>(HttpStatus.OK);
    }

}
