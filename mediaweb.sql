GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `documents`;
CREATE TABLE IF NOT EXISTS `documents` (
  `idDoc` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `author` varchar(60) NOT NULL,
  `typeDoc` varchar(10) NOT NULL,
  `login` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idDoc`),
  KEY `FK_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


INSERT INTO `documents` (`idDoc`, `title`, `author`, `typeDoc`, `login`) VALUES
(1, 'SFML Game development', 'Artur Moreira, Henrik Vogelius Hansson, Jan Haller', 'Livre', NULL),
(3, 'Captain America : Civil War', 'Marvel', 'CD', NULL),
(4, 'The Beatles Anthology 3', 'The Beatles', 'DVD', 'abo1'),
(6, 'livre', 'auteur', 'Livre', NULL);


DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `typeUser` enum('biblioth�caire','abonn�') NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `users` (`login`, `password`, `typeUser`) VALUES
('abo1', 'abo1', 'abonn�'),
('biblio1', 'biblio1', 'biblioth�caire');

ALTER TABLE `documents`
  ADD CONSTRAINT `FK_login` FOREIGN KEY (`login`) REFERENCES `users` (`login`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;
