CREATE TABLE IF NOT EXISTS product (
    id bigint PRIMARY KEY,
    name varchar(255) NOT NULL,
    description varchar(255),
    available_quantity int NOT NULL,
    price decimal(19, 2) NOT NULL,
    category VARCHAR(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS product_id_seq
    OWNED BY product.id;
ALTER SEQUENCE product_id_seq OWNER TO postgres;