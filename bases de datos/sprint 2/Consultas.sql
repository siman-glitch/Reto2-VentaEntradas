USE cinee;

-- 1) Dinero recaudado por cada pelicula con recaudacion superior a 100
SELECT p.idpelicula, p.titulo,
       ROUND(SUM(e.numpersonas * (e.precio - e.descuento)), 2) AS recaudacion
FROM pelicula p, sesion s, entrada e
WHERE p.idpelicula = s.idpelicula
  AND s.idsesion = e.idsesion
GROUP BY p.idpelicula, p.titulo
HAVING recaudacion > 100
ORDER BY recaudacion DESC;

-- 2) Duraciion media de las peliculas por genero
SELECT p.genero,
       ROUND(AVG(p.duracion), 2) AS duracion_media
FROM pelicula p
GROUP BY p.genero
ORDER BY duracion_media DESC;

-- 3) Numero de sesiones ofrecidas por pelicula 
SELECT p.idpelicula, p.titulo,
       COUNT(s.idsesion) AS num_sesiones
FROM pelicula p, sesion s
WHERE p.idpelicula = s.idpelicula
GROUP BY p.idpelicula, p.titulo
ORDER BY num_sesiones DESC, p.titulo;

-- 3.1) Numero de sesiones ofrecidas por pelicula (filtrar por genero: Accion)
SELECT p.idpelicula, p.titulo, p.genero,
       COUNT(s.idsesion) AS num_sesiones
FROM pelicula p, sesion s
WHERE p.idpelicula = s.idpelicula
  AND p.genero = 'Acción'
GROUP BY p.idpelicula, p.titulo, p.genero
ORDER BY num_sesiones DESC, p.titulo;

-- 3.2) Numero de sesiones ofrecidas por pelicula (filtrar por precio: <= 7.00)
SELECT p.idpelicula, p.titulo, p.precio,
       COUNT(s.idsesion) AS num_sesiones
FROM pelicula p, sesion s
WHERE p.idpelicula = s.idpelicula
  AND p.precio <= 7.00
GROUP BY p.idpelicula, p.titulo, p.precio
ORDER BY num_sesiones DESC, p.titulo;

-- 4) Precio medio de las peliculas por genero
SELECT p.genero,
       ROUND(AVG(p.precio), 2) AS precio_medio
FROM pelicula p
GROUP BY p.genero
ORDER BY precio_medio DESC;

-- 5) Peliculas con mayor recaudacion (Top 5)
SELECT p.idpelicula, p.titulo, p.genero, p.duracion,
       ROUND(SUM(e.numpersonas * (e.precio - e.descuento)), 2) AS recaudacion
FROM pelicula p, sesion s, entrada e
WHERE p.idpelicula = s.idpelicula
  AND s.idsesion = e.idsesion
GROUP BY p.idpelicula, p.titulo, p.genero, p.duracion
ORDER BY recaudacion DESC
LIMIT 5;

-- 6) Clientes con mayores porcentajes de descuento en sus compras
SELECT c.dni, c.nombre, c.apellidos,
       ROUND(MAX((co.descuento * 100.0) / co.preciototal), 2) AS porcentaje_desc
FROM cliente c, compra co
WHERE c.dni = co.dni
GROUP BY c.dni, c.nombre, c.apellidos
ORDER BY porcentaje_desc DESC, c.apellidos, c.nombre;

-- 7) Clientes que han adquirido mayor numero de entradas
SELECT c.dni, c.nombre, c.apellidos,
       SUM(e.numpersonas) AS total_entradas
FROM cliente c, compra co, entrada e
WHERE c.dni = co.dni
  AND co.idcompra = e.idcompra
GROUP BY c.dni, c.nombre, c.apellidos
ORDER BY total_entradas DESC, c.apellidos, c.nombre;

-- 8) Clientes que han gastado mas dinero
SELECT c.dni, c.nombre, c.apellidos,
       ROUND(SUM(co.preciototal - co.descuento), 2) AS gastado
FROM cliente c, compra co
WHERE c.dni = co.dni
GROUP BY c.dni, c.nombre, c.apellidos
ORDER BY gastado DESC, c.apellidos, c.nombre;

-- 9) Peliculas con espectadores inferiores a 400
SELECT p.idpelicula, p.titulo,
       SUM(s.numEspectadores) AS espectadores_totales
FROM pelicula p, sesion s
WHERE p.idpelicula = s.idpelicula
GROUP BY p.idpelicula, p.titulo
HAVING espectadores_totales < 400
ORDER BY espectadores_totales ASC;

-- 10) Clientes que aun no han comprado ninguna entrada
SELECT c.dni, c.nombre, c.apellidos, c.correoElectronico
FROM cliente c
WHERE NOT EXISTS (
  SELECT 1
  FROM compra co, entrada e
  WHERE co.dni = c.dni
    AND e.idcompra = co.idcompra
);