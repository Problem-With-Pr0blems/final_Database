package com22.databasefinalproject.dao;

import com22.databasefinalproject.config.ConnectionConfig;
import com22.databasefinalproject.entity.Book;
import com22.databasefinalproject.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS customers (
                    customer_id SERIAL PRIMARY KEY,
                    customer_name VARCHAR(255) NOT NULL
                );
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
            System.out.println("table customers was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCustomer(Customer customer) {
        String sql =
                "INSERT  INTO customer (customer_name) VALUES (?)";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("customer was added");
    }

    public void updateCustomer(Long id, Customer customer) {
        String sql = "UPDATE customer " +
                "SET    customer_name = ?" +
                "where customer_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("customer was updated");
    }

    public void deleteCustomer(Long id) {
        String sql = "DELETE FROM customer where customer_id = ?";
        try (
                Connection connection = ConnectionConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("customer was deleted");
    }

    public List<Customer> selectCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                customerList.add(new Customer(
                                resultSet.getLong("customer_id"),
                                resultSet.getString("customer_name")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    public void getAllCustomers() {
        String sql = """
                    select c.customer_name, count(o.order_id) as purchase
                        from customer c
                    join orders o on c.customer_id = o.customer_id
                    group by c.customer_name
                    having count(o.order_id) > 1
                """;
        try (
                Connection connection = ConnectionConfig.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("customer_name") + " - ");
                System.out.println(resultSet.getString("purchase"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

