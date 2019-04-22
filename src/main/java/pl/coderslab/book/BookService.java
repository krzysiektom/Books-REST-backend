package pl.coderslab.book;

import java.util.List;

public interface BookService {
    List<Book> getList();

    Book getById(long id);

    Book addNew(Book book);

    Book modify(Book book, Long id);

    void delete(long id);
}
