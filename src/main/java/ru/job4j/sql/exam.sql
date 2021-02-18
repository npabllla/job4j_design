create database exam;
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

insert into company(id, name) values (1, 'Company#1');
insert into company(id, name) values (2, 'Company#2');
insert into company(id, name) values (3, 'Company#3');
insert into company(id, name) values (4, 'Company#4');
insert into company(id, name) values (5, 'Company#5');
insert into company(id, name) values (6, 'Company#6');

insert into person(id, name, company_id) values (1, 'Person#1', 5);
insert into person(id, name, company_id) values (2, 'Person#2', 4);
insert into person(id, name, company_id) values (3, 'Person#3', 3);
insert into person(id, name, company_id) values (4, 'Person#4', 1);
insert into person(id, name, company_id) values (5, 'Person#5', 1);
insert into person(id, name, company_id) values (6, 'Person#6', 4);
insert into person(id, name, company_id) values (7, 'Person#7', 4);
insert into person(id, name, company_id) values (8, 'Person#8', 3);
insert into person(id, name, company_id) values (9, 'Person#9', 5);
insert into person(id, name, company_id) values (10, 'Person#10', 5);
insert into person(id, name, company_id) values (11, 'Person#11', 2);
insert into person(id, name, company_id) values (12, 'Person#12', 2);
insert into person(id, name, company_id) values (13, 'Person#13', 2);
insert into person(id, name, company_id) values (14, 'Person#14', 4);


-- В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека
select p.name,c.name from person as p join company c on p.company_id = c.id where p.company_id != 5;
-- Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
select c.name,count(p.id) from company c join person p on c.id = p.company_id
group by c.name
order by count(p.id) desc limit 1;
