CREATE DATABASE java_a_from_z;

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE rules (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE role_rules (
 id SERIAL PRIMARY KEY,
 role_id  INTEGER REFERENCES roles (id) NOT NULL
 rules_id INTEGER REFERENCES rules (id) NOT NULL,
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR(50) NOT NULL UNIQUE,
  passw VARCHAR(50) NOT NULL,
  data_registration DATE,
  role_id  INTEGER REFERENCES roles (id) NOT NULL
);

CREATE TABLE state (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE item (
  id SERIAL PRIMARY KEY,
  name        VARCHAR(50)                        NOT NULL,
  description TEXT                               NOT NULL,
  user_id     INTEGER REFERENCES users (id)      NOT NULL,
  category_id INTEGER REFERENCES categories (id) NOT NULL,
  state_id    INTEGER REFERENCES state (id)      NOT NULL
);


CREATE TABLE attaches (
  id SERIAL PRIMARY KEY,
  description TEXT NOT NULL,
  item_id     INTEGER REFERENCES item (id) NOT NULL
);

CREATE TABLE comments (
  id SERIAL PRIMARY KEY,
  description TEXT                         NOT NULL,
  item_id     INTEGER REFERENCES item (id) NOT NULL
);

INSERT INTO roles (name) VALUES ('administrator'), ('user'), ('guest');

INSERT INTO rules (name) VALUES ('create'), ('read'), ('update'), ('delete'), ('comment');

INSERT INTO users (login, password, data_registration, role_id) VALUES
  ('administrator', 'administrator', 20150912, (SELECT id
                    FROM roles
                    WHERE name = 'administrator')),
  ('user', 'user', 20151212, (SELECT id
                    FROM roles
                    WHERE name = 'user')),
  ('guest', 'guest', 20160116, (SELECT id
                      FROM roles
                      WHERE name = 'guest'));

INSERT INTO state (name) VALUES ('new'), ('in work'), ('close');

INSERT INTO categories (name) VALUES ('C1'), ('C2'), ('C3');

INSERT INTO item (name, description, user_id, category_id, state_id) VALUES
  (
    'item_1',
    'desc One',
    (SELECT id
     FROM users
     WHERE login = 'user'),
    (SELECT id
     FROM categories
     WHERE name = 'C1'),
    (SELECT id
     FROM state
     WHERE name = 'in work')
  ),
  (
    'item_2',
    'desc Two',
    (SELECT id
     FROM users
     WHERE login = 'user'),
    (SELECT id
     FROM categories
     WHERE name = 'C2'),
    (SELECT id
     FROM state
     WHERE name = 'close')
  );

INSERT INTO comments (description, item_id) VALUES
  (
    'Comment one',
    (SELECT id
     FROM item
     WHERE name = 'item_1')
  ),
  (
    'Comment two',
    (SELECT id
     FROM item
     WHERE name = 'item_2')
  );

INSERT INTO attaches (description, item_id) VALUES (
  'attach', (SELECT id
             FROM item
             WHERE name = 'item_2')
)

INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'administrator'), (SELECT id 
 FROM rules
 WHERE name = 'create'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'administrator'), (SELECT id 
 FROM rules
 WHERE name = 'read'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'administrator'), (SELECT id 
 FROM rules
 WHERE name = 'update'));
 
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'administrator'), (SELECT id 
 FROM rules
 WHERE name = 'delete'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'administrator'), (SELECT id 
 FROM rules
 WHERE name = 'comment'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'user'), (SELECT id 
 FROM rules
 WHERE name = 'create'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'user'), (SELECT id 
 FROM rules
 WHERE name = 'read'));
INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'user'), (SELECT id 
 FROM rules
 WHERE name = 'comment'));
 INSERT INTO role_rules (role_id, rules_id) VALUES
((SELECT id 
 FROM roles
 WHERE name = 'guest'), (SELECT id 
 FROM rules
 WHERE name = 'read'));