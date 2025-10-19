INSERT INTO roles (name) VALUES ('ROLE_CUSTOMER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_COMERCIO');

INSERT INTO USER (email, password) VALUES ('fer.hola@example.com', '$2a$12$ECPk9I0EqCpiblvSCEzRwex00M3e7LU23hHbx9V3JXKhsv01gWV5S'); -- "securePasswordFeR321**//CodE"
INSERT INTO roles_customer (customer_id, role_id) VALUES ((SELECT id_customer FROM users WHERE email = 'fer.hola@example.com'), (SELECT id_customer FROM roles WHERE name = 'ROLE_CUSTOMER'));
INSERT INTO profiles (dni, name, first_surname, second_surname, phone_number, user_id) VALUES ('87654321Z', 'CUSTOMER', 'System', 'Root', '699999999', (SELECT id_user FROM users WHERE email = 'fer.hola2@example.com'));