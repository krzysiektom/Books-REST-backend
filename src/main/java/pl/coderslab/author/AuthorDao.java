package pl.coderslab.author;

import org.springframework.stereotype.Component;
import pl.coderslab.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
class AuthorDao implements Dao<Author> {
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


    @Override
    public Author findById(long id) {
        Author author = new Author();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM warsztat04.authors WHERE id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                author = new Author(id, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM warsztat04.authors");
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

    @Override
    public void save(Author author) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (author.getId() == 0) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO warsztat04.authors(firstName, lastName) VALUES (?,?)");
                statement.setString(1, author.getFirstName());
                statement.setString(2, author.getLastName());
                statement.executeLargeUpdate();
            } else {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE warsztat04.authors SET firstName=?,lastName=? WHERE id=?");
                statement.setString(1,author.getFirstName());
                statement.setString(2,author.getLastName());
                statement.setLong(3,author.getId());
                statement.executeLargeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM warsztat04.authors WHERE id=?");
            statement.setLong(1, id);
            statement.executeLargeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
