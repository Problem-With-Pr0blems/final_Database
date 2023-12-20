package com22.databasefinalproject.api;

import com22.databasefinalproject.entity.Author;
import com22.databasefinalproject.entity.Book;
import com22.databasefinalproject.entity.Customer;
import com22.databasefinalproject.entity.Order;
import com22.databasefinalproject.service.AuthorService;
import com22.databasefinalproject.service.BookService;
import com22.databasefinalproject.service.CustomerService;
import com22.databasefinalproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class Controller {
    AuthorService authorService = new AuthorService();
    BookService bookService = new BookService();
    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();

    @PostMapping("/createAuthorTable")
    @Operation(summary = "create table author")
    public void createAuthorTable() {
        authorService.createTable();
    }

    @PostMapping("/createBookTable")
    @Operation(summary = "create table book")
    public void createBookTable() {
        bookService.createTable();
    }

    @PostMapping("/createCustomerTable")
    @Operation(summary = "create table customer")
    public void createCustomerTable() {
        customerService.createTable();
    }

    @PostMapping("/createOrderTable")
    @Operation(summary = "create table order")
    public void createOrderTable() {
        orderService.createTable();
    }

    @PostMapping("/createRelationTableBookOrder")
    @Operation(summary = "create book order relation table")
    public void createRelationTableBookOrder() {
        orderService.createRelationTable();
    }

    @PostMapping("/createAuthorBookTable")
    @Operation(summary = "create author book relation table")
    public void createRelationTableAuthorBook() {
        authorService.createRelationTable();
    }

    //============================================================

    @PostMapping("/insertAuthor")
    @Operation(summary = "insert author")
    public void insertAuthor(@RequestBody Author author) {
        authorService.insertAuthor(author);
    }

    @PostMapping("/insertBook")
    @Operation(summary = "insert book")
    public void insertBook(@RequestBody Book book) {
        bookService.insertBook(book);
    }

    @PostMapping("/insertCustomer")
    @Operation(summary = "insert customer")
    public void insertCustomer(@RequestBody Customer customer) {
        customerService.insertCustomer(customer);
    }

    @PostMapping("/insertOrder")
    @Operation(summary = "insert order")
    public void insertAuthor(@RequestBody Order order) {
        orderService.insertOrder(order);
    }

    @PostMapping("/insertAuthorBook")
    @Operation(summary = "insert relation between author and book tables")
    public void insertRelationAuthorBook(@RequestParam Long authorId, @RequestParam Long bookId) {
        authorService.insertRelation(authorId, bookId);
    }

    @PostMapping("/insertBookOrder")
    @Operation(summary = "insert relation between book and order tables")
    public void insertRelationBookOrder(@RequestParam Long bookId, @RequestParam Long orderId) {
        orderService.insertRelation(bookId, orderId);
    }


    // ========================================================

    @PutMapping("/updateAuthor")
    @Operation(summary = "update author")
    public void updateAuthor(@RequestParam Long id, @RequestBody Author author) {

        authorService.updateAuthor(id, author);
    }

    @PutMapping("/updateBook")
    @Operation(summary = "update book")
    public void updateBook(@RequestParam Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @PutMapping("/updateCustomer")
    @Operation(summary = "update customer")
    public void updateCustomer(@RequestParam Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
    }

    @PutMapping("/updateOrder")
    @Operation(summary = "update order")
    public void updateOrder(@RequestParam Long id, @RequestBody Order order) {
        orderService.updateOrder(id, order);
    }

    //===============================================================

    @DeleteMapping("/deleteAuthor")
    @Operation(summary = "delete author")
    public void deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
    }

    @DeleteMapping("/deleteBook")
    @Operation(summary = "delete book")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/deleteCustomer")
    @Operation(summary = "delete customer")
    public void deleteCustomer(@RequestParam Long id) {
        customerService.deleteCustomer(id);
    }

    @DeleteMapping("/deleteOrder")
    @Operation(summary = "delete order")
    public void deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
    }

    //============================================================

    @GetMapping("/getAuthors")
    @Operation(summary = "get all authors")
    public void selectAuthors(){
        authorService.selectAuthors();
    }

    @GetMapping("/getBooks")
    @Operation(summary = "get all books")
    public void selectBooks(){
        bookService.selectBooks();
    }

    @GetMapping("/getCustomers")
    @Operation(summary = "get all customers")
    public void selectCustomers(){
        customerService.selectCustomers();
    }

    @GetMapping("/getOrders")
    @Operation(summary = "get all orders")
    public void selectOrders(){
        orderService.selectOrders();
    }
}
