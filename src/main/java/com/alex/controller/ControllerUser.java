package com.alex.controller;

import com.alex.dao.BookRepository;
import com.alex.dao.RoleRepository;
import com.alex.dao.UserRepository;
import com.alex.model.Book.Book;
import com.alex.model.User.User;
import com.alex.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class ControllerUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = {"/createUser"}, method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('Admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        user.setRoleId(roleRepository.findByRoleName("User"));
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        userDetailsService.save(user);
    }

    @RequestMapping(value = {"/addUserBook"}, method = RequestMethod.POST)
    public ResponseEntity<?> addUserBook(@RequestBody Map<String, Integer[]> object) {
        User user = userRepository.findById(object.get("user")[0]).get();
        Set<Book> books = bookRepository.findBookByArray(object.get("book"));
        int amount = 0;
        for (Book addBook: books) {
            if(user.getBookList().add(addBook)) {
                addBook.setAmount(addBook.getAmount() - 1);
                bookRepository.save(addBook);
                amount++;
            }
        }
        userRepository.save(user);
        return new ResponseEntity("Added " + amount + " from " + object.get("book").length, HttpStatus.OK);
    }

    @RequestMapping(value = {"/deleteUserBook"}, method = RequestMethod.POST)
    public ResponseEntity<?> createUserBook(@RequestBody Map<String, Integer[]> object) {
        User user = userRepository.findById(object.get("user")[0]).get();
        Set<Book> books = bookRepository.findBookByArray(object.get("book"));
        int amount = 0;
        for (Book deleteBook: books) {
            if(user.getBookList().remove(deleteBook)) {
                deleteBook.setAmount(deleteBook.getAmount() + 1);
                bookRepository.save(deleteBook);
                amount++;
            }
        }
        userRepository.save(user);
        return new ResponseEntity("Delete " + amount + " from " + object.get("book").length, HttpStatus.OK);
    }

}
