create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into devices(name, price) values ('watch1', 15.2);
insert into devices(name, price) values ('phone1', 68.84);
insert into devices(name, price) values ('phone2', 35.8);
insert into devices(name, price) values ('watch2', 10.2);
insert into devices(name, price) values ('phone3', 112.4);
insert into devices(name, price) values ('phone4', 118.1);
insert into devices(name, price) values ('phone5', 78.3);

insert into people(name) values ('Ivan');
insert into people(name) values ('Kirill');
insert into people(name) values ('Alexandr');
insert into people(name) values ('Mihail');
insert into people(name) values ('Lev');

insert into devices_people(device_id, people_id) values (1,2);
insert into devices_people(device_id, people_id) values (2,2);
insert into devices_people(device_id, people_id) values (3,2);
insert into devices_people(device_id, people_id) values (4,1);
insert into devices_people(device_id, people_id) values (7,1);
insert into devices_people(device_id, people_id) values (5,3);
insert into devices_people(device_id, people_id) values (6,4);
insert into devices_people(device_id, people_id) values (3,4);
insert into devices_people(device_id, people_id) values (1,4);
insert into devices_people(device_id, people_id) values (6,5);

select avg(price) from devices;
select p.name, avg(d.price) from people as p join devices_people as dp on dp.people_id = p.id join devices as d on dp.device_id = d.id
group by p.name;
select p.name, avg(d.price) from people as p join devices_people as dp on dp.people_id = p.id join devices as d on dp.device_id = d.id
group by p.name
having min(d.price) > 50;