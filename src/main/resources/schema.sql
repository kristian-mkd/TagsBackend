DROP TABLE IF EXISTS ACCOUNTS;

  CREATE TABLE ACCOUNTS (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    email VARCHAR(250) NOT NULL,
    displayName VARCHAR(250) NOT NULL,
    password VARCHAR(50) NOT NULL
  );