# Reto2-VentaEntradas
## Descripción del proyecto
Este proyecto consiste en el desarrollo de una aplicación para la gestión de la venta de entradas de cine, realizada como parte del Reto 2 del ciclo formativo de Desarrollo de Aplicaciones Multiplataforma (DAM).

El sistema permite gestionar películas, salas, sesiones y clientes, así como simular el proceso de compra de entradas tanto desde una aplicación de escritorio en Java como desde una página web dinámica.  
Toda la información se apoya en una base de datos relacional MySQL.

## Funcionalidades principales
- Gestión de clientes, películas, salas y sesiones.
- Proceso de compra de entradas con cálculo de precios.
- Aplicación de descuentos según el número de películas compradas.
- Sistema de login de usuarios.
- Simulación del proceso de compra desde una página web.
- Almacenamiento de la información en base de datos.
- Documentación del diseño de la base de datos (modelo E/R y normalización).
## Tecnologías utilizadas
- Java (aplicación de escritorio)
- MySQL (base de datos)
- HTML5 / CSS3 / JavaScript (web dinámica)
- Git y GitHub (control de versiones)

- ## Estructura del repositorio

El repositorio se organiza de la siguiente manera para facilitar el mantenimiento y la comprensión del proyecto:

Reto2-VentaEntradas
 ├ CineApp/              → Aplicación de escritorio desarrollada en Java
 │   ├ src/              → Código fuente del proyecto
 │   ├ pojos/            → Clases de dominio
 │   ├ dao/              → Acceso a datos
 │   └ connection/       → Conexión con la base de datos
 │
 ├ web/                  → Página web para la simulación de la compra
 │   ├ index.html        → Página principal
 │   ├ login.html        → Página de login
 │   ├ style.css         → Estilos CSS
 │   └ imagenes/         → Recursos gráficos
 │
 ├ bases de datos/       → Scripts de la base de datos
 │   ├ script.sql        → Creación de la base de datos y tablas
 │   ├ inserts.sql       → Inserción de datos de prueba
 │   └ consultas.sql     → Consultas SQL solicitadas en el reto
 │
 ├ documentacion/        → Documentación del diseño
 │   ├ MODELO_ER.jpg     → Diagrama Entidad-Relación
 │   ├ Normalizacion.pdf → Proceso de normalización
 │   └ Universo_del_discurso.pdf
 │
 ├ README.md             → Descripción general del proyecto
 └ LICENSE               → Licencia del proyecto

## Ejecución del proyecto

### Aplicación Java
- Importar el proyecto CineApp en Eclipse.
- Configurar la conexión a la base de datos MySQL.
- Ejecutar la clase principal del proyecto.

### Página web
- Acceder a la carpeta web.
- Abrir el archivo index.html en un navegador.
- Seguir el proceso de simulación de compra de entradas.

## Autor
Proyecto realizado por **Siman**   **jefrrey**  **houdaifa**
1º DAw – Reto 2 - grupo 3 
- Eclipse IDE
- JUnit (tests unitarios)
