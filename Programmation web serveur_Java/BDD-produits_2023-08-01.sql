/* CRÉATION DE LA BASE DE DONNÉES ET DES TABLES */
-- Création de la base de données
DROP DATABASE IF EXISTS Soa;    -- nom provisoire
CREATE DATABASE Soa;
USE Soa;

-- Création de la table Internaute
DROP TABLE IF EXISTS Internaute;
CREATE TABLE Internaute (
    idInternaute int(10) AUTO_INCREMENT PRIMARY KEY
);

-- Création de la table Administrateur
DROP TABLE IF EXISTS Administrateur;
CREATE TABLE Administrateur (
    codeInternaute int(10) PRIMARY KEY,
    codeEmploye int(10) UNIQUE NOT NULL,
    FOREIGN KEY (codeInternaute) REFERENCES Internaute(idInternaute)
);

DROP TABLE IF EXISTS Client;
CREATE TABLE Client (
    codeInternaute int(10) PRIMARY KEY,
    prenom varchar(25) NOT NULL,
    nom varchar(25) NOT NULL,
    dateNaissance date NOT NULL,
    telephone varchar(25),
    courriel varchar(100) UNIQUE NOT NULL,
    motDePasse varchar(25) NOT NULL,
    adresse_perso varchar(255) NOT NULL,    -- adresse personnelle
    ville_perso varchar(100) NOT NULL,
    province_perso varchar(100) NOT NULL,
    pays_perso varchar(100) NOT NULL,
    codepost_perso varchar(100) NOT NULL,
    adresse_livraison varchar(255) NOT NULL,    -- adresse de livraison
    ville_livraison varchar(100) NOT NULL,
    province_livraison varchar(100) NOT NULL,
    pays_livraison varchar(100) NOT NULL,
    codepost_livraison varchar(100) NOT NULL,
    FOREIGN KEY (codeInternaute) REFERENCES Internaute(idInternaute)
);

-- Création de la table Catégorie
DROP TABLE IF EXISTS Categorie;
CREATE TABLE Categorie (
    id int(10) AUTO_INCREMENT PRIMARY KEY,
    nom varchar(255) UNIQUE,
    responsable int(10),        -- Un administrateur peut créer, modifier ou supprimer une catégorie
    FOREIGN KEY (responsable) REFERENCES Administrateur(codeEmploye)
);

-- Création de la table Produits
DROP TABLE IF EXISTS Produits;
CREATE TABLE Produits (
    id int(10) AUTO_INCREMENT PRIMARY KEY,
    nom varchar(255) UNIQUE,
    description varchar(255) NOT NULL,
    categorie int(10) NOT NULL,
    prix float NOT NULL,
    image_url varchar(255),     -- Utiliser l'adresse URL ou le chemin de fichier vers l'image
    responsable int(10),        -- Un administrateur peut créer, modifier ou supprimer un produit
    FOREIGN KEY (categorie) REFERENCES Categorie(id),
    FOREIGN KEY (responsable) REFERENCES Administrateur(codeEmploye)
);

-- Création de la table Panier
DROP TABLE IF EXISTS Panier;
CREATE TABLE Panier (
    idPanier int(10) AUTO_INCREMENT PRIMARY KEY,
    idClient int(10) NOT NULL,
    idProduit int(10) NOT NULL,
    quantite int(10) NOT NULL,
    prixUnitaire float NOT NULL,
    FOREIGN KEY (idClient) REFERENCES Client(codeInternaute),
    FOREIGN KEY (idProduit) REFERENCES Produits(id)
);

-- Remplissage de la BDD
INSERT INTO Internaute (idInternaute) VALUES
(1),
(2),
(3),
(4),
(5);

INSERT INTO Administrateur (codeInternaute, codeEmploye) VALUES
(1, 123456),
(2, 789101),
(3, 800813);

INSERT INTO Categorie (id, nom, responsable) VALUES
(1, 'Shampooings', 123456),
(2, 'Revitalisants', 123456),
(3, 'Masques', 123456),
(4, 'Huiles', 123456),
(5, 'Sérums', 789101),
(6, 'Produits coiffants', 789101),
(7, 'Teintures', 789101);

INSERT INTO Client (
    codeInternaute, prenom, nom, dateNaissance, telephone, 
    courriel, motDePasse, adresse_perso, ville_perso, province_perso, 
    pays_perso, codepost_perso, adresse_livraison, ville_livraison, 
    province_livraison, pays_livraison, codepost_livraison
)
VALUES
(
    4, 'Anne', 'Honyme', '1990-05-15', '514-123-4567', 
    'test@test.com', 'password1234', '123 Main St', 'Montreal', 'Quebec', 
    'Canada', 'H2X 1X5', '456 Elm St', 'Quebec City', 'Quebec', 
    'Canada', 'G1V 2W3'
),
(
    5, 'Jane', 'Shepard', '1985-11-03', '418-888-8888', 
    'jshepard@example.com', 'c1tadel', '789 Maple Ave', 'Vancouver', 'British Columbia', 
    'Canada', 'V6Z 2R9', '123 Citadel St', 'Toronto', 'Ontario', 
    'Canada', 'M4B 1V2'
);

-- Corrections apportées à la table Administrateur pour que les admins puissent se connecter à leur compte
ALTER TABLE Administrateur ADD motDePasse varchar(25);
UPDATE Administrateur SET motDePasse = "admin1234" WHERE codeInternaute = 1;
UPDATE Administrateur SET motDePasse = "java101" WHERE codeInternaute = 2;
UPDATE Administrateur SET motDePasse = "h3h3h3" WHERE codeInternaute = 3;

-- Ajout du champ 'username' avant le champ 'courriel' dans la table 'Client'
ALTER TABLE Client
ADD COLUMN username varchar(8) AFTER nom; -- Le champ 'username' sera placé après la colonne 'nom'

-- Création de la gâchette (trigger)
DELIMITER //

CREATE TRIGGER GenerateUsername
BEFORE INSERT ON Client
FOR EACH ROW
BEGIN
    DECLARE initials VARCHAR(2);
    DECLARE counter INT;
    SET initials = CONCAT(LEFT(NEW.prenom, 2), LEFT(NEW.nom, 2));
    SET counter = 1;

    -- Générer un nom d'utilisateur unique
    WHILE EXISTS (SELECT * FROM Client WHERE username = CONCAT(initials, counter)) DO
        SET counter = counter + 1;
    END WHILE;

    SET NEW.username = CONCAT(initials, counter);
END;
//

DELIMITER ;
