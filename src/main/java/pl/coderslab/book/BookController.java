package pl.coderslab.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    private MemoryBookService memoryBookService;

    @Autowired
    BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
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
        return memoryBookService.getList();
    }

    @GetMapping("/{id}")
    Book bookById(@PathVariable long id) {
        return memoryBookService.getBookById(id);
    }

    @PostMapping("")
    Book newBook(@RequestBody Book book) {
        return memoryBookService.addNewBook(book);
    }

    @PutMapping("/{id}")
    Book bookById(@PathVariable long id,
                  @RequestBody Book book) {
        return memoryBookService.modifyBook(book, id); //TODO zmiania danych
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable long id) {
        memoryBookService.deleteBook(id);
    }
}