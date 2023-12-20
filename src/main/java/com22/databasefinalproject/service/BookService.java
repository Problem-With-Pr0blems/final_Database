package com22.databasefinalproject.service;

import com22.databasefinalproject.dao.BookDao;
import com22.databasefinalproject.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookService {

    BookDao bookDao = new BookDao();

    public void createTable(){
        bookDao.createTable();
    }

    public void insertBook(Book book) {
        bookDao.insertBook(book);
    }

    public void updateBook(Long id, Book book) {
        bookDao.updateBook(id, book);
    }

    public void deleteBook(Long id) {
        bookDao.deleteBook(id);
    }

    public void selectBooks() {
        for(Book book : bookDao.selectBooks()){
            System.out.println(book);
        }
    }

    public void getAllBooks() {
        bookDao.getAllBooks();
    }

    public void getAllOrdersForBook(Long id) {
        bookDao.getAllOrdersForBook(id);
    }

    public void getTopBooks(Long n) {
        bookDao.getTopBooks(n);
    }
}