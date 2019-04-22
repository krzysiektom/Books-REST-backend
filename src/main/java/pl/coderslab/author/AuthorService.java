package pl.coderslab.author;

import java.util.List;

public interface AuthorService{
    List<Author> getList();

    Author getById(long id);

    Author addNew(Author author);

    Author modify(Author author, Long id);

    void delete(long id);
}
