package com22.databasefinalproject.service;

import com22.databasefinalproject.dao.OrderDao;
import com22.databasefinalproject.entity.Order;

public class OrderService {

    OrderDao orderDao = new OrderDao();

    public void createTable(){
        orderDao.createTable();
    }

    public void createRelationTable(){
        orderDao.createTableRelation();
    }

    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

    public void updateOrder(Long id, Order order) {
        orderDao.updateOrder(id, order);
    }

    public void deleteOrder(Long id) {
        orderDao.deleteOrder(id);
    }

    public void selectOrders() {
        for(Order order : orderDao.selectOrders()){
            System.out.println(order);
        }
    }

    public void insertRelation(Long bookId, Long orderId) {
        orderDao.insertRelation(bookId, orderId);
    }
}