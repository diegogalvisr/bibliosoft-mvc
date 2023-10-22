-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-10-2023 a las 15:44:13
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdbibliosoft`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admins`
--

CREATE TABLE `admins` (
  `id_admin` int(11) NOT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `cargo` int(11) DEFAULT NULL,
  `imagen` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `admins`
--

INSERT INTO `admins` (`id_admin`, `usuario`, `clave`, `nombre`, `apellido`, `direccion`, `cargo`, `imagen`) VALUES
(23001, 'admin', 'admin', 'JUAN DIEGO', 'GALVIS ROMERO', 'CALLE 22A#21-19', 1, NULL),
(23002, 'JUAN', 'juan', 'YORGEN ELIECER', 'GALVIS ROMERO ', 'CALLE 22A#21-19', 1, NULL),
(23004, 'lromero46', 'lilaromero78', 'LILA', 'ROMERO', 'CLL 22#21A-19', 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`id`, `nombre`) VALUES
(1, 'MATEMATICAS'),
(2, 'ESPAÑOL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`id_autor`, `nombre`) VALUES
(1, 'Gabriel García Márquez'),
(2, 'Laura Esquivel'),
(3, 'Héctor Abad Faciolince'),
(4, 'Isabel Allende'),
(5, 'Fernando Vallejo'),
(6, 'Alvaro Mutis'),
(7, 'Juan Gabriel Vásquez'),
(8, 'Julia de Burgos'),
(9, 'Jorge Luis Borges'),
(10, 'Pablo Neruda'),
(11, 'Mario Vargas Llosa'),
(12, 'Carlos Fuentes'),
(13, 'Julio Cortázar'),
(14, 'Piedad Bonnett'),
(15, 'Germán Castro Caicedo'),
(16, 'Gloria Anzaldúa'),
(17, 'Rafael Pombo'),
(18, 'José Asunción Silva'),
(19, 'Joaquín Gallegos Lara'),
(20, 'Fernando Pessoa'),
(21, 'Claribel Alegría'),
(22, 'Rigoberta Menchú'),
(23, 'Eduardo Galeano'),
(24, 'Pablo Escobar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargos`
--

CREATE TABLE `cargos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cargos`
--

INSERT INTO `cargos` (`id`, `nombre`) VALUES
(1, 'ADMINISTRADOR'),
(2, 'AUXILIAR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'FICCION'),
(2, 'ACCION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `id_editorial` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `editorial`
--

INSERT INTO `editorial` (`id_editorial`, `nombre`) VALUES
(1, 'Editorial Planeta Colombia'),
(2, 'Penguin Random House Grupo Editorial Colombia'),
(3, 'Editorial Norma'),
(4, 'Grupo Editorial Oveja Negra'),
(5, 'Editorial SM Colombia'),
(6, 'Editorial Pontificia Universidad Javeriana'),
(7, 'Ediciones B Colombia'),
(8, 'Alfaguara Colombia'),
(9, 'Grupo Editorial Educatex'),
(10, 'Editorial Tragaluz'),
(11, 'Editorial Panamericana'),
(12, 'Ediciones Universidad de Salamanca Colombia'),
(13, 'Ediciones Aurora'),
(14, 'Editorial La Silueta'),
(15, 'Editorial Universidad del Rosario'),
(16, 'Ediciones Universidad de Antioquia'),
(17, 'Editorial Universidad Nacional de Colombia'),
(18, 'Editorial Magisterio'),
(19, 'Ediciones Uniandes'),
(20, 'Editorial Norma Comics'),
(21, 'Ediciones La Línea'),
(22, 'Editorial Universidad del Norte'),
(23, 'Editorial Exlibris'),
(24, 'Editorial Rey Naranjo'),
(25, 'Editorial Catapulta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id`, `nombre`) VALUES
(1, 'PRESTADO'),
(2, 'CULMINADO'),
(3, 'DISPONIBLE'),
(4, 'NO DISPONIBLE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grados`
--

CREATE TABLE `grados` (
  `id` int(11) NOT NULL,
  `numero` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grados`
--

INSERT INTO `grados` (`id`, `numero`) VALUES
(1, '101'),
(2, '102'),
(3, '103'),
(4, '104'),
(5, '105'),
(6, '201'),
(7, '202'),
(8, '0000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id_libro` int(11) NOT NULL,
  `isbn` int(100) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_editorial` int(100) DEFAULT NULL,
  `id_autor` int(100) DEFAULT NULL,
  `tipo_libro` char(20) DEFAULT NULL,
  `precio` int(255) DEFAULT NULL,
  `contMaterial` text DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `cantidad` int(100) DEFAULT NULL,
  `prestado` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id_libro`, `isbn`, `titulo`, `id_editorial`, `id_autor`, `tipo_libro`, `precio`, `contMaterial`, `categoria`, `cantidad`, `prestado`) VALUES
(24001, 65899527, 'TORK WA', 2, 2, 'C', 35800, 'LAPICES', 1, 3, 2),
(24003, 184811, 'HOLA PUES', 2, 1, 'c', 5899, 'Materiales', 2, 5, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multas`
--

CREATE TABLE `multas` (
  `id_multa` int(11) NOT NULL,
  `estado` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `multas`
--

INSERT INTO `multas` (`id_multa`, `estado`) VALUES
(1, 'ACTIVA'),
(2, 'INACTIVA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id_prestamo` int(11) NOT NULL,
  `tipo_prestamo` char(2) DEFAULT 'A',
  `id_admin` int(10) DEFAULT NULL,
  `id_usuario` int(10) DEFAULT NULL,
  `id_libro` int(10) DEFAULT NULL,
  `asignatura` int(11) DEFAULT NULL,
  `fecha_inicial` date DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `acta` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`id_prestamo`, `tipo_prestamo`, `id_admin`, `id_usuario`, `id_libro`, `asignatura`, `fecha_inicial`, `fecha_final`, `estado`, `acta`) VALUES
(25001, 'A', 23004, 26003, 24003, 1, '2023-10-20', '2023-10-25', 2, 0),
(25003, 'A', 23001, 26003, 24001, 1, '2023-10-22', '2023-10-25', 1, 1),
(25004, 'B', 23001, 26004, 24003, 1, '2023-10-22', '2023-10-25', 1, 1),
(25005, 'T', 23001, 26003, 24001, 2, '2023-10-22', '2023-10-25', 1, 1),
(25006, 'T', 23001, 26003, 24001, 2, '2023-10-22', '2023-10-25', 1, 1),
(25007, 'A', 23001, 26006, 24001, 2, '2023-10-22', '2023-10-29', 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `user_generico` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `telefonoF` varchar(255) DEFAULT NULL,
  `grado` int(10) DEFAULT NULL,
  `id_multa` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `user_generico`, `nombre`, `apellido`, `direccion`, `telefono`, `telefonoF`, `grado`, `id_multa`) VALUES
(26003, '20233359', 'JUAN DIEGO', 'GALVIS ROMERO', 'CLL 22 ', '3143480005', '3204872818', 7, NULL),
(26004, '20232241', 'JUAN ELIECER', 'Galvis Romero', 'Calle 22a #21a -19 Mujeres del futuro, Aguas Calientes, La libertad', 'A3013256544', '223554222', 8, NULL),
(26005, '20236786', 'YEIDER', 'GONZALEZ', 'CLL 22A #21-58', '3002568974', '301254789', 2, NULL),
(26006, '20239029', 'FELIPE', 'OSORIO', 'CLL LIBERTAD', '3254888987', '1457886', 4, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `CARGOS_USUARIOS_FK` (`cargo`);

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id_autor`);

--
-- Indices de la tabla `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`id_editorial`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `grados`
--
ALTER TABLE `grados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id_libro`),
  ADD KEY `AUTOR_LIBRO_FK` (`id_autor`),
  ADD KEY `EDITORIAL_LIBRO_FK` (`id_editorial`),
  ADD KEY `CATEGORIA_LIBRO_FK` (`categoria`);

--
-- Indices de la tabla `multas`
--
ALTER TABLE `multas`
  ADD PRIMARY KEY (`id_multa`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id_prestamo`),
  ADD KEY `ADMINS_PRESTAMOS_FK` (`id_admin`),
  ADD KEY `LIBROS_PRESTAMOS_FK` (`id_libro`),
  ADD KEY `USUARIOS_PRESTAMOS_FK` (`id_usuario`),
  ADD KEY `ESTADO_PRESTAMOS_FK` (`estado`),
  ADD KEY `ASIGNATURA_PRESTAMOS_FK` (`asignatura`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `GRADOS_USUARIOS_FK` (`grado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admins`
--
ALTER TABLE `admins`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23005;

--
-- AUTO_INCREMENT de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `cargos`
--
ALTER TABLE `cargos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `editorial`
--
ALTER TABLE `editorial`
  MODIFY `id_editorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `grados`
--
ALTER TABLE `grados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24004;

--
-- AUTO_INCREMENT de la tabla `multas`
--
ALTER TABLE `multas`
  MODIFY `id_multa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id_prestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25008;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26007;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `CARGOS_USUARIOS_FK` FOREIGN KEY (`cargo`) REFERENCES `cargos` (`id`);

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `AUTOR_LIBRO_FK` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`),
  ADD CONSTRAINT `CATEGORIA_LIBRO_FK` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `EDITORIAL_LIBRO_FK` FOREIGN KEY (`id_editorial`) REFERENCES `editorial` (`id_editorial`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `ADMINS_PRESTAMOS_FK` FOREIGN KEY (`id_admin`) REFERENCES `admins` (`id_admin`),
  ADD CONSTRAINT `ASIGNATURA_PRESTAMOS_FK` FOREIGN KEY (`asignatura`) REFERENCES `asignatura` (`id`),
  ADD CONSTRAINT `ESTADO_PRESTAMOS_FK` FOREIGN KEY (`estado`) REFERENCES `estado` (`id`),
  ADD CONSTRAINT `LIBROS_PRESTAMOS_FK` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id_libro`),
  ADD CONSTRAINT `USUARIOS_PRESTAMOS_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `GRADOS_USUARIOS_FK` FOREIGN KEY (`grado`) REFERENCES `grados` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
