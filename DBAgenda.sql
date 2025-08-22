drop database if exists agenda_db;
create database agenda_db;
use agenda_db;
 
 create table Usuarios(
	codigo_usuario integer auto_increment,
    apodo varchar(32),
    contrasena varchar(32),
    constraint pk_usuarios primary key (codigo_usuario)
 );
 
 create table Contactos(
	codigo_contacto integer auto_increment,
    nombre varchar (64),
    telefono varchar (16),
    correo varchar(128),
    codigo_usuario integer,
    constraint pk_contactos primary key (codigo_contacto),
    constraint fk_contactos_usuarios foreign key(codigo_usuario)
		references Usuarios(codigo_usuario) on delete cascade
);
 
 insert into Usuarios (apodo, contrasena)
	values("Roger","r123");

insert into Contactos (nombre, telefono, correo, codigo_usuario)
	values("Steven","4901-7478","rsteven@kinal", 1);