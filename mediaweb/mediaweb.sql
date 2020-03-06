SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `documents` (
  `idDoc` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author` varchar(60) NOT NULL,
  `typeDoc` int(11) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idDoc`),
  KEY `FK_login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `typeUser` enum('bibliothecaire','abonne') NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1;

ALTER TABLE `documents`
  ADD CONSTRAINT `FK_login` FOREIGN KEY (`login`) REFERENCES `users` (`login`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

INSERT INTO `documents` (`idDoc`, `title`, `author`, `typeDoc`, `login`) VALUES
(1, 'Tintin en Amérique', 'Georges Rémi', 1, NULL),
(2, 'Pirates des Caraïbes', 'Gore Verbinski', 2, NULL),
(3, 'Nyxia Tome 3', 'Tayc', 3, NULL),
(4, 'Naruto', 'Masashi Kishimoto', 1, NULL);

INSERT INTO `users` (`login`, `password`, `typeUser`) VALUES
('user', 'user', 'abonne'),
('admin', 'admin', 'bibliothecaire');