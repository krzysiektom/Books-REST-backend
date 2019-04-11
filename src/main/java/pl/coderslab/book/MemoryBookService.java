package pl.coderslab.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class MemoryBookService implements BookService {
    private List<Book> list = new ArrayList<>();

    MemoryBookService() {
        list.add(new Book(1L, "9788324631766", "Thinking in Java",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                 "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Helion", "programming"));
    }

    @Override
    public List<Book> getList() {
        return list;
    }



    @Override
    public Book getById(long id) {
        return list.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(new Book()); //TODO zgłosić wyjątek
    }

    @Override
    public Book addNew(Book book) {
        list.add(book);
        return book;
    }

    @Override
    public Book modify(Book book, Long id) {
        Book originalBook = getById(id);
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
        return getById(book.getId());
    }

    @Override
    public void delete(long id) {
        list.remove(getById(id));
    }
}