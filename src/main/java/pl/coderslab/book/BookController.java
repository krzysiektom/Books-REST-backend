package pl.coderslab.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    MemoryBookService memoryBookService;

    public BookController(MemoryBookService memoryBookService) {
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
}