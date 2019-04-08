package pl.coderslab.author;

import pl.coderslab.Service;

import java.util.List;

public interface AuthorService extends Service<Author> {
    List<Author> getList();

    Author getById(long id);

    Author addNew(Author author);

    Author modify(Author author, Long id);

    void delete(long id);
}
