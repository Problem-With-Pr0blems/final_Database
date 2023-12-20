package com22.databasefinalproject.dao;

import com22.databasefinalproject.config.ConnectionConfig;
import com22.databasefinalproject.entity.Customer;
import com22.databasefinalproject.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS  orders (
                   order_id SERIAL PRIMARY KEY,
                   order_date varchar NOT NULL,
                   customer_id INTEGER REFERENCES Customer(customer_id)
               );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table orders was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTableRelation(){
        String sql = """
                CREATE TABLE IF NOT EXISTS Book_Order (
                   book_id INTEGER REFERENCES book(book_id),
                   order_id INTEGER REFERENCES orders(order_id),
                   PRIMARY KEY (book_id, order_id)
               );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table Book_Order was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrder(Order order) {
        String sql =
                "INSERT  INTO orders (order_date, customer_id) VALUES (?, ?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, order.getOrderDate());
            preparedStatement.setLong(2, order.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("order was added");
    }

    public void updateOrder(Long id, Order order) {
        String sql = "UPDATE orders " +
                "SET    order_date = ?," +
                "           customer_id = ?" +
                "where order_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, order.getOrderDate());
            preparedStatement.setLong(2, order.getCustomerId());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("order was updated");
    }

    public void deleteOrder(Long id) {
        String sql = "DELETE FROM orders where order_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("order was deleted");
    }

    public List<Order>  selectOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                orderList.add(new Order(
                                resultSet.getLong("order_id"),
                                resultSet.getString("order_date"),
                                resultSet.getLong("customer_id")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderList;
    }

    public void insertRelation(Long bookId, Long orderId) {
        String sql =
                "INSERT  INTO book_order (book_id, order_id) VALUES (?, ?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, bookId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("relation between book and order was added");
    }
}
