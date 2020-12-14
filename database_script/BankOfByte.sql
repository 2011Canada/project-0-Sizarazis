drop schema if exists bankofbyte cascade;
create schema bankofbyte;
set schema 'bankofbyte';


create table users (
	user_id serial primary key,
	user_password text not null,
	first_name text,
	last_name text,
	birth_date timestamp,
	email text,
	phone int,
	address text
);


create table customer (
	user_id int unique not null references users (user_id),
	customer_id serial primary key
);


create table account (
	date_created timestamp not null,
	balance numeric(10,2) not null,
	is_validated boolean not null,
	customer_id int not null references customer (customer_id),
	account_id serial primary key
);


create table employee (
	user_id int unique not null references users (user_id),
	employee_id serial primary key
);


create table transaction_logs (
	transaction_id serial primary key,
	transaction_type text not null,
	amount numeric(10,2) not null,
	from_account int references account (account_id),
	to_account int references account (account_id)
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
insert into customer (user_id)
			values (4);
insert into account (date_created, balance, is_validated, customer_id)
			values ('2020-01-01', 2000.00, true, 1);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Augustus', 'Caesar', '0027-01-01', 'augustus@julioclaudian.com', 11111111, '1 Forum Drive');
insert into customer (user_id)
			values (5);
insert into account (date_created, balance, is_validated, customer_id)
			values ('2020-01-01', 0.00, false, 2);
insert into users (user_password, first_name, last_name, birth_date, email, phone, address)
			values ('Password123!', 'Tiberius', 'Caesar', '0014-01-01', 'tiberius@julioclaudian.com', 11111111, '1 Forum Drive');
insert into customer (user_id)
			values (6);
insert into account (date_created, balance, is_validated, customer_id)
			values ('2020-01-01', 1000.00, true, 3);
		
--employee that is also a customer with an account
insert into customer (user_id)
			values (1);
insert into account (date_created, balance, is_validated, customer_id)
			values ('2020-12-12', 500.00, true, 4);

--customer with 2 accounts
insert into account (date_created, balance, is_validated, customer_id)
			values ('2020-12-12', 500.00, true, 1);

--transaction logs
insert into transaction_logs (transaction_type, amount, from_account)
			values ('WITHDRAW', 100.00, 1);
insert into transaction_logs (transaction_type, amount, to_account)
			values ('DEPOSIT', 100.00, 5);
insert into transaction_logs (transaction_type, amount, from_account, to_account)
			values ('TRANSFER', 100.00, 1, 5);
		
commit;
	
select * from users;
select * from customer;
select * from account;
select * from employee;
select * from transaction_logs;

--select a.account_id, a.balance, a.is_validated from account a, customer c where c.customer_id = 1 and c.customer_id = a.customer_id;
--SELECT * FROM bankofbyte.account a, bankofbyte.customer c WHERE c.customer_id = 1 AND a.customer_id = c.customer_id;
--select MAX(customer_id) from customer;

--select u.user_password from users u, customer c where c.customer_id = 1 and c.user_id = u.user_id; 
	
