package pl.coderslab.book;

import pl.coderslab.author.Author;

import java.util.List;

public class Book {
    private long id;
    private String isbn;
    private String title;
    private String publisher;
    private String type;
    private List<Author> authors;

    Book() {
    }

    public Book(long id, String isbn, String title, String publisher, String type) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
