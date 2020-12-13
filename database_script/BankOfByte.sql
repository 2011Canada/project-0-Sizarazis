drop schema if exists bankofbyte cascade;
create schema bankofbyte;
set schema 'bankofbyte';


create table users (
	user_id serial primary key,
	user_password text not null,
	first_name text not null,
	last_name text not null,
	birth_date timestamp,
	email text,
	phone int,
	address text
);


create table account (
	date_created timestamp not null,
	balance numeric(10,2) not null,
	is_validated boolean not null,
	user_id int not null references users (user_id),
	account_id serial primary key
);


create table customer (
	user_id int unique not null references users (user_id),
	account_id int unique not null references account (account_id),
	primary key (user_id, account_id)
);


create table employee (
	user_id int unique not null references users (user_id),
	employee_id serial primary key
);


begin;

--dummy employees
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Stephen', 'Razis', '1991-01-02', 'stephen@stephenrazis.com', 11111111, '1234 someplace drive');
insert into employee (user_id) values (1);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Alpha', 'Centauri', '1111-01-01', 'ac@space.com', 11111111, 'Somewhere in space');
insert into employee (user_id) values (2);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Santa', 'Claus', '0270-03-15', 'santa@reindeer.net', 11111111, '1 North Pole');
insert into employee (user_id) values (3);

--dummy customers
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Julius', 'Caesar', '0051-01-01', 'julius@julioclaudian.com', 11111111, '1 Forum Drive');
insert into account (date_created, balance, is_validated, user_id)
			values ('2020-01-01', 2000.00, true, 4);
insert into customer (user_id, account_id)
			values (4, 1);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Augustus', 'Caesar', '0027-01-01', 'augustus@julioclaudian.com', 11111111, '1 Forum Drive');
insert into account (date_created, balance, is_validated, user_id)
			values ('2020-01-01', 0.00, false, 5);
insert into customer (user_id, account_id)
			values (5, 2);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Tiberius', 'Caesar', '0014-01-01', 'tiberius@julioclaudian.com', 11111111, '1 Forum Drive');
insert into account (date_created, balance, is_validated, user_id)
			values ('2020-01-01', 1000.00, true, 6);
insert into customer (user_id, account_id)
			values (6, 3);
insert into account (date_created, balance, is_validated, user_id)
			values ('2020-12-12', 500.00, true, 1);
insert into customer (user_id, account_id)
			values (1, 4);
commit;


select * from users;

select * from customer;

select * from account;

select * from employee;