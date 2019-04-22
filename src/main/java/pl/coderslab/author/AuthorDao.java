package pl.coderslab.author;

import org.springframework.stereotype.Component;
import pl.coderslab.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
class AuthorDao {
    private static AuthorDao instance;
    private final String dbName = "warsztat04";
    private final String URL = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private AuthorDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static AuthorDao getInstance() {
        if (instance == null) {
            instance = new AuthorDao();
        }
        return instance;
    }

    public Author findById(long id) {
        Author author = new Author();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM authors WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                author = new Author(id, firstName, lastName);
                statement = connection.prepareStatement(
                        "SELECT * FROM books WHERE id in(SELECT book_authors.book_id FROM book_authors WHERE author_id=?)");
                statement.setLong(1, author.getId());
                resultSet = statement.executeQuery();
                List<Book> books = new ArrayList<>();
                while (resultSet.next()) {
                    long idBook = resultSet.getLong("id");
                    String isbn = resultSet.getString("isbn");
                    String title = resultSet.getString("title");
                    String publisher = resultSet.getString("publisher");
                    String type = resultSet.getString("type");
                    books.add(new Book(idBook, isbn, title, publisher, type));
                }
                author.setBooks(books);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM authors");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Author author = new Author(id, firstName, lastName);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void save(Author author) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (author.getId() == 0) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO authors (firstName, lastName) VALUES (?,?)");
                statement.setString(1, author.getFirstName());
                statement.setString(2, author.getLastName());
                statement.executeUpdate();
            } else {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE authors SET firstName=?,lastName=? WHERE id=?");
                statement.setString(1, author.getFirstName());
                statement.setString(2, author.getLastName());
                statement.setLong(3, author.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM authors WHERE id=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
