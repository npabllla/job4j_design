create table users(
    id serial primary key,
    name varchar(255)
);
create table meetings(
    id serial primary key,
    name varchar(255)
);
create table users_meetings(
    id serial primary key,
    status varchar(255),
    user_id int references users(id),
    meeting_id int references meetings(id)
);

insert into users(name) values ('Ivan');
insert into users(name) values ('Kirill');
insert into users(name) values ('Petr');
insert into users(name) values ('Alexandr');
insert into users(name) values ('Ilya');
insert into users(name) values ('Konstantin');
insert into users(name) values ('Daniil');
insert into users(name) values ('Evgenii');

insert into meetings(name) values ('Java for beginners');
insert into meetings(name) values ('Java for middle');
insert into meetings(name) values ('Java advanced');
insert into meetings(name) values ('Architecture');
insert into meetings(name) values ('Reserve');

insert into users_meetings(user_id, meeting_id, status) values (1, 1, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (2, 1, 'отклонил');
insert into users_meetings(user_id, meeting_id, status) values (3, 3, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (4, 2, 'отклонил');
insert into users_meetings(user_id, meeting_id, status) values (5, 4, 'отклонил');
insert into users_meetings(user_id, meeting_id, status) values (6, 2, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (7, 1, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (8, 3, 'отклонил');
insert into users_meetings(user_id, meeting_id, status) values (2, 3, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (2, 4, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (4, 3, 'подтвердил');
insert into users_meetings(user_id, meeting_id, status) values (5, 2, 'отклонил');
insert into users_meetings(meeting_id) values (5);
-- Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select m.name, count(m.name) from meetings as m right join users_meetings um on m.id = um.meeting_id
where um.status like '%подтвердил%' group by m.name;
-- Нужно получить все совещания, где не было ни одной заявки на посещения
select m.name from meetings as m full join users_meetings um on m.id = um.meeting_id
group by m.name
having count(um.status) = 0;