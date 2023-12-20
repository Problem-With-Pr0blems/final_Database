package com22.databasefinalproject.dao;

import com22.databasefinalproject.config.ConnectionConfig;
import com22.databasefinalproject.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDao {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS authors (
                    author_id SERIAL PRIMARY KEY,
                    author_full_name VARCHAR(255) NOT NULL
                );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table authors was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTableRelation(){
        String sql = """
                CREATE TABLE IF NOT EXISTS Author_Book (
                    author_id INTEGER REFERENCES author(author_id),
                    book_id INTEGER REFERENCES book(book_id),
                    PRIMARY KEY (author_id, book_id)
                );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table Author_Book was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAuthor(Author author) {
        String sql =
                "INSERT  INTO author (author_full_name) VALUES (?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, author.getFullName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("author was added");
    }

    public void updateAuthor(Long id, Author author) {
        String sql = "UPDATE author " +
                "SET    author_full_name = ?" +
                "where author_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, author.getFullName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("author was updated");
    }

    public void deleteAuthor(Long id) {
        String sql = "DELETE FROM author where author_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("author was deleted");
    }

    public List<Author> selectAuthors() {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                authorList.add(new Author(
                                resultSet.getLong("author_id"),
                                resultSet.getString("author_full_name")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return authorList;
    }

    public void insertRelation(Long authorId, Long bookId) {
        String sql =
                "INSERT  INTO author_book (author_id, book_id) VALUES (?, ?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, authorId);
            preparedStatement.setLong(2, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("relation between author and book was added");
    }

    public void getTopAuthor() {
        String sql = """
                    select a.author_full_name, count(o.order_id) as total_sales
                        from author a
                    join author_book ab on a.author_id = ab.author_id
                    join book b on ab.book_id = b.book_id
                    join book_order bo on b.book_id = bo.book_id
                    join orders o on bo.order_id = o.order_id
                    group by a.author_full_name
                    order by total_sales desc
                    limit 1
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("author_full_name") + " - ");
                System.out.println(resultSet.getString("total_sales"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
