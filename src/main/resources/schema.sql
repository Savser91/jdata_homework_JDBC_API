CREATE TABLE CUSTOMERS
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    surname      VARCHAR(255),
    age          INTEGER CHECK (age > 0),
    phone_number VARCHAR(20) UNIQUE
);

CREATE TABLE ORDERS
(
    id           SERIAL PRIMARY KEY,
    date         TIMESTAMP DEFAULT NOW(),
    product_name VARCHAR(255),
    customer_id  INTEGER REFERENCES customers,
    amount       INTEGER CHECK ( amount > 0 )
);