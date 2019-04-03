--  CREATE TABLE car_marks (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(50) NOT NULL);
--
--  create table car_models(
--    id			SERIAL PRIMARY KEY,
--    name		VARCHAR(50) NOT NULL,
--    mark_id integer not null REFERENCES car_marks (id));
--
--  CREATE TABLE transmission (id SERIAL PRIMARY KEY, name VARCHAR(50) NOT NULL);
--
--  CREATE TABLE body_type (id SERIAL PRIMARY KEY,name VARCHAR(50) NOT NULL);

-- insert into car_marks(name) values ('Honda', 'honda1');
-- insert into car_marks(name) values ('Honda', 'honda2');
-- insert into car_marks(name) values ('Honda', 'honda3');
-- insert into car_marks(name) values ('KIA', 'kia1');
-- insert into car_marks(name) values ('KIA', 'kia2');
-- insert into car_marks(name) values ('KIA', 'kia3');
-- insert into car_marks(name, models) values ('BMW', 'bmw1');
-- insert into car_marks(name, models) values ('BMW', 'bmw2');
-- insert into car_marks(name, models) values ('BMW', 'bmw3');


 insert into car_marks(name) values ('Honda');
 insert into car_marks(name) values ('KIA');
 insert into car_marks(name) values ('BMW');

 insert into car_models(mark_id, name) values (1, 'honda1');
 insert into car_models(mark_id, name) values (1, 'honda2');
 insert into car_models(mark_id, name) values (1, 'honda3');
 insert into car_models(mark_id, name) values (2, 'KIA1');
 insert into car_models(mark_id, name) values (2, 'KIA2');
 insert into car_models(mark_id, name) values (2, 'KIA3');
 insert into car_models(mark_id, name) values (3, 'BMW1');
 insert into car_models(mark_id, name) values (3, 'BMW2');
 insert into car_models(mark_id, name) values (3, 'BMW3');

insert into transmission(name) values ('mechanic');
insert into transmission(name) values ('automatic');
insert into transmission(name) values ('robot');
insert into transmission(name) values ('variable');

insert into body_type(name) values ('sedan');
insert into body_type(name) values ('hatchback');
insert into body_type(name) values ('wagon');
insert into body_type(name) values ('SUV');
insert into body_type(name) values ('cabriolet');
insert into body_type(name) values ('coupe');
insert into body_type(name) values ('limousine');
insert into body_type(name) values ('minivan');
insert into body_type(name) values ('pickup');
insert into body_type(name) values ('Van');
insert into body_type(name) values ('minibus');

insert into usr(login, password) values ('user', 'user');

