CREATE TABLE defaultUser (
    username varchar(255) NOT NULL PRIMARY KEY,
    password varchar(255) NOT NULL
);

CREATE TABLE ong (
    id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
    nome varchar(255) NOT NULL,
    endereco varchar(255),
    telefone varchar(255),
    email varchar(255)
);