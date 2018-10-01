package com.store.dao;

import java.util.List;

import com.store.entities.Book;

public interface BookDao {
	
	List<Book> findAll();
	
	Book findOne(Long id);
	
	Book saveBook(Book book);
	
	List<Book> blurrySearch(String title);
	
	void removeOne(Long id);

}
