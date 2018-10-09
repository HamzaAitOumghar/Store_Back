package com.store.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.resource.HttpResource;

import com.store.dao.BookDao;
import com.store.entities.Book;

@RestController
@RequestMapping("/book")
public class BookRestService {


	@Autowired
	private BookDao bookRepo;

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Book addBook(@RequestBody Book book) {
		System.out.println(book);
		return this.bookRepo.saveBook(book);
	}

	@RequestMapping(value="/add/image",method=RequestMethod.POST)
	public ResponseEntity upload(@RequestParam("id") Long id,HttpServletRequest request,HttpServletResponse response) {

		try {
			Book book = this.bookRepo.findOne(id);
			MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
			Iterator<String> iterator=multipartRequest.getFileNames();
			MultipartFile file =multipartRequest.getFile(iterator.next());
			String fileName=id+".png";

			byte[] bytes=file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();


			return new ResponseEntity("upload successefuly",HttpStatus.OK);



		} catch(Exception e) {

			System.out.println(e.getMessage());
			return new ResponseEntity("upload failed",HttpStatus.BAD_REQUEST);

		}


	}
	
	@RequestMapping(value="/update/image",method=RequestMethod.POST)
	public ResponseEntity updateImage(@RequestParam("id") Long id,HttpServletRequest request,HttpServletResponse response) {

		try {
			Book book = this.bookRepo.findOne(id);
			MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
			Iterator<String> iterator=multipartRequest.getFileNames();
			MultipartFile file =multipartRequest.getFile(iterator.next());
			String fileName=id+".png";

			Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
			
			byte[] bytes=file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();


			return new ResponseEntity("upload successefuly",HttpStatus.OK);



		} catch(Exception e) {

			System.out.println(e.getMessage());
			return new ResponseEntity("upload failed",HttpStatus.BAD_REQUEST);

		}


	}
	
	
	@RequestMapping("/all")
	public List<Book> getAllBook(){
		return this.bookRepo.findAll();
	}

	@RequestMapping("/{id}")
	public Book getBookById(@PathVariable("id") Long  id){
		return this.bookRepo.findOne(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Book updateBook(@RequestBody Book book) {
		System.out.println(book.toString());
		return this.bookRepo.saveBook(book);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public Long deleteBook(@PathVariable("id") Long id) throws IOException {
		
		this.bookRepo.removeOne(id);
		String fileName=id+".png";
		Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
		return id;
	}
	
	

}
