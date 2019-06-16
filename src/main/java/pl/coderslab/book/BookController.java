package pl.coderslab.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                 "Helion", "programming");
    }

    @GetMapping("/")
    List<Book> books() {
        return bookService.getList();
    }

    @GetMapping("/{id}")
    Book bookById(@PathVariable long id) {
        return bookService.getById(id);
    }

    @PostMapping("/")
    Book newBook(@RequestBody Book book) {
        return bookService.addNew(book);
    }

    @PutMapping("/{id}")
    Book bookById(@PathVariable long id,
                  @RequestBody Book book) {
        return bookService.modify(book, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Book> deleteBook(@PathVariable long id) {
        Book book = bookService.getById(id);
        if(book==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}