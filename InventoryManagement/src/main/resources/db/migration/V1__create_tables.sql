
CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price NUMERIC NOT NULL
);

CREATE TABLE stock_item (
                            id BIGSERIAL PRIMARY KEY,
                            product_id BIGINT UNIQUE REFERENCES product(id),
                            quantity INTEGER NOT NULL,
                            updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reservation (
                             id BIGSERIAL PRIMARY KEY,
                             product_id BIGINT REFERENCES product(id),
                             quantity INTEGER NOT NULL,
                             created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                             status VARCHAR(50) NOT NULL
);

CREATE TABLE movement (
                          id BIGSERIAL PRIMARY KEY,
                          product_id BIGINT REFERENCES product(id),
                          type VARCHAR(50) NOT NULL,
                          quantity INTEGER NOT NULL,
                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
