package pl.coderslab.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DbAuthorService implements AuthorService {
    private AuthorDao authorDao;

    @Autowired
    public DbAuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getList() {
        return authorDao.findAll();
    }

    @Override
    public Author getById(long id) {
        return authorDao.findById(id);
    }

    @Override
    public Author addNew(Author author) {
        authorDao.save(author);
        return author;
    }

    @Override
    public Author modify(Author author, Long id) {
        Author originalAuthor = getById(id);
        if(!author.getFirstName().isEmpty()){
            originalAuthor.setFirstName(author.getFirstName());
        }
        if(!author.getLastName().isEmpty()){
            originalAuthor.setLastName(author.getLastName());
        }
        authorDao.save(originalAuthor);
        return originalAuthor;
    }

    @Override
    public void delete(long id) {
        authorDao.remove(id);
    }
}
