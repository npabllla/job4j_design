insert into role(name) values ('Junior');
insert into role(name) values ('Middle');
insert into role(name) values ('Senior');

insert into category(name) values ('Easy');
insert into category(name) values ('medium');
insert into category(name) values ('Hard');

insert into state(name) values ('not important');
insert into state(name) values ('important');
insert into state(name) values ('extremely important');

insert into users(name, role_id) values ('Bob', 1);
insert into users(name, role_id) values ('Jhon', 2);
insert into users(name, role_id) values ('Mike', 3);

insert into rules(name) values ('beginner-admin');
insert into rules(name) values ('semi-admin');
insert into rules(name) values ('admin');

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (3, 3);

insert into item(name, user_id, category_id, state_id) values ('Bug', 1, 1, 1);
insert into item(name, user_id, category_id, state_id) values ('Feature', 2, 2, 2);
insert into item(name, user_id, category_id, state_id) values ('Architecture', 3, 3, 3);

insert into comments(text, item_id) values ('Bug fix', 1);
insert into comments(text, item_id) values ('Make a new feature', 2);
insert into comments(text, item_id) values ('Think about new architecture', 3);

insert into attaches(text, item_id) values ('Path to bag', 1);
insert into attaches(text, item_id) values ('Technical task', 2);
insert into attaches(text, item_id) values ('Technical task', 3);
