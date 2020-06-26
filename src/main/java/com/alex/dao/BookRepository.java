package com.alex.dao;

import com.alex.model.Book.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM book b WHERE b.id IN ?1 AND b.amount > 0", nativeQuery = true)
    Set<Book> findBookByArray(Integer[] array);
}
