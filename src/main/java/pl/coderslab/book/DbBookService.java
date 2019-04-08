package pl.coderslab.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
class DbBookService implements BookService {

    private BookDao bookDao;

    @Autowired
    private DbBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getList() {
        return bookDao.findAll();
    }

    @Override
    public Book getById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public Book addNew(Book book) {
        bookDao.save(book);
        return book;
    }

    @Override
    public Book modify(Book book, Long id) {
        Book originalBook = getById(id);
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
       bookDao.save(originalBook);
        return originalBook;
    }

    @Override
    public void delete(long id) {
        bookDao.remove(id);
    }
}
