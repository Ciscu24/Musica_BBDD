-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2020 a las 13:54:59
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `musicddbb`
--
CREATE DATABASE IF NOT EXISTS `musicddbb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `musicddbb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nacionalidad` varchar(255) NOT NULL,
  `foto` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `artista`
--

INSERT INTO `artista` (`id`, `nombre`, `nacionalidad`, `foto`) VALUES
(1, 'David Bisbal', 'Español', 'https://ibb.co/tB6VKPG'),
(3, 'Myke Towers', 'Puerto Rico', 'https://e-cdns-images.dzcdn.net/images/artist/5c9f84d97a49eab40cc32e2a8e1e1c11/500x500.jpg'),
(4, 'Bad Bunny', 'Puerto Rico', 'https://los40es00.epimg.net/los40/imagenes/2020/10/20/musica/1603221924_971405_1603223481_noticia_normal.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `cancion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `duracion` int(11) NOT NULL,
  `id_disco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cancion`
--

INSERT INTO `cancion` (`id`, `nombre`, `duracion`, `id_disco`) VALUES
(1, 'Canción Con Yandel', 180, 1),
(11, 'Pa\' Romperla', 194, 1),
(12, 'Bad con Nicky', 202, 1),
(13, 'Como se Siente (Remix)', 227, 1),
(14, 'MIB', 200, 11),
(15, 'Parcerita', 240, 11),
(16, 'En tus planes', 220, 12),
(17, 'Si tu la quieres', 202, 12),
(18, 'Sabras', 205, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disco`
--

CREATE TABLE `disco` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `foto` varchar(500) NOT NULL,
  `id_artista` int(11) NOT NULL,
  `fecha_pro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `disco`
--

INSERT INTO `disco` (`id`, `nombre`, `foto`, `id_artista`, `fecha_pro`) VALUES
(1, 'Las que no iban a salir', 'https://images.squarespace-cdn.com/content/v1/5c6d8645aadd344a28004478/1589225544221-85PJCY401GKYEXD1O5FL/ke17ZwdGBToddI8pDm48kMX3kvVuZF0k0qXuePz5b31Zw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpxJZM6kmh3pZ0wlO5hEh2gNWvjG9u7FN0ikQLI8MIVi1iyM8CW3unfJTJlcD8_7AHU/Formato+horizontal+web+%287%29.png', 4, '2020-05-10'),
(9, 'YHLQMDLG', 'https://www.mor.bo/wp-content/uploads/2020/03/yhlqmdlg-scaled.jpg', 4, '2020-02-29'),
(11, 'Easy Money Baby', 'https://m.media-amazon.com/images/I/71mvafzo+WL._SS500_.jpg', 3, '2020-01-24'),
(12, 'En tus planes', 'https://images-na.ssl-images-amazon.com/images/I/81hpumDk5dL._SL1500_.jpg', 1, '2020-01-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista`
--

CREATE TABLE `lista` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `descripcion` varchar(256) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `lista`
--

INSERT INTO `lista` (`id`, `nombre`, `descripcion`, `id_usuario`) VALUES
(1, 'Reggaetón para correr', 'Estos es una lista para ejercicio', 1),
(2, 'PlayList24', 'Esta es la playlist de Ciscu24', 3),
(8, 'Mejor David Bisbal', 'Esta playlist contiene las mejores canciones de David Bisbal', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista_cancion`
--

CREATE TABLE `lista_cancion` (
  `id_lista` int(11) NOT NULL,
  `id_cancion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `lista_cancion`
--

INSERT INTO `lista_cancion` (`id_lista`, `id_cancion`) VALUES
(1, 1),
(8, 16),
(8, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

CREATE TABLE `suscripcion` (
  `id_usuario` int(11) NOT NULL,
  `id_lista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `suscripcion`
--

INSERT INTO `suscripcion` (`id_usuario`, `id_lista`) VALUES
(1, 1),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `correo` varchar(256) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `foto` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `correo`, `nombre`, `foto`) VALUES
(1, 'aedcarmona@francisco.es', 'Eduardo', 'https://twitter.com/edu_cl123/photo'),
(2, 'Admin', 'Admin', 'admin.png'),
(3, 'Ciscu24', 'Ciscu', 'ciscu24.png'),
(4, 'edu@', 'edu', 'edu.png');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_disco` (`id_disco`);

--
-- Indices de la tabla `disco`
--
ALTER TABLE `disco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_artista` (`id_artista`);

--
-- Indices de la tabla `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `lista_cancion`
--
ALTER TABLE `lista_cancion`
  ADD PRIMARY KEY (`id_lista`,`id_cancion`),
  ADD KEY `lista_cancion_ibfk_1` (`id_cancion`);

--
-- Indices de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD PRIMARY KEY (`id_usuario`,`id_lista`),
  ADD KEY `suscripcion_ibfk_1` (`id_lista`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artista`
--
ALTER TABLE `artista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `cancion`
--
ALTER TABLE `cancion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `disco`
--
ALTER TABLE `disco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `lista`
--
ALTER TABLE `lista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `disco`
--
ALTER TABLE `disco`
  ADD CONSTRAINT `disco_ibfk_1` FOREIGN KEY (`id_artista`) REFERENCES `artista` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `lista`
--
ALTER TABLE `lista`
  ADD CONSTRAINT `lista_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `lista_cancion`
--
ALTER TABLE `lista_cancion`
  ADD CONSTRAINT `lista_cancion_ibfk_1` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lista_cancion_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
