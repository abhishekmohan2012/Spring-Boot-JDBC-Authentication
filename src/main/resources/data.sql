INSERT INTO users (username, password, enabled)
VALUES ( 'commonuser', 'password1', true );

INSERT INTO users (username, password, enabled)
VALUES ( 'admin', 'admin123', true );

INSERT INTO users (username, password, enabled)
VALUES ( 'privilegedUser', 'password2', true );

INSERT INTO authorities (username, authority)
VALUES ( 'commonuser', 'ROLE_USER');

INSERT INTO authorities (username, authority)
VALUES ( 'admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
VALUES ( 'privilegedUser', 'ROLE_PRIVILEGE');