package pl.coderslab.book;

import java.util.List;

public interface BookService {
    List<Book> getList();
    void setList(List<Book> list);
    Book getBookById(long id);
    Book addNewBook(Book book);
    Book modifyBook(Book book, Long id);
    void deleteBook(long id);
}
