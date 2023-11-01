DROP DATABASE IF EXISTS projet_jeu;
CREATE DATABASE projet_jeu;
USE projet_jeu;



--
-- Table structure for table equipes
--

DROP TABLE IF EXISTS equipes;

CREATE TABLE equipes (
  idEquipes int NOT NULL AUTO_INCREMENT,
  nom varchar(45) NOT NULL,
  budget double NOT NULL,
  nbPoints int NOT NULL,
  nbAttaque int NOT NULL CHECK (nbAttaque <= 11), -- contraindre un nombre maximum de 11 attaquant par équipe
  nbDefense int NOT NULL CHECK (nbDefense <= 11),-- contraindre un nombre maximum de 11 defenseur par équipe
  nbGardien int NOT NULL CHECK (nbDefense <= 2), -- contraindre un nombre maximum de 2 gardien par équipe
  nbEntraineur int NOT NULL CHECK (nbDefense = 1), -- contraindre un nombre maximum de 1 entraineur par équipe
  PRIMARY KEY (idEquipes)
);

--
-- Table structure for table `attaque`
--

DROP TABLE IF EXISTS attaque;

CREATE TABLE attaque (
  idAttaque int NOT NULL AUTO_INCREMENT,
  nom varchar(45) NOT NULL,
  prenom varchar(45) NOT NULL,
  dateNais varchar(45) NOT NULL,
  salaire double NOT NULL,
  talent ENUM('Débutant', 'Intermédiaire', 'Expérimenté') DEFAULT 'Débutant',
  nbPoints int NOT NULL,
  position ENUM('','Avant', 'Arrière') DEFAULT '',
  equipe int NOT NULL DEFAULT 0,
  PRIMARY KEY (idAttaque),
  CONSTRAINT fk_attaque_Equipe FOREIGN KEY (equipe) REFERENCES equipes(idEquipes)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

--
-- Table structure for table defense
--

DROP TABLE IF EXISTS defense;

CREATE TABLE defense (
  idDefense int NOT NULL AUTO_INCREMENT,
  nom varchar(45) NOT NULL,
  prenom varchar(45) NOT NULL,
  dateNais varchar(45) NOT NULL,
  salaire double NOT NULL,
  talent ENUM('Débutant', 'Intermédiaire', 'Expérimenté') DEFAULT 'Débutant',
  nbPoints int NOT NULL,
  position ENUM('','Avant', 'Arrière') DEFAULT '',
  equipe int NOT NULL DEFAULT 0,
  PRIMARY KEY (idDefense),
  CONSTRAINT fk_defense_Equipe FOREIGN KEY (equipe) REFERENCES equipes(idEquipes)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

--
-- Table structure for table entraineur
--

DROP TABLE IF EXISTS entraineur;

CREATE TABLE entraineur (
  idEntraineur int NOT NULL AUTO_INCREMENT,
  nom varchar(45) NOT NULL,
  prenom varchar(45) NOT NULL,
  dateNais varchar(45) NOT NULL,
  salaire double NOT NULL,
  talent ENUM('Débutant', 'Intermédiaire', 'Expérimenté') DEFAULT 'Débutant',
  nbPoints int  NOT NULL,
  position ENUM('','Avant', 'Arrière') DEFAULT '',
  equipe int  NOT NULL DEFAULT 0,
  PRIMARY KEY (idEntraineur),,
  CONSTRAINT fk_entraineur_Equipe FOREIGN KEY (equipe) REFERENCES equipes(idEquipes)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


--
-- Table structure for table gardien
--

DROP TABLE IF EXISTS gardien;

CREATE TABLE gardien (
  idGardien int NOT NULL AUTO_INCREMENT,
  nom varchar(45) NOT NULL,
  prenom varchar(45) NOT NULL,
  dateNais varchar(45) NOT NULL,
  salaire double NOT NULL,
  talent ENUM('Débutant', 'Intermédiaire', 'Expérimenté') DEFAULT 'Débutant',
  nbPoints int NOT NULL,
  position ENUM('','Avant', 'Arrière') DEFAULT '',
  equipe int NOT NULL DEFAULT 0,
  PRIMARY KEY (idGardien),
  CONSTRAINT fk_gardien_Equipe FOREIGN KEY (equipe) REFERENCES equipes(idEquipes)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- INSERTION DANS LES TABLES
INSERT INTO equipes (nom,
                     budget,
                     nbPoints,
                     nbAttaque,
                     nbDefense,
                     nbGardien,
                     nbEntraineur)
VALUES
('Les-Champions',9007750,0,0,0,0,0),
('Les-Hasardeux',1000000,0,0,0,0,0),
('Les-Mielleux',5000000,0,0,0,0,0),
('Les-Couillons',200500,0,0,0,0,0);

INSERT INTO attaque (nom,
                     prenom,
                     dateNais,
                     salaire,
                     talent,
                     nbPoints,
                     position,
                     equipe)
VALUES
('Le-Canari', 'Titi', '1980-07-21', 500000,'Débutant', 50, 'Avant','1'),
('Le-Chat', 'Gros-Minet', '1980-07-21', 300000,'Intérmediaire',100,'Arrière','1'),
('Le-Chat', 'Tom', '1979-07-21', 450000,'Débutant',50,'Arrière','1'),
('La-Souris', 'Jerry', '1975-07-21', 100000,'Expérimenté', 150,'Avant','1');

-- requete pour JavaFx sans trigger
 /*UPDATE equipes JOIN attaque ON equipes.idEquipes = attaque.equipe 
  SET equipes.nbAttaque = (SELECT COUNT(*) FROM attaque),  +
        equipes.nbPoints = (SELECT SUM(nbPoints) FROM attaque),  +
        equipes.budget = equipes.budget - (SELECT SUM(salaire) 
  FROM attaque) 
  GROUP BY idEquipes;*/


/**             GACHETTE 1
    créer une gachette qui incrémentera le champ nbAttaque de 1 de la table equipes a chaque fois qu'un attaquant est ajouter à la table equipe. Vérifie si l'équipe correspondante existe dans la table "equipes", avant l'ajout. le nombre d'attaque ne doit pas depasser le nombre d'attaque correspondant.
*/
DELIMITER $$
CREATE TRIGGER increment_nbAttaque
--  la gachette doit se déclencher après l'insertion d'une ligne dans la table "attaque"
AFTER INSERT ON attaque
-- la gachette est exécutée une fois pour chaque ligne insérée dans la table "attaque".
FOR EACH ROW
BEGIN
    DECLARE nbAttaqueEquipe INT;
    -- récupère le nombre d'attaquants actuel pour l'équipe correspondante et stocke la valeur dans la variable nbAttaqueEquipe
    SELECT nbAttaque INTO nbAttaqueEquipe FROM equipes WHERE idEquipes = NEW.equipe;
    -- vérifie si le nombre d'attaquants actuel est inférieur à 11 pour l'équipe correspondante
    IF nbAttaqueEquipe < 12 THEN
      -- incrémente le champ nbAttaque de la table "equipes" de 1 pour l'équipe correspondante
        UPDATE equipes SET nbAttaque = nbAttaque + 1 WHERE idEquipes = NEW.equipe;
    END IF;
END$$
DELIMITER ;

/** GACHETTE 2
    créer une gachette qui ajoutera la valeur de nbPoints de l'attaquant ajouté au champ nbPoints correspondant de l'équipe à laquelle il appartient.. De plus, vérifie si l'équipe correspondante existe dans la table "equipes", avant l'ajout.
*/
DELIMITER $$
CREATE TRIGGER add_nbPoints
-- la gachette est exécutée une fois pour chaque ligne insérée dans la table "attaque".
AFTER INSERT ON attaque
-- la gachette est exécutée une fois pour chaque ligne insérée dans la table "attaque"
FOR EACH ROW
BEGIN
    -- Les variables idEquipe et nbPointsEquipe sont déclarées pour stocker l'ID de l'équipe 
    -- et le nombre de points actuel de l'équipe correspondante.
    DECLARE idEquipe INT;
    DECLARE nbPointsEquipe INT;
    -- récupère l'ID de l'équipe et le nombre de points actuel pour l'équipe correspondante et stocke les valeurs dans les variables idEquipe et nbPointsEquipe
    SELECT equipe, nbPoints INTO idEquipe, nbPointsEquipe FROM equipes WHERE idEquipes = NEW.equipe;
    -- si l'équipe correspondante existe dans la table "equipes
    IF idEquipe IS NOT NULL THEN
      -- ajoute la valeur de nbPoints de l'attaquant ajouté au champ nbPoints de l'équipe correspondante.
        UPDATE equipes SET nbPoints = nbPoints + NEW.nbPoints WHERE idEquipes = NEW.equipe;
    END IF;
END$$
DELIMITER ;


/** GACHETTE 3
   créer une gachette qui soustraite du budget de la table equipes le salaire de la table attaque lorsqu'un attaque est ajouter a la table equipe. De plus, vérifie si l'équipe correspondante existe dans la table "equipes", avant l'ajout. Enfin, si le salaire de l'attaque est supérieur au budget de l'equipe, l'ajout de l'attaque sera impossible
*/
DELIMITER $$
CREATE TRIGGER update_budget
-- la gachette est exécutée une fois pour chaque ligne insérée dans la table "attaque".
BEFORE INSERT ON attaque
FOR EACH ROW
BEGIN
    DECLARE budget_equipe DOUBLE;
    -- sélectionne le budget de l'équipe correspondante à l'attaquant ajouté et stocke la valeur dans la variable budget_equipe.
    SET budget_equipe = (SELECT budget FROM equipes WHERE idEquipes = NEW.equipe);
    -- vérifie si l'équipe correspondante existe dans la table "equipes" avant l'ajout
    IF (SELECT COUNT(*) FROM equipes WHERE idEquipes = NEW.equipe) = 1 THEN
      -- vérifie si le budget de l'équipe est suffisant pour payer le salaire de l'attaquant.
      IF budget_equipe >= NEW.salaire THEN
          -- soustrait le salaire de l'attaquant au champ budget de la table "equipes" pour l'équipe correspondante
              UPDATE equipes SET budget = budget - NEW.salaire WHERE idEquipes = NEW.equipe;
      ELSE
          SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Le salaire de l''attaquant est supérieur au budget de l''équipe.';
      END IF;
    END IF;
END$$
DELIMITER ;


