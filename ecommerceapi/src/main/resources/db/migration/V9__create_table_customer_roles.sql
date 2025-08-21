CREATE TABLE customer_roles (
    customer_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (customer_id, role_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);