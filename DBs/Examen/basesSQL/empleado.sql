-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-04-2018 a las 04:15:47
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
-- Base de datos: `empleado`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarDatos` (`dni` VARCHAR(10), `nombre` VARCHAR(25), `apellidos` VARCHAR(25), `ciudad` VARCHAR(25), `sueldo` VARCHAR(25))  begin
	insert into empleado values(dni,nombre,apellidos,ciudad,sueldo);
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `ciudad` varchar(25) NOT NULL,
  `sueldo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`dni`, `nombre`, `apellidos`, `ciudad`, `sueldo`) VALUES
('123132', 'adfasdf', 'adsfasfd', 'asdfasdf', '123412'),
('12345', 'Eduardo', 'PanYagua', 'no tiene', '1'),
('11111', 'asdfsss', 'ssssss', 'sssss', '333'),
('333333', 'sdfgsdfg', 'sfdgsdfg', 'sdfgsfg', '45'),
('1123123', 'asdfasdf', 'asdfasdf', 'asdfasfd', '12321'),
('12312312', 'asdfasdf', 'asdfasd', 'asdfasdf', '123123'),
('1231123', 'adsfaf', 'asdfasdf', 'asdfasfd', '12312123');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
