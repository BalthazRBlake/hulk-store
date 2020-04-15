DROP TABLE product_movements IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE movements IF EXISTS;
DROP TABLE employees IF EXISTS;

CREATE TABLE products (
	id INTEGER IDENTITY PRIMARY KEY,
	item VARCHAR_IGNORECASE(35) NOT NULL,
	brand VARCHAR_IGNORECASE(35) NOT NULL,
	hero VARCHAR_IGNORECASE(35) NOT NULL,
	units INTEGER NOT NULL
);
CREATE INDEX products_item ON products (item);

CREATE TABLE employees(
	id INTEGER IDENTITY PRIMARY KEY,
	first_name VARCHAR(35) NOT NULL
)

CREATE TABLE movements (
	id INTEGER IDENTITY PRIMARY KEY,
	date DATE,
	type VARCHAR(35) NOT NULL,
	emp_id INTEGER NOT NULL
);
ALTER TABLE movements ADD CONSTRAINT fk_movements_employees FOREIGN KEY (emp_id) REFERENCES employees (id);

CREATE TABLE product_movement (
	product_id INTEGER NOT NULL,
  	movement_id INTEGER NOT NULL
);
ALTER TABLE product_movement ADD CONSTRAINT fk_product_movement_products FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE product_movement ADD CONSTRAINT fk_product_movement_movements FOREIGN KEY (movement_id) REFERENCES movements (id);
