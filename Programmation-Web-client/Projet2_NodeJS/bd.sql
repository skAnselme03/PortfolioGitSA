-- Bd superhero

CREATE DATABASE bd_superhero.
use bd_superhero.

CREATE TABLE IF NOT EXISTS `heros` (
`id` BIGINT UNSIGNED AUTO_INCREMENT,
`prenom` VARCHAR (255) NOT NULL,
`nom` VARCHAR (255) NOT NULL,
`nomSuperHero` VARCHAR (255) NOT NULL,
`ville` VARCHAR (255) NOT NULL,
PRIMARY KEY (`id`))
ENGINE = InnoDB.
INSERT INTO `bd_superhero`.`heros` (`prenom`, `nom`, `nomSuperHero`,`ville`) VALUES ('Peter',
'Parker', 'spiderman','new york');
INSERT INTO `bd_superhero`.`heros` (`prenom`, `nom`, `nomSuperHero`,`ville`) VALUES ('tony',
'Stark', 'ironman','long island');