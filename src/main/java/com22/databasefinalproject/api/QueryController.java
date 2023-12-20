package com22.databasefinalproject.api;

import com22.databasefinalproject.service.AuthorService;
import com22.databasefinalproject.service.BookService;
import com22.databasefinalproject.service.CustomerService;
import com22.databasefinalproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query/")
public class QueryController {
    AuthorService authorService = new AuthorService();
    BookService bookService = new BookService();
    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();

    @GetMapping("/getAllBooksWithAuthors")
    @Operation(summary = "Retrieve a list of books with their authors.")
    public void getAllBooks() {
        bookService.getAllBooks();
    }

    @GetMapping("/getAllOrdersForBook")
    @Operation(summary = "Find the total sales for a specific book.")
    public void getAllOrdersForBook(@RequestParam Long id) {
        bookService.getAllOrdersForBook(id);
    }

    @GetMapping("/getAllCustomersWithPurchase")
    @Operation(summary = "List customers who made more than one purchase.")
    public void getAllOrdersForBook() {
        customerService.getAllCustomersWithPurchase();
    }

    @GetMapping("/getTopBooks")
    @Operation(summary = "Find the top N best-selling books.")
    public void getTopBooks(Long n) {
        bookService.getTopBooks(n);
    }

    @GetMapping("/getTopAuthor")
    @Operation(summary = "Identify authors with the highest total sales.")
    public void getTopBooks() {
        authorService.getTopAuthor();
    }
}
