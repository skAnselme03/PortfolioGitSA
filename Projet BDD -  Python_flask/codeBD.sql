/********************************************************************/
/****			Création de la Base de données				      ***/
/********************************************************************/
CREATE DATABASE PetitKangourou;
USE PetitKangourou;


/********************************************************************/
/****    			Création de la table Users     		          ***/
/********************************************************************/
DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
idUser INTEGER NOT NULL AUTO_INCREMENT, 
Prenom VARCHAR(32) DEFAULT'', 
Nom VARCHAR(32) DEFAULT'', 
NumeroTelephone VARCHAR(20) DEFAULT'', 
Courriel VARCHAR(50) DEFAULT'', 
Username VARCHAR(30) DEFAULT'', 
Password VARCHAR(30) DEFAULT'', 
PhotoProfil varchar(200) DEFAULT'', 
CodeAdresse INT, 
SoldePoints INT DEFAULT 1000,
PRIMARY KEY (idUser), 
FOREIGN KEY (CodeAdresse) REFERENCES Adresses(idAdresse) 
ON DELETE CASCADE ON update CASCADE 
); 


/********************************************************************/
/****    			Création de la table Adresses                 ***/
/********************************************************************/
DROP TABLE IF EXISTS Adresses;
CREATE TABLE Adresses( 
idAdresse INTEGER NOT NULL AUTO_INCREMENT, 
NumCivique INTEGER, 
Rue VARCHAR(32) DEFAULT'', 
Ville VARCHAR(32) DEFAULT'', 
Provinces ENUM('QC', 'ON'), 
CodePostal CHAR(7) DEFAULT'', 
Pays ENUM('CAN') DEFAULT'CAN', 
PRIMARY KEY (idAdresse) 
); 

/********************************************************************/
/****    			Création de la table Produits                 ***/
/********************************************************************/
DROP TABLE IF EXISTS Produits;
CREATE TABLE Produits( 
idProduit INTEGER NOT NULL AUTO_INCREMENT, 
Titre VARCHAR(100), 
Description VARCHAR(300), 
Etat ENUM('Neuf','Comme neuf','Bon état', 'Assez bon état' ), 
PhotoProduit varchar(200) DEFAULT'', 
Prix integer NOT NULL,
Categorie ENUM('Jouets en bois', 'Livres', 'Poupées et figurines', 'Voitures, trains et avions', 'Jeux imitation', 
    'Personnages de film et émission', 'Jeux éducatifs', 'Jouets pour touts petits', 'Jeux de société', 
    'Jeux extérieur', 'Instruments de musique'), 
CategorieAge ENUM('0-6 mois', '6-12 mois', '12-24 mois', '2-5 ans'), 
ValeurPoint INT,
CodeVendeur INTEGER NOT NULL, 
PRIMARY KEY (idProduit), 
FOREIGN KEY (CodeVendeur) REFERENCES Users(idUser) 
ON DELETE CASCADE ON update CASCADE 
); 

/********************************************************************/
/****    			Création de la table Wishlist                 ***/
/********************************************************************/
DROP TABLE IF EXISTS Wishlist;
CREATE TABLE Wishlist( 
idWishlist INTEGER NOT NULL AUTO_INCREMENT, 
CodeProduit INTEGER NOT NULL, 
CodeUser INTEGER NOT NULL, 
PRIMARY KEY (idWishlist), 
FOREIGN KEY (CodeUser) REFERENCES Users(idUser), 
FOREIGN KEY (CodeProduit) REFERENCES Produits(idProduit) 
ON DELETE CASCADE ON update CASCADE 
); 

/********************************************************************/
/****    			Création de la table Transactions             ***/
/********************************************************************/
DROP TABLE IF EXISTS Transactions;
CREATE TABLE Transactions( 
idTransaction INTEGER NOT NULL AUTO_INCREMENT, 
DateTransaction DATETIME, 
Statut ENUM ('Réservé', 'Vendu') DEFAULT 'Réservé', 
CodeAcheteur INTEGER NOT NULL, 
CodeProduit INTEGER NOT NULL, 
PRIMARY KEY (idTransaction), 
FOREIGN KEY (CodeAcheteur) REFERENCES Users(idUser) 
ON DELETE CASCADE ON update CASCADE, 
FOREIGN KEY (CodeProduit) REFERENCES Produits(idProduit) 
ON DELETE CASCADE ON update CASCADE 
); 

/********************************************************************/
/****    			Création de la table Avis                     ***/
/********************************************************************/
DROP TABLE IF EXISTS Avis;
CREATE TABLE Avis( 
idAvis INTEGER NOT NULL AUTO_INCREMENT, 
Message VARCHAR(300), 
Note ENUM('1','2','3','4','5'), 
CodeTransaction INTEGER, 
PRIMARY KEY (idAvis), 
FOREIGN KEY (CodeTransaction) REFERENCES Transactions(idTransaction) 
ON DELETE CASCADE ON update CASCADE 
); 

/********************************************************************/
/****   	Création de la table ProduitsArchives                 ***/
/********************************************************************/
CREATE TABLE ProduitsArchives( 
idProduit INTEGER NOT NULL, 
Titre VARCHAR(100), 
PhotoProduit varchar(200) DEFAULT'', 
ValeurPoint INT,
CodeVendeur INTEGER NOT NULL,
CodeAcheteur INTEGER NOT NULL, 
PRIMARY KEY (idProduit)
);


/********************************************************************/
/****    	   		Gâchette calculer_points                      ***/
/********************************************************************/

CREATE TRIGGER calculer_points BEFORE INSERT ON produits 
FOR EACH ROW 
BEGIN 
    DECLARE points integer; 
    SET points = NEW.prix; 
    
    IF NEW.etat = 'Neuf' THEN 
        SET points = points * 0.9; 
    END IF; 
	IF NEW.etat = 'Comme neuf' THEN 
        SET points = points * 0.7; 
    END IF; 
	IF NEW.etat = 'Bon état' THEN 
        SET points = points * 0.5; 
    END IF; 
	IF NEW.etat = 'Assez bon état' THEN 
        SET points = points *0.3; 
    END IF; 
    SET NEW.ValeurPoint = points * 1000; 
END// 
DELIMITER ; 


/********************************************************************/
/****    	   	Gâchette update_solde_points                      ***/
/********************************************************************/

DELIMITER // 
CREATE TRIGGER update_solde_points 
AFTER UPDATE ON transactions 
FOR EACH ROW 
BEGIN 
	DECLARE points integer;
    DECLARE vendeur integer;
    SET points = (SELECT ValeurPoint FROM ProduitsArchives WHERE idProduit = New.CodeProduit);
    SET vendeur = (SELECT CodeVendeur FROM ProduitsArchives WHERE idProduit = New.CodeProduit);
    IF NEW.Statut = "Vendu" THEN 
		UPDATE users SET SoldePoints = SoldePoints - points WHERE idUser = NEW.CodeAcheteur; 
		UPDATE users SET SoldePoints = SoldePoints + points WHERE idUser =  vendeur; 
    END IF; 
END// 
DELIMITER ; 


/********************************************************************/
/****    	   		Gâchette archive_produit                      ***/
/********************************************************************/

DELIMITER // 
CREATE TRIGGER archive_produit
BEFORE UPDATE ON transactions 
FOR EACH ROW 
BEGIN 
    DECLARE titre_jouet varchar(100);
    DECLARE valeur_point_jouet integer;
    DECLARE photo_jouet varchar(200);
    DECLARE code_vendeur_jouet integer;
	
    SET code_vendeur_jouet = (SELECT codeVendeur from Produits where idProduit = New.CodeProduit);
	SET titre_jouet = (SELECT Titre from Produits where idProduit = New.CodeProduit);
    SET photo_jouet = (SELECT PhotoProduit from Produits where idProduit = New.CodeProduit);
    SET valeur_point_jouet = (SELECT ValeurPoint from Produits where idProduit = New.CodeProduit);
    IF NEW.Statut = "Vendu" THEN 
        INSERT INTO ProduitsArchives SET idProduit=New.CodeProduit, codeVendeur=code_vendeur_jouet, CodeAcheteur=New.CodeAcheteur, Titre=titre_jouet, PhotoProduit=photo_jouet, ValeurPoint=valeur_point_jouet;
		SET FOREIGN_KEY_CHECKS = 0;
		DELETE FROM Produits WHERE idProduit = NEW.CodeProduit;
		SET FOREIGN_KEY_CHECKS = 1;

    END IF; 
END// 
DELIMITER ; 
