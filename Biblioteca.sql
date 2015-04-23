DROP DATABASE IF EXISTS Biblioteca;
CREATE DATABASE Biblioteca;
USE Biblioteca;
CREATE TABLE Libro (
    ID_libro int unsigned auto_increment primary key,
    Nombre_Libro varchar(75),
    Autor varchar(75),
    ISBN char(10),
    NuEdicion int(3) NOT NULL,
    editorial set('Minotauro', 'Salamandra', 'Edelvives') NOT NULL,
    Genero set('Ciencia Ficcion', 'Medieval'),
    Prestado varchar(2) NOT NULL,
    PrestadoA varchar(100) NOT NULL
) engine=innodb;
Create table Prestamo (
    ID_libro int unsigned,
    NombreLibro varchar(100) NOT NULL,
    Autor varchar(100),
    Prestado varchar(2) NOT NULL,
    FechaPrestamo date,
    FechaDevolucion date,
    constraint prestamoidlibropk primary key (ID_libro),
	constraint prestamoidlibrofk foreign key (ID_libro) references Libro(ID_libro) on delete set null on update cascade
)engine=innodb;
Create table HistoricoLibro (
    ID_libro int unsigned primary key,
    NombreLibro varchar(100) NOT NULL,
    Autor varchar(100),
    Prestado varchar(2) NOT NULL,
    FechaPrestamo date,
    FechaDevolucion date
)engine=innodb;