CREATE TABLE book_authors
(
  book_id   BIGINT NOT NULL,
  author_id BIGINT NOT NULL,
  FOREIGN KEY (book_id) REFERENCES warsztat04.books (id) ON DELETE CASCADE,
FOREIGN KEY (author_id) REFERENCES warsztat04.authors(id) ON DELETE CASCADE
);
INSERT INTO book_authors(book_id, author_id) VALUES (1,1);
INSERT INTO book_authors(book_id, author_id) VALUES (2,2);
INSERT INTO book_authors(book_id, author_id) VALUES (2,3);
INSERT INTO book_authors(book_id, author_id) VALUES (3,4);
INSERT INTO book_authors(book_id, author_id) VALUES (3,5);
