package com22.databasefinalproject.service;

import com22.databasefinalproject.dao.AuthorDao;
import com22.databasefinalproject.entity.Author;
import com22.databasefinalproject.entity.Order;

public class AuthorService {

    AuthorDao authorDao = new AuthorDao();

    public void createTable() {
        authorDao.createTable();
    }

    public void createRelationTable(){
        authorDao.createTableRelation();
    }

    public void insertAuthor(Author author) {
        authorDao.insertAuthor(author);
    }

    public void updateAuthor(Long id, Author author) {
        authorDao.updateAuthor(id, author);
    }


    public void deleteAuthor(Long id) {
        authorDao.deleteAuthor(id);
    }

    public void selectAuthors() {
        for (Author author : authorDao.selectAuthors()){
            System.out.println(author.toString());
        }
    }

    public void insertRelation(Long authorId, Long bookId) {
        authorDao.insertRelation(authorId, bookId);
    }

    public void getTopAuthor() {
        authorDao.getTopAuthor();
    }
}
