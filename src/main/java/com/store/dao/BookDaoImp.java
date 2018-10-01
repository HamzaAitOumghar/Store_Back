package com.store.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Book;

@Service
public class BookDaoImp implements BookDao{

	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> findAll() {
		List<Book> books=(List<Book>)this.bookRepo.findAll();

		List<Book> activeBooks=new ArrayList<>();

		for(Book b : books) {
			if(b.isActive()) {
				activeBooks.add(b);
			}
		}
		return activeBooks;
	}

	@Override
	public Book findOne(Long id) {
		// TODO Auto-generated method stub
		return this.bookRepo.getOne(id);
	}

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return this.bookRepo.save(book);
	}

	@Override
	public List<Book> blurrySearch(String title) {
		List<Book> books=(List<Book>)this.bookRepo.findByTitleContaining(title);

		List<Book> activeBooks=new ArrayList<>();
		for(Book b : books) {
			if(b.isActive()) {
				activeBooks.add(b);
			}
		}
		return activeBooks;
 

	}

	@Override
	public void removeOne(Long id) {
		this.bookRepo.deleteById(id);		
	}


}
