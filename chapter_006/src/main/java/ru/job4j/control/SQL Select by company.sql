CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);


INSERT INTO company (id, name) VALUES 
(1, 'company_1'),
(2, 'company_2'),
(3, 'company_3'),
(4, 'company_4'),
(5, 'company_5');


INSERT INTO person (id, name, company_id) VALUES 
(1, 'Ольга', 2),
(2, 'Роман', 1),
(3, 'Анастасия', 5),
(4, 'Антонина', 3),
(5, 'Евгений', 2),
(6, 'Александр', 4),
(7, 'Егор', 2),
(8, 'Елизавета', 3),
(9, 'Михаил', 5),
(10, 'Павел', 1);

//  1.Retrieve in a single query:
// - names of all persons that are NOT in the company with id = 5

SELECT p.name, c.name FROM person p 
INNER JOIN company c on p.company_id = c.id
WHERE c.id <> 5;

// - company name for each person
SELECT p.name, c.name FROM person p 
INNER JOIN company c on p.company_id = c.id;

// 2.Select the name of the company with the maximum number of persons + number of persons in this company

SELECT c.name, count(p.id) Person_Count 
FROM company c, person p 
WHERE c.id = p.company_id 
GROUP BY c.name
ORDER BY Person_Count desc limit 1;


