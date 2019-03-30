package pl.coderslab.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class MemoryBookService {
    private List<Book> list = new ArrayList<>();

    MemoryBookService() {
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    List<Book> getList() {
        return list;
    }

    void setList(List<Book> list) {
        this.list = list;
    }

    Book getBookById(long id) {
        return list.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(new Book()); //TODO zamienić na wyjątek
    }

    Book addNewBook(Book book) {
        list.add(book);
        return book;
    }

    void deleteBook(long id) {
        list.remove(getBookById(id));
    }
}