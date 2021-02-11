create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    depart_id int references departments(id)
);

insert into departments(name) values ('Dep1');
insert into departments(name) values ('Dep2');
insert into departments(name) values ('Dep3');
insert into departments(name) values ('Dep4');

insert into employees(name, depart_id) values ('Emp1', 4);
insert into employees(name, depart_id) values ('Emp2', 4);
insert into employees(name, depart_id) values ('Emp3', 3);
insert into employees(name, depart_id) values ('Emp41', null);
insert into employees(name, depart_id) values ('Emp42', null);
insert into employees(name, depart_id) values ('Emp5', 3);
insert into employees(name, depart_id) values ('Emp6', 1);
insert into employees(name, depart_id) values ('Emp7', 4);
insert into employees(name, depart_id) values ('Emp8', 1);
insert into employees(name, depart_id) values ('Emp9', 1);

-- Выполнить запросы с left, right, full, cross соединениями
select * from departments d left join employees e on d.id = e.depart_id;
select * from departments d right join employees e on d.id = e.depart_id;
select * from departments d cross join employees e;
select * from departments as d full join employees e on d.id = e.depart_id;
-- Используя left join найти департаменты, у которых нет работников
select * from departments d left join employees e on d.id = e.depart_id where e.id is null;
-- Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from departments d left join employees e on d.id = e.depart_id;
select * from employees e right join departments d on d.id = e.depart_id;
-- Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
    gender char
);
insert into teens(name, gender) values ('Teen1', 'M');
insert into teens(name, gender) values ('Teen1', 'F');
insert into teens(name, gender) values ('Teen1', 'N');
select * from teens t1 cross join teens t2 where t1.gender != t2.gender;