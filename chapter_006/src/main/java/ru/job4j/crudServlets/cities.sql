create table country(
	id			bigint not null primary key,
	name		character varying(128) not null,
	crt_date	TIMESTAMP with time zone NOT NULL DEFAULT NOW());

create table city(
	id			SERIAL PRIMARY KEY,
	country_id	bigint not null REFERENCES country (id),
	name		character varying(128) not null,
	crt_date	TIMESTAMP with time zone NOT NULL DEFAULT NOW());

insert into country(id, name) values (0, 'Россия');
insert into country(id, name) values (1, 'Украина');
insert into country(id, name) values (2, 'Беларусь');
insert into city(country_id, name) values (0, 'Москва');
insert into city(country_id, name) values (0, 'Санкт-Петербург');
insert into city(country_id, name) values (0, 'Новосибирск');
insert into city(country_id, name) values (0, 'Екатеринбург');
insert into city(country_id, name) values (0, 'Нижний Новгород');
insert into city(country_id, name) values (0, 'Казань');
insert into city(country_id, name) values (0, 'Челябинск');
insert into city(country_id, name) values (0, 'Омск');
insert into city(country_id, name) values (0, 'Самара');
insert into city(country_id, name) values (0, 'Ростов-на-Дону');
insert into city(country_id, name) values (0, 'Уфа');
insert into city(country_id, name) values (0, 'Красноярск');
insert into city(country_id, name) values (0, 'Пермь');
insert into city(country_id, name) values (0, 'Воронеж');
insert into city(country_id, name) values (0, 'Волгоград');
insert into city(country_id, name) values (1, 'Киев');
insert into city(country_id, name) values (1, 'Харьков');
insert into city(country_id, name) values (1, 'Одесса');
insert into city(country_id, name) values (1, 'Днепр');
insert into city(country_id, name) values (1, 'Донецк');
insert into city(country_id, name) values (2, 'Минск');
insert into city(country_id, name) values (2, 'Брест');
insert into city(country_id, name) values (2, 'Гродно');
insert into city(country_id, name) values (2, 'Гомель');
insert into city(country_id, name) values (2, 'Витебск');
insert into city(country_id, name) values (2, 'Могилёв');
insert into city(country_id, name) values (2, 'Вобруйск');
insert into city(country_id, name) values (2, 'Барановичи');
insert into city(country_id, name) values (2, 'Новополоцк');
insert into city(country_id, name) values (2, 'Пинск')
