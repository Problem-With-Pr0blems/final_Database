package com22.databasefinalproject.service;

import com22.databasefinalproject.dao.CustomerDao;
import com22.databasefinalproject.entity.Customer;

public class CustomerService {

    CustomerDao customerDao = new CustomerDao();

    public void createTable(){
        customerDao.createTable();
    }

    public void insertCustomer(Customer customer) {
        customerDao.insertCustomer(customer);
    }

    public void updateCustomer(Long id, Customer customer) {
        customerDao.updateCustomer(id, customer);
    }

    public void deleteCustomer(Long id) {
        customerDao.deleteCustomer(id);
    }

    public void selectCustomers() {
        for(Customer customer : customerDao.selectCustomers()){
            System.out.println(customer);
        }

    }

    public void getAllCustomersWithPurchase() {
        customerDao.getAllCustomers();
    }
}
