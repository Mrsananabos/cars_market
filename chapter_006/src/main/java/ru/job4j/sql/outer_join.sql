CREATE TABLE bodywork (
    id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE engine (
    id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE transmission (
    id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE cars (
   id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE,
   bodywork_id  INTEGER REFERENCES bodywork (id) NOT NULL, 
   engine_id INTEGER REFERENCES engine (id) NOT NULL, 
   transmission_id INTEGER REFERENCES transmission (id) NOT NULL 
);

INSERT INTO bodywork (name) VALUES ('cедан'), ('хэтчбек'), ('универсал'), ('лифтбэк'), ('купе'), ('кабриолет');
INSERT INTO engine (name) VALUES ('роторно-поршневой'), ('газовый'), ('дизельный'), ('бензиновый'), ('поршневой');
INSERT INTO transmission (name) VALUES ('механическая'), ('автоматическая'), ('роботизированная'), ('вариативная');
INSERT INTO cars (name, bodywork_id, engine_id, transmission_id) VALUES
  ('model_1', 3, 4, 1),
  ('model_2', 1, 3, 4),
  ('model_3', 6, 2, 2);

SELECT c.name, b.name, e.name, t.name 
FROM cars AS c
INNER JOIN bodywork as b ON c.bodywork_id = b.id
INNER JOIN engine as e ON c.engine_id = e.id
INNER JOIN transmission as t ON c.transmission_id = t.id;

SELECT b.name FROM bodywork AS b
LEFT OUTER JOIN cars AS c ON b.id = c.bodywork_id
WHERE c.id IS NULL;

SELECT e.name FROM engine AS e
LEFT OUTER JOIN cars AS c ON e.id = c.transmission_id
WHERE c.id IS NULL;

SELECT tr.name FROM transmission AS tr
LEFT OUTER JOIN cars AS cr ON tr.id = cr.transmission_id
WHERE cr.id IS NULL;

