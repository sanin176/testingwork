package com.alex.dao;

import com.alex.model.User.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);

	@Modifying
	@Transactional
	@Query(value = "SELECT r.role_name FROM user u INNER JOIN role r ON u.id_role = r.id AND u.username = ?1", nativeQuery = true)
	List<String> findUserByUsername(String username);
}