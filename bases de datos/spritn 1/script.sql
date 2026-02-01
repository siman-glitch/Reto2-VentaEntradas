CREATE DATABASE cinee;
USE cinee;

CREATE TABLE cliente(
  dni char(9) primary key,
  nombre varchar(50) not null,
  apellidos varchar(50) not null,
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
    ON UPDATE CASCADE,

  CONSTRAINT uq_sesion UNIQUE (idsala, fecha, horainicio)
);

CREATE TABLE compra(
  idcompra int primary key auto_increment,
  fecha date not null,
  hora time not null,
  preciototal decimal(8,2) not null,
  descuento decimal(5,2) not null default 0,
  dni char(9) not null,

  CONSTRAINT ck_precio_compra CHECK (preciototal >= 0),
  CONSTRAINT ck_descuento_compra CHECK (descuento >= 0),
  CONSTRAINT ck_descuento_menor_preciototal CHECK (descuento < preciototal),

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
  CONSTRAINT ck_descuento_menor_precio CHECK (descuento < precio),

  CONSTRAINT fk_idcompra_entrada
    FOREIGN KEY (idcompra) REFERENCES compra(idcompra)
    ON UPDATE CASCADE
    ON DELETE CASCADE,

  CONSTRAINT fk_idsesion_entrada
    FOREIGN KEY (idsesion) REFERENCES sesion(idsesion)
    ON UPDATE CASCADE
);