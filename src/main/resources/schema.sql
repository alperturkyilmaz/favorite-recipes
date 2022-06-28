CREATE SEQUENCE IF NOT EXISTS  HIBERNATE_SEQUENCE START WITH 100 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS ingredient (
    id BIGINT NOT NULL PRIMARY KEY ,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipe (
    id BIGINT NOT NULL PRIMARY KEY ,
    name VARCHAR(256) NOT NULL UNIQUE,
    number_of_servings INT NOT NULL,
    instructions VARCHAR(2048) NOT NULL,
    vegetarian BOOLEAN
);

CREATE TABLE IF NOT EXISTS recipe_ingredient (
    ingredient_id BIGINT NOT NULL,
    recipe_id   BIGINT NOT NULL,
    PRIMARY KEY (ingredient_id,recipe_id)
);