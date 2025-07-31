CREATE TABLE product(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    price NUMERIC(10, 2) NOT NULL ,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    available_to_discount BOOLEAN NOT NULL
);