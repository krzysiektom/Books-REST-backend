CREATE TABLE authors
(
  id        BIGINT AUTO_INCREMENT,
  firstName VARCHAR(255),
  lastName  VARCHAR(255),
  PRIMARY KEY (id)
);
INSERT INTO authors (firstName, lastName)
VALUES ("Bruce", "Eckel");
INSERT INTO authors (firstName, lastName)
VALUES ("Sierra", "Kathy");
INSERT INTO authors (firstName, lastName)
VALUES ("Bates", "Bert");
INSERT INTO authors (firstName, lastName)
VALUES ("Cay", "Horstmann");
INSERT INTO authors (firstName, lastName)
VALUES ("Gary", "Cornell");