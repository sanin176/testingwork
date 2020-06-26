package com.alex.controller;

import com.alex.dao.BookRepository;
import com.alex.model.Book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
public class ControllerBook {

    @Autowired
    private BookRepository booksRepository;

    @RequestMapping(value = {"/createBook"}, method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('Admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        book.setCreatedAt(LocalDate.now());
        book.setUpdatedAt(LocalDate.now());
        booksRepository.save(book);
    }
}
