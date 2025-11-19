CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE autores (
id INT auto_increment PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
nacionalidad VARCHAR(50)
);

CREATE TABLE libros (
isbn VARCHAR(13) PRIMARY KEY,
titulo VARCHAR(200) NOT NULL,
autor_id INT,
año_publicacion INT,
cantidad_total INT NOT NULL,
cantidad_disponible INT NOT NULL,
FOREIGN KEY (autor_id) REFERENCES autores(id)
);

CREATE TABLE socios (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
apellido VARCHAR(100) NOT NULL,
dni VARCHAR(10) UNIQUE NOT NULL,
telefono VARCHAR(15)
);

CREATE TABLE prestamos (
id INT AUTO_INCREMENT PRIMARY KEY,
libro_isbn VARCHAR(13),
socio_id INT,
fecha_prestamo DATE NOT NULL,
fecha_devolucion_prevista DATE NOT NULL,
fecha_devolucion_real DATE NULL,
estado ENUM('PRESTADO', 'DEVUELTO') DEFAULT 'PRESTADO',
FOREIGN KEY (libro_isbn) REFERENCES libros(isbn),
FOREIGN KEY (socio_id) REFERENCES socios(id)
);

select * from autores;