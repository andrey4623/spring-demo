create table users
(
	id int auto_increment
		primary key,
	username varchar(45) not null,
	name varchar(255) not null,
	password varchar(255) not null
)
;

create table roles
(
	id int auto_increment
		primary key,
	name varchar(12) not null,
	constraint roles_name_uindex
		unique (name)
)
;

create table privileges
(
	id int auto_increment
		primary key,
	name varchar(25) not null,
	constraint privileges_name_uindex
		unique (name)
)
;

create table users_roles
(
	id int auto_increment
		primary key,
	user_id int not null,
	role_id int not null,
	constraint users_roles_user_id_role_id_pk
		unique (user_id, role_id),
	constraint users_roles_users_id_fk
		foreign key (user_id) references users (id),
	constraint users_roles_roles_id_fk
		foreign key (role_id) references roles (id)
)
;

create index users_roles_roles_id_fk
	on users_roles (role_id)
;

create table roles_privileges
(
	id int auto_increment
		primary key,
	role_id int not null,
	privilege_id int not null,
	constraint roles_privileges_role_id_privilege_id_pk
		unique (role_id, privilege_id),
	constraint roles_privileges_roles_id_fk
		foreign key (role_id) references roles (id),
	constraint roles_privileges_privileges_id_fk
		foreign key (privilege_id) references privileges (id)
)
;

create index roles_privileges_privileges_id_fk
	on roles_privileges (privilege_id)
;
