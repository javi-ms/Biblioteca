DROP DATABASE IF EXISTS Biblioteca;
CREATE DATABASE Biblioteca;
USE Biblioteca;
CREATE TABLE Libro(
    ID_libro int(3) primary key,
    Nombre_Libro varchar(75),
    Autor varchar(75),
    ISBN char(10),
    NuEdicion int(3) NOT NULL,
    editorial set('Minotauro', 'Salamandra', 'Edelvives') NOT NULL,
    Genero set('Ciencia Ficcion', 'Medieval'),
	Publicacion date,
    Prestado boolean NOT NULL,
    PrestadoA varchar(100),
	Sinopsis varchar(255)
);

Create table Prestamo (
    ID_Prestamo int(3) primary key,
    ID_libro int(3),
    NombreLibro varchar(100) NOT NULL,
    Autor varchar(100),
    PrestadoA varchar(20) not null,
    FechaPrestamo date,
    FechaDevolucion date,
    CONSTRAINT Libro_FK FOREIGN KEY (ID_libro)
        REFERENCES Libro (ID_libro)
);
/*Esto va en un select
Create table HistoricoLibro (
    ID_libro int unsigned primary key,
    NombreLibro varchar(100) NOT NULL,
    Autor varchar(100),
    Prestado varchar(2) NOT NULL,
    FechaPrestamo date,
    FechaDevolucion date,
	ID_Prestamo char(3),
	CONSTRAINT HistoricoLibro FOREIGN KEY (ID_Prestamo) REFERENCES Prestamo (ID_Prestamo)
	
);*/

insert into Libro values(1,'Harry Potter','Rowling',0000000001,1,'Salamandra','Ciencia Ficcion','0000-00-00',true,'Javier','El libro va de magia');

insert into Prestamo values(1,1,'Harry potter','Rowling','Javier','0000/00/00','0000-00-0000' );
/*
Select * from Libro where Id_libro=1;

Select 
    p.ID_Prestamo,
    p.Id_Libro,
    p.NombreLibro,
    p.Autor,
    p.PrestadoA,
    p.FechaPrestamo,
    p.FechaDevolucion
from
    Prestamo as p
join
Libro as l using (id_libro)
where
    l.Prestado is true;
*/

