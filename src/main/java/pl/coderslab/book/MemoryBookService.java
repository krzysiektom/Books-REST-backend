package pl.coderslab.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class MemoryBookService implements BookService {
    private List<Book> list = new ArrayList<>();

    MemoryBookService() {
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    @Override
    public List<Book> getList() {
        return list;
    }

    @Override
    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public Book getBookById(long id) {
        return list.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(new Book()); //TODO zamienić na wyjątek
    }

    @Override
    public Book addNewBook(Book book) {
        list.add(book);
        return book;
    }

    @Override
    public Book modifyBook(Book book, Long id) {
        Book originalBook = getBookById(id);
        if (!book.getAuthor().isEmpty()) {
            originalBook.setAuthor(book.getAuthor());
        }
        if (!book.getIsbn().isEmpty()) {
            originalBook.setIsbn(book.getIsbn());
        }
        if (!book.getPublisher().isEmpty()) {
            originalBook.setPublisher(book.getPublisher());
        }
        if (!book.getTitle().isEmpty()) {
            originalBook.setTitle(book.getTitle());
        }
        if (!book.getType().isEmpty()) {
            originalBook.setType(book.getType());
        }
        return getBookById(book.getId());
    }

    @Override
    public void deleteBook(long id) {
        list.remove(getBookById(id));
    }
}