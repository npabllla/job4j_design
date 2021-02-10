create table type(
    id serial primary key,
    name varchar(255)
);

create table products(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date DATE,
    price float
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мясо');
insert into type(name) values ('Выпечка');
insert into type(name) values ('Яйца');

insert into products(name, type_id, expired_date, price) values ('Сыр1', 1, '2021-02-13', 413.45);
insert into products(name, type_id, expired_date, price) values ('Сыр2', 1, '2021-3-01', 215.6);
insert into products(name, type_id, expired_date, price) values ('Молоко1', 2, '2021-2-26', 64.5);
insert into products(name, type_id, expired_date, price) values ('Молоко2', 2, '2021-3-02', 84.13);
insert into products(name, type_id, expired_date, price) values ('Молоко3', 2, '2021-2-15', 47.54);
insert into products(name, type_id, expired_date, price) values ('Хлеб1', 4, '2021-2-27', 99.5);
insert into products(name, type_id, expired_date, price) values ('Хлеб2', 4, '2021-2-20', 43.5);
insert into products(name, type_id, expired_date, price) values ('Хлеб3', 4, '2021-2-18', 29.5);
insert into products(name, type_id, expired_date, price) values ('Мясо1', 3, '2021-2-16', 364.5);
insert into products(name, type_id, expired_date, price) values ('Мясо2', 3, '2021-2-13', 245.5);
insert into products(name, type_id, expired_date, price) values ('Мясо3', 3, '2021-2-11', 301.5);
insert into products(name, type_id, expired_date, price) values ('Мясо4', 3, '2021-3-02', 189.5);
insert into products(name, type_id, expired_date, price) values ('Яйца1', 5, '2021-2-26', 76.3);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name from products as p join type as t on p.type_id = t.id where t.name = 'Сыр' group by p.name;
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from products where name LIKE '%мороженное%';
-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select name from products where EXTRACT(MONTH FROM expired_date) = EXTRACT(MONTH FROM now())+1;
-- 4. Написать запрос, который выводит самый дорогой продукт.
select * from products where price = (select max(price) from products);
-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(t.name) from products p join type t on t.id = p.type_id where t.name = 'Мясо' group by t.name;
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from products p join type t on t.id = p.type_id where t.name = 'Молоко' or t.name = 'Сыр';
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name from products as p join type as t on t.id = p.type_id
group by t.name
having count(t.name) < 10;
-- 8. Вывести все продукты и их тип.
select p.id, p.name, p.type_id, p.expired_date, p.price, t.name from products p join type t on t.id = p.type_id;
