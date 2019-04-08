package pl.coderslab.book;

import pl.coderslab.Service;

import java.util.List;

public interface BookService extends Service<Book> {
    List<Book> getList();

    Book getById(long id);

    Book addNew(Book book);

    Book modify(Book book, Long id);

    void delete(long id);
}
