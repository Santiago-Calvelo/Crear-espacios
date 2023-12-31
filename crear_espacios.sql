-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-10-2023 a las 03:17:03
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `crear_espacios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `DNI` int(8) NOT NULL,
  `NomApel` varchar(35) DEFAULT NULL,
  `Direccion` varchar(35) DEFAULT NULL,
  `Telefono` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`DNI`, `NomApel`, `Direccion`, `Telefono`) VALUES
(12345678, 'Jose Pepito', 'Casa 123', 12344567),
(22222222, 'sads', 'dwa 12', 1111111111),
(59874532, 'Emanuel Grassi', 'Av Siempreviva 123', 1197235489);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `CodPago` int(11) NOT NULL,
  `Monto` int(11) DEFAULT NULL,
  `Metodo` varchar(30) DEFAULT NULL,
  `CodRes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`CodPago`, `Monto`, `Metodo`, `CodRes`) VALUES
(3, 50000, 'Tarjeta', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promos`
--

CREATE TABLE `promos` (
  `CodPromo` int(11) NOT NULL,
  `Contenido` varchar(120) NOT NULL,
  `Precio` int(11) DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promos`
--

INSERT INTO `promos` (`CodPromo`, `Contenido`, `Precio`, `Stock`) VALUES
(1, 'asd', 123, 200),
(2, 'asd asd', 255, 123),
(3, '43 SIllas 53 Cafeteras 3 Trampolines', 50000, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `CodRes` int(4) NOT NULL,
  `FechaEntrega` date DEFAULT NULL,
  `FechaRetiro` date DEFAULT NULL,
  `CodCli` int(8) NOT NULL,
  `CodPromo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`CodRes`, `FechaEntrega`, `FechaRetiro`, `CodCli`, `CodPromo`) VALUES
(2, '2023-11-19', '2023-11-20', 59874532, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`CodPago`),
  ADD KEY `ibfk_2` (`CodRes`);

--
-- Indices de la tabla `promos`
--
ALTER TABLE `promos`
  ADD PRIMARY KEY (`CodPromo`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`CodRes`),
  ADD KEY `CodCli` (`CodCli`),
  ADD KEY `fk2` (`CodPromo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `CodPago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `promos`
--
ALTER TABLE `promos`
  MODIFY `CodPromo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `CodRes` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `ibfk_2` FOREIGN KEY (`CodRes`) REFERENCES `reserva` (`CodRes`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk2` FOREIGN KEY (`CodPromo`) REFERENCES `promos` (`CodPromo`),
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`CodCli`) REFERENCES `clientes` (`DNI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
