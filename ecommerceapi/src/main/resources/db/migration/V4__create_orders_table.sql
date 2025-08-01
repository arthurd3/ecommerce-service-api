CREATE TABLE orders (
id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

specification VARCHAR(255),
customer_id BIGINT NOT NULL,
address_id BIGINT NOT NULL,
product_id UUID NOT NULL,
status VARCHAR(50) NOT NULL,
order_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES customer(id),
CONSTRAINT fk_address FOREIGN KEY(address_id) REFERENCES address(id),
CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES product(id)
);