INSERT INTO users (username, name, password) VALUES ('user', 'Andrey', '827ccb0eea8a706c4c34a16891f84e7b');
INSERT INTO users (username, name, password) VALUES ('admin', 'Admin', '21232f297a57a5a743894a0e4a801fc3');

INSERT INTO privileges (name) VALUES ('ROLE_ACCESS_CONTROL_PANEL');
INSERT INTO privileges (name) VALUES ('ROLE_READ_PROFILE');

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 2);

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
