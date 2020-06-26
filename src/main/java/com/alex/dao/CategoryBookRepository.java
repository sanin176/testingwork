package com.alex.dao;

import com.alex.model.Book.CategoryBook;
import org.springframework.data.repository.CrudRepository;

public interface CategoryBookRepository extends CrudRepository<CategoryBook, Integer> {

}
