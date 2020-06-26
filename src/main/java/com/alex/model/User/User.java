package com.alex.model.User;

import com.alex.model.Book.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Email(message = "Email should be valid")
	@NotNull
	private String username;
	@NotNull
	private String password;
	private LocalDate createdAt;
	private LocalDate updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idRole")
	private Role roleId;

	//	@Size(max=4, message = "List is already complete")
	@ManyToMany
	@JoinTable(name = "users_books",
			//foreign key for CarsEntity in employee_car table
			joinColumns = @JoinColumn(name = "user_id"),
			//foreign key for other side - EmployeeEntity in employee_car table
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	private Set<Book> bookList = new HashSet<>();

}