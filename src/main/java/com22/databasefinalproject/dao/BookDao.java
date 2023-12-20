package com22.databasefinalproject.dao;

import com22.databasefinalproject.config.ConnectionConfig;
import com22.databasefinalproject.entity.Author;
import com22.databasefinalproject.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS books (
                    book_id SERIAL PRIMARY KEY,
                    book_title VARCHAR(255) NOT NULL
                );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table books was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertBook(Book book) {
        String sql =
                "INSERT  INTO book (book_title) VALUES (?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, book.getBookTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("book was added");
    }

    public void updateBook(Long id, Book book) {
        String sql = "UPDATE book " +
                "SET    book_title = ?" +
                "where book_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, book.getBookTitle());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("book was updated");
    }

    public void deleteBook(Long id) {
        String sql = "DELETE FROM book where book_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("book was deleted");
    }

    public List<Book> selectBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bookList.add(new Book(
                                resultSet.getLong("book_id"),
                                resultSet.getString("book_title")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    public void getAllBooks() {
        String sql = """
                    select b.book_title, a.author_full_name
                           from book b
                    left join author_book ab on b.book_id = AB.book_id
                    left join author a on ab.author_id = a.author_id
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("book_title") + " - ");
                System.out.println(resultSet.getString("author_full_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllOrdersForBook(Long id) {
        String sql = """
                   select b.book_title, count(bo.order_id) as total_sales
                       from book b
                   join book_order bo on b.book_id = bo.book_id
                   where b.book_id = ?
                   group by b.book_title
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("book_title") + " - ");
                System.out.println(resultSet.getString("total_sales"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getTopBooks(Long n) {
        String sql = """
                   select b.book_title, count(o.order_id) as circulation
                       from book b
                   join book_order bo on b.book_id = bo.book_id
                   join orders o on bo.order_id = o.order_id
                   group by b.book_title
                   order by circulation desc
                   limit ?
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, n);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("book_title") + " - ");
                System.out.println(resultSet.getString("circulation"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
