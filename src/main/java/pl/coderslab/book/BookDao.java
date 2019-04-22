package pl.coderslab.book;

import org.springframework.stereotype.Component;
import pl.coderslab.author.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
class BookDao {
    private static BookDao instance;
    private final String dbName = "warsztat04";
    private final String URL = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }
        return instance;
    }

    public void save(Book book) {
        try (Connection conn = DriverManager.getConnection(URL,
                USER, PASSWORD)) {
            if (book.getId() == 0) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO books(isbn, title, publisher, type) VALUES(?,?,?,?)");
                stmt.setString(1, book.getIsbn());
                stmt.setString(2, book.getTitle());
                stmt.setString(3, book.getPublisher());
                stmt.setString(4, book.getType());
                stmt.executeUpdate();
            } else {
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE books SET isbn=?, title=?, publisher=?, type=? where id = ?");
                stmt.setString(1, book.getIsbn());
                stmt.setString(2, book.getTitle());
                stmt.setString(3, book.getPublisher());
                stmt.setString(4, book.getType());
                stmt.setLong(5, book.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(long id) {
        try (Connection conn = DriverManager.getConnection(URL,
                USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM books WHERE id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findById(long id) {
        Book book = new Book();
        try (Connection conn = DriverManager.getConnection(URL,
                USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM books WHERE id=?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");
                book = new Book(id, isbn, title, publisher, type);
                List<Author> authors = new ArrayList<>();
                stmt = conn.prepareStatement(
                        "SELECT * FROM warsztat04.authors WHERE id IN(SELECT warsztat04.book_authors.author_id FROM warsztat04.book_authors WHERE book_id=?)");
                stmt.setLong(1,id);
                rs = stmt.executeQuery();
                while (rs.next()){
                    long idAuthor = rs.getLong("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    authors.add(new Author(idAuthor, firstName, lastName));
                }
                book.setAuthors(authors);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL,
                USER, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM books");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");
                Book book = new Book(id, isbn, title, publisher, type);
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

