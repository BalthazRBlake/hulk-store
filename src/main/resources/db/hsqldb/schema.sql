DROP TABLE product_movements IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE movements IF EXISTS;
DROP TABLE employees IF EXISTS;
DROP TABLE move_types IF EXISTS;

CREATE TABLE products (
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR_IGNORECASE(35) NOT NULL,
	brand VARCHAR_IGNORECASE(35) NOT NULL,
	hero VARCHAR_IGNORECASE(35) NOT NULL,
	units INTEGER NOT NULL
);
CREATE INDEX products_name ON products (name);

CREATE TABLE employees(
	id INTEGER IDENTITY PRIMARY KEY,
	first_name VARCHAR(35) NOT NULL
)

CREATE TABLE move_types(
	id INTEGER IDENTITY PRIMARY KEY,
	name VARCHAR(35)
);

CREATE TABLE movements (
	id INTEGER IDENTITY PRIMARY KEY,
	date DATE,
	emp_id INTEGER NOT NULL,
	type_id INTEGER NOT NULL
);
ALTER TABLE movements ADD CONSTRAINT fk_movements_employees FOREIGN KEY (emp_id) REFERENCES employees (id);
ALTER TABLE movements ADD CONSTRAINT fk_movements_types FOREIGN KEY (type_id) REFERENCES move_types (id);

CREATE TABLE product_movement (
	product_id INTEGER NOT NULL,
  	movement_id INTEGER NOT NULL
);
ALTER TABLE product_movement ADD CONSTRAINT fk_product_movement_products FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE product_movement ADD CONSTRAINT fk_product_movement_movements FOREIGN KEY (movement_id) REFERENCES movements (id);
