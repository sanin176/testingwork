package com.alex.controller;

import com.alex.dao.CategoryBookRepository;
import com.alex.model.Book.CategoryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
public class ControllerCategoryBook {

    @Autowired
    private CategoryBookRepository categoryBooksRepository;

    @RequestMapping(value = {"/createCategoryBook"}, method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('Admin')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategoryBooks(@RequestBody CategoryBook categoryBooks) {
        categoryBooks.setCreatedAt(LocalDate.now());
        categoryBooks.setUpdatedAt(LocalDate.now());
        categoryBooksRepository.save(categoryBooks);
    }
}
