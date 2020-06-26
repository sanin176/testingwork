package com.alex.model.Book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class CategoryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCategory;
    @NotNull
    private String nameCategory;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(mappedBy="categoryBook", fetch = FetchType.EAGER)
    private List<Book> books;
}
