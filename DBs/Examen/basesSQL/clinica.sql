-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-04-2018 a las 04:15:25
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinica`
--

CREATE TABLE `clinica` (
  `id` int(6) UNSIGNED NOT NULL,
  `nombreA` varchar(20) DEFAULT NULL,
  `especie` varchar(20) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `nombreD` varchar(20) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usuario` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clinica`
--

INSERT INTO `clinica` (`id`, `nombreA`, `especie`, `edad`, `sexo`, `nombreD`, `telefono`, `fecha`, `usuario`) VALUES
(1, '', 'Gato', 3, 'M', 'Joe', '', '2018-03-28 13:16:31', ''),
(2, '', 'asdf', 22, 'H', 'asdfsadf', '', '2018-03-28 13:18:09', ''),
(3, 'asdfasdf', 'ewewe', 32, 'H', 'asdfasdf', '333322', '2018-03-28 13:20:01', ''),
(4, 'asdfasdf', 'ewewe', 32, 'H', 'asdfasdf', '333322', '2018-03-28 13:21:20', 'optima'),
(5, 'JaBALI', 'HIPO', 23, 'H', 'ASDFASDF', '434344', '2018-03-28 13:22:01', 'optima'),
(6, 'Ultimo', 'Ultima', 66, 'M', 'UltimoDueÃ±o', '66666666', '2018-03-28 13:25:28', 'optima');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clinica`
--
ALTER TABLE `clinica`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clinica`
--
ALTER TABLE `clinica`
  MODIFY `id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
