create table owner(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    owner_id int references owner(id)
);

insert into owner(name) values ('Ivan');
insert into owner(name) values ('Kirill');
insert into owner(name) values ('Alexandr');

insert into cars(name, owner_id) values ('Car21', 1);
insert into cars(name, owner_id) values ('Car35', 1);
insert into cars(name, owner_id) values ('Car1', 3);

select ow.name, c.name from owner as ow join cars as c on ow.id = c.owner_id;
select ow.name as ИмяВладельца, c.name as НазваниеМашины from owner as ow join cars as c on ow.id = c.owner_id;
select ow.name, c.name from owner as ow join cars as c on ow.id = c.owner_id where ow.name LIKE '%Ivan';