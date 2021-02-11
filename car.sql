create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
    id serial primary key,
    name varchar(255)
);

create table transmission(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id) unique,
    engine_id int references engine(id) unique,
    transmission_id int references transmission(id) unique
);

insert into body(name) values ('B1');
insert into body(name) values ('B2');
insert into body(name) values ('B3');
insert into body(name) values ('B4');
insert into body(name) values ('B5');

insert into engine(name) values ('E1');
insert into engine(name) values ('E2');
insert into engine(name) values ('E3');
insert into engine(name) values ('E4');
insert into engine(name) values ('E5');

insert into transmission(name) values ('T1');
insert into transmission(name) values ('T2');
insert into transmission(name) values ('T3');
insert into transmission(name) values ('T4');
insert into transmission(name) values ('T5');

insert into car(name, body_id, engine_id, transmission_id) values ('C1', 2, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values ('C2', 3, 1, 5);
insert into car(name, body_id, engine_id, transmission_id) values ('C3', 4, 4, 2);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name, e.name, t.name from car c  join body b on c.body_id = b.id
                                                  join engine e on c.engine_id = e.id
                                                  join transmission t on c.transmission_id = t.id;
-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.name from car c full join body b on c.body_id = b.id where c.id is null;
select e.name from car c full join engine e on c.engine_id = e.id where c.id is null;
select t.name from car c full join transmission t on c.transmission_id = t.id where c.id is null;