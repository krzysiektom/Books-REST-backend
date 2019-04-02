package pl.coderslab.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    private BookService bookService;

    @Autowired
    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/hello")
    String hello() {
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    List<Book> books() {
        return bookService.getList();
    }

    @GetMapping("/{id}")
    Book bookById(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("")
    Book newBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @PutMapping("/{id}")
    Book bookById(@PathVariable long id,
                  @RequestBody Book book) {
        return bookService.modifyBook(book, id);
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }
}