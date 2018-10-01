package com.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entities.Book;
import com.store.security.User;

public interface BookRepository extends JpaRepository<Book, Long> {

	
	List<Book> findByTitleContaining(String tite) ;
}
