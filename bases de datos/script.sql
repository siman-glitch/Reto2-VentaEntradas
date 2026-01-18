CREATE DATABASE cine_daw;
USE cine_daw;

CREATE TABLE cliente(
  dni varchar(9) primary key,
  nombre varchar(50) not null,
  apellidos varchar(50),
  correoElectronico varchar(100),
  contrasena varchar(255) not null
);

CREATE TABLE sala(
  idsala int primary key auto_increment,
  nombresala varchar(50) not null
);

CREATE TABLE pelicula(
  idpelicula int primary key auto_increment,
  titulo varchar(200) not null,
  duracion int not null,
  genero varchar(100) not null,
  precio decimal(6,2) not null,
  CONSTRAINT ck_duracion CHECK (duracion > 0),
  CONSTRAINT ck_precio_pelicula CHECK (precio > 0)
);

CREATE TABLE sesion(
  idsesion int primary key auto_increment,
  fecha date not null,
  horainicio time not null,
  horafin time not null,
  precio decimal(6,2) not null,
  numEspectadores int not null default 0,
  idsala int not null,
  idpelicula int not null,

  CONSTRAINT ck_precio_sesion CHECK (precio > 0),
  CONSTRAINT ck_numEspectadores CHECK (numEspectadores >= 0),

  CONSTRAINT fk_idsala_sesion
    FOREIGN KEY (idsala) REFERENCES sala(idsala)
    ON UPDATE CASCADE,

  CONSTRAINT fk_idpelicula_sesion
    FOREIGN KEY (idpelicula) REFERENCES pelicula(idpelicula)
    ON UPDATE CASCADE
);

CREATE TABLE compra(
  idcompra int primary key auto_increment,
  fecha date not null,
  hora time not null,
  preciototal decimal(8,2) not null,
  descuento decimal(5,2) not null default 0,
  dni varchar(9) not null,

  CONSTRAINT ck_precio_compra CHECK (preciototal >= 0),
  CONSTRAINT ck_descuento_compra CHECK (descuento >= 0),

  CONSTRAINT fk_cliente_compra
    FOREIGN KEY (dni) REFERENCES cliente(dni)
    ON UPDATE CASCADE
);

CREATE TABLE entrada(
  identrada int primary key auto_increment,
  numpersonas int not null,
  precio decimal(6,2) not null,
  descuento decimal(5,2) not null default 0,
  idcompra int not null,
  idsesion int not null,

  CONSTRAINT ck_numpersonas_entrada CHECK (numpersonas > 0),
  CONSTRAINT ck_precio_entrada CHECK (precio >= 0),
  CONSTRAINT ck_descuento_entrada CHECK (descuento >= 0),

  CONSTRAINT fk_idcompra_entrada
    FOREIGN KEY (idcompra) REFERENCES compra(idcompra)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_idsesion_entrada
    FOREIGN KEY (idsesion) REFERENCES sesion(idsesion)
    ON UPDATE CASCADE
);
INSERT INTO cliente VALUES
('45678901D', 'Carlos', 'García', 'carlos@correo.es', '1234'),
('56789012E', 'María', 'Fernández', 'maria@correo.es', '1234'),
('67890123F', 'Antonio', 'Martínez', 'antonio@correo.es', '1234'),
('78901234G', 'Lucía', 'Sánchez', 'lucia@correo.es', '1234'),
('89012345H', 'Javier', 'Rodríguez', 'javier@correo.es', '1234'),
('90123456J', 'Pablo', 'Navarro', 'pablo@correo.es', '1234'),
('01234567K', 'Elena', 'Ruiz', 'elena@correo.es', '1234');

INSERT INTO sala (nombresala) VALUES
('Sala IMAX'),
('Sala 3D'),
('Sala Premium');
INSERT INTO pelicula (titulo, duracion, genero, precio) VALUES
('Ocho apellidos vascos', 98, 'Comedia', 6.00),
('La casa de papel', 120, 'Acción', 7.50),
('El orfanato', 105, 'Terror', 6.50),
('Campeones', 124, 'Drama', 6.00),
('Torrente', 97, 'Comedia', 5.50);
INSERT INTO sesion
(fecha, horainicio, horafin, precio, numEspectadores, idsala, idpelicula)
VALUES
('2026-01-22', '18:00:00', '20:00:00', 6.00, 80, 1, 1),
('2026-01-22', '20:30:00', '22:30:00', 7.50, 120, 2, 2),
('2026-01-23', '19:00:00', '21:00:00', 6.50, 60, 3, 3),
('2026-01-23', '22:00:00', '23:50:00', 5.50, 40, 1, 5);
INSERT INTO compra
(fecha, hora, preciototal, descuento, dni)
VALUES
('2026-01-22', '17:30:00', 12.00, 0, '45678901D'),
('2026-01-22', '19:45:00', 15.00, 1.50, '56789012E'),
('2026-01-23', '18:15:00', 18.00, 0, '67890123F'),
('2026-01-23', '21:00:00', 11.00, 0, '78901234G');
INSERT INTO entrada
(numpersonas, precio, descuento, idcompra, idsesion)
VALUES
(2, 6.00, 0, 1, 1),
(3, 7.50, 1.50, 2, 2),
(1, 6.50, 0, 3, 3),
(2, 5.50, 0, 4, 4);

