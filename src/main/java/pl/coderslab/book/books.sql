CREATE DATABASE warsztat04
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
use warsztat04;
CREATE TABLE books
(
  id        BIGINT AUTO_INCREMENT,
  isbn      VARCHAR(255),
  title     VARCHAR(255),
  author    VARCHAR(255),
  publisher VARCHAR(255),
  type      VARCHAR(255),
  PRIMARY KEY (id)
);
INSERT INTO books(isbn, title, author, publisher, type)
VALUES ("9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
INSERT INTO books(isbn, title, author, publisher, type)
VALUES ("9788324627738", "Rusz glowa, Java.", "Sierra Kathy, Bates Bert", "Helion", "programming");
INSERT INTO books(isbn, title, author, publisher, type)
VALUES ("9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming");
