CREATE TABLE post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE candidate
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE "user"
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL
);
