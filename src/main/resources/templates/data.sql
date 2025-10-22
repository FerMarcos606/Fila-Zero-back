INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');
-- INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
-- INSERT INTO roles (name) VALUES ('ROLE_COMERCIO');

INSERT INTO customers (username, email, password, role_id) VALUES ('fer.hola','fer.hola@example.com', '$2a$10$YXzHtME0AnzqL9U9rA6iFONnCyIlQEkpmN2/gKfUBFlSzuZQ2GcvC', (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER')); -- "securePasswordFeR321**//CodE"
I
INSERT INTO customers (username, email, password, role_id) VALUES ('maria.garcia', 'maria.garcia@example.com', '$2a$10$YXzHtME0AnzqL9U9rA6iFONnCyIlQEkpmN2/gKfUBFlSzuZQ2GcvC', (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));

INSERT INTO customers (username, email, password, role_id) VALUES ('Jose.Lopez', 'Jose.Lopez@example.com', '$2a$10$YXzHtME0AnzqL9U9rA6iFONnCyIlQEkpmN2/gKfUBFlSzuZQ2GcvC', (SELECT id_role FROM roles WHERE name = 'ROLE_CUSTOMER'));