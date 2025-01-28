CREATE TABLE IF NOT EXISTS category (
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255)
);

CREATE SEQUENCE IF NOT EXISTS category_id_seq
    OWNED BY category.id;
ALTER SEQUENCE category_id_seq OWNER TO postgres;

CREATE TABLE IF NOT EXISTS product (
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255),
    available_quantity int NOT NULL,
    price decimal(19, 2) NOT NULL,
    category_id bigint NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE SEQUENCE IF NOT EXISTS product_id_seq
    OWNED BY product.id;
ALTER SEQUENCE product_id_seq OWNER TO postgres;