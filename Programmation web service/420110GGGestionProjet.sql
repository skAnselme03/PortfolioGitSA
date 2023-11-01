-- Supprime la base de données si elle existe
DROP DATABASE IF EXISTS 420110GGGestionProjet;

-- Crée la base de données
CREATE DATABASE 420110GGGestionProjet;

-- Utiliser la bdd
USE 420110GGGestionProjet;

-- Table Utilisateurs
-- DROP TABLE IF EXISTS Utilisateurs;
CREATE TABLE Utilisateurs (
    IdUtilisateur INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(45) NOT NULL,
    Prenom VARCHAR(45) NOT NULL,
    Username VARCHAR(45) NOT NULL,
    Password VARCHAR(15) NOT NULL,
    Courriel VARCHAR(45) UNIQUE NOT NULL,
    Titre VARCHAR(45) NOT NULL,
    Departement VARCHAR(45) NOT NULL
);



-- Table Projets
-- DROP TABLE IF EXISTS Projets;
CREATE TABLE Projets (
    IdProjet INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CodeProjet VARCHAR(100) UNIQUE,
    Nom VARCHAR(45) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    DateDebut DATE NOT NULL,
    DateFin DATE NOT NULL,
    DateTermine DATE,
    Client VARCHAR(20) NOT NULL,
    Statut ENUM('Non Assigné', 'À faire', 'En cours', 'En pause', 'En retard', 'Archivé','Terminé') NOT NULL DEFAULT 'Non Assigné',
    CodeUtilisateur INT,
    FOREIGN KEY (CodeUtilisateur) REFERENCES Utilisateurs(IdUtilisateur)
);

-- Table Tâches
-- DROP TABLE IF EXISTS Taches;
CREATE TABLE Taches (
    IdTache INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CodeTache VARCHAR(100) UNIQUE NOT NULL,
    Nom VARCHAR(45) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    DateDebut DATE NOT NULL,
    DateFin DATE NOT NULL,
    DateTermine DATE NOT NULL,
    Statut ENUM('À faire', 'En cours', 'En pause','Non requis', 'En retard', 'Terminé') NOT NULL DEFAULT 'À faire',
    IdProjet INT NULL,
    FOREIGN KEY (IdProjet) REFERENCES Projets(IdProjet)
);


------------------------------------------------------------------
--------------------	GÂCHETTE	------------------------------
------------------------------------------------------------------

-- Supprime le gâchette s'il existe
DROP TRIGGER IF EXISTS GenerateUsername;

-- Supprime le gâchette s'il existe
DROP TRIGGER IF EXISTS GenerateCodeProjet;

-- Supprime le gâchette s'il existe
DROP TRIGGER IF EXISTS AssignProjectToAdmin;

-- Supprime le gâchette s'il existe
DROP TRIGGER IF EXISTS GenerateUniqueCodeTache;


-- Cette gâchette (gâchette/trigger) est configuré pour s'exécuter avant l'insertion d'un utilisateur dans la table "Utilisateurs". Il génère le username en utilisant les 2 dernières lettres du nom et du prénom et 3 chiffres, puis vérifie s'il existe déjà dans la table. S'il existe, il incrémente les trois derniers chiffres jusqu'à ce qu'un username unique soit trouvé

DELIMITER //

-- Crée un gâchette avant l'insertion dans la table Utilisateurs
CREATE TRIGGER GenerateUsername BEFORE INSERT ON Utilisateurs
FOR EACH ROW
BEGIN
    -- Déclare les variables nécessaires
    DECLARE NewUsername VARCHAR(45);
    DECLARE BaseUsername VARCHAR(45);
    DECLARE UsernameCount INT;
    
    -- Génère la base du username en utilisant les 2 premières lettres du nom et du prénom
    SET BaseUsername = CONCAT(LEFT(NEW.Nom, 2), LEFT(NEW.Prenom, 2));
    
    -- Vérifie si le username existe déjà dans la table
    SELECT COUNT(*) INTO UsernameCount FROM Utilisateurs WHERE Username LIKE CONCAT(BaseUsername, '%');
    
    -- Si le username existe, incrémente le chiffre jusqu'à obtenir un username unique
    IF UsernameCount > 0 THEN
        SET @counter = 1;
        
        -- Utilise une boucle pour vérifier l'unicité du username
        WHILE UsernameCount > 0 DO
            -- Génère quatre chiffres aléatoires
            SET @randomDigits = LPAD(FLOOR(RAND() * 10000), 4, '0');
            
            -- Construit le nouveau username avec les chiffres générés
            SET NewUsername = CONCAT(BaseUsername, @randomDigits);
            
            -- Vérifie si le nouveau username existe
            SELECT COUNT(*) INTO UsernameCount FROM Utilisateurs WHERE Username = NewUsername;
            
            SET @counter = @counter + 1;
        END WHILE;
    ELSE
        -- Si le username est unique, utilise la base du username suivie de quatre chiffres aléatoires
        SET @randomDigits = LPAD(FLOOR(RAND() * 10000), 4, '0');
        SET NewUsername = CONCAT(BaseUsername, @randomDigits);
    END IF;
    
    -- Définit le nouveau username pour la nouvelle ligne à insérer
    SET NEW.Username = NewUsername;
END;
//

DELIMITER ;


-- Cette gâchette (declencheur/trigger) génère un codeProjet unique avec un mot de 3 lettres suivies de 4 chiffres. Il vérifie d'abord s'il existe déjà dans la table et incrémenter les quatre dernière chiffre si nécessaire pour garantir l'unicité.
DELIMITER //

-- Crée un gâchette avant l'insertion dans la table Projets
CREATE TRIGGER GenerateCodeProjet
BEFORE INSERT ON Projets FOR EACH ROW
BEGIN
    DECLARE newCode VARCHAR(7);
    DECLARE codeExists INT;

    -- Générer un code aléatoire de 4 chiffres
    SET newCode = CONCAT(CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), LPAD(FLOOR(1000 + RAND() * 9000), 4, '0'));

    -- Vérifier si le code existe déjà dans la table
    SELECT COUNT(*) INTO codeExists FROM Projets WHERE CodeProjet = newCode;

    -- Si le code existe, incrémentez les 4 chiffres finaux
    IF codeExists > 0 THEN
        SET @maxCode = (SELECT MAX(SUBSTRING(CodeProjet, 4, 4)) FROM Projets WHERE CodeProjet REGEXP '^[A-Z]{3}[0-9]{4}$');
        IF @maxCode IS NULL THEN
            SET newCode = CONCAT(CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), LPAD(1, 4, '0'));
        ELSE
            SET newCode = CONCAT(CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), CHAR(FLOOR(65 + RAND() * 26)), LPAD(@maxCode + 1, 4, '0'));
        END IF;
    END IF;

    -- Affectez le nouveau code généré au projet
    SET NEW.CodeProjet = newCode;
END;
//

DELIMITER ;

-- Créez une gâchette "BEFORE INSERT" sur la table Projets
DELIMITER //

CREATE TRIGGER AssignProjectToAdmin
BEFORE INSERT ON Projets FOR EACH ROW
BEGIN
    DECLARE adminUserId INT;

    -- Recherchez l'ID de l'utilisateur avec l'adresse e-mail "admin@admin.com"
    SELECT IdUtilisateur INTO adminUserId FROM Utilisateurs WHERE Courriel = 'admin@admin.com';

   -- Si l'utilisateur admin existe et le statut du projet est 'Non Assigné', attribuez le projet à cet utilisateur
    IF adminUserId IS NOT NULL AND NEW.Statut = 'Non Assigné' THEN
        SET NEW.CodeUtilisateur = adminUserId;
    END IF;
END;
//

DELIMITER ;


-- Cette gâchette (gâchette/trigger) génère le codeTache en ajoutant un "T" au début, suivi d'un chiffre incrémenté pour chaque nouvelle tâche d'un projet, puis du code du projet. Il vérifie d'abord s'il existe déjà des tâches avec le même code de projet et incrémente le chiffre si nécessaire pour garantir l'unicité. 
DELIMITER //

-- Crée un gâchette avant l'insertion dans la table Taches
CREATE TRIGGER GenerateUniqueCodeTache BEFORE INSERT ON Taches
FOR EACH ROW
BEGIN
    -- Déclare les variables nécessaires
    DECLARE NewCodeTache VARCHAR(100);
    DECLARE CodeTacheCount INT;
    
    -- Récupère le code du projet lié à la tâche
    SET @ProjectCode = (SELECT CodeProjet FROM Projets WHERE IdProjet = NEW.IdProjet);
    
    -- Vérifie s'il existe déjà des tâches avec le même code de projet
    SELECT COUNT(*) INTO CodeTacheCount FROM Taches WHERE CodeTache LIKE CONCAT('T%', @ProjectCode);
    
    -- Si des tâches existent, incrémente le chiffre
    IF CodeTacheCount > 0 THEN
        SET @counter = 1;
        
        -- Utilise une boucle pour vérifier l'unicité du codeTache
        WHILE CodeTacheCount > 0 DO
            SET NewCodeTache = CONCAT('T', @counter, @ProjectCode);
            
            -- Vérifie si le nouveau codeTache existe
            SELECT COUNT(*) INTO CodeTacheCount FROM Taches WHERE CodeTache = NewCodeTache;
            
            SET @counter = @counter + 1;
        END WHILE;
    ELSE
        -- Si c'est la première tâche du projet, utilise "T1" suivi du code du projet
        SET NewCodeTache = CONCAT('T1', @ProjectCode);
    END IF;
    
    -- Définit le nouveau codeTache pour la nouvelle ligne à insérer
    SET NEW.CodeTache = NewCodeTache;
END;
//

DELIMITER ;



------------------------------------------------------------------
--------------------	DONNÉES 	------------------------------
------------------------------------------------------------------

-- Insérer 10 données utilisateur (5 femmes et 5 hommes)
INSERT INTO Utilisateurs (Nom, Prenom, Password, Courriel, Titre, Departement)
VALUES
    ('admin', 'admin', 'admin123482', 'admin@admin.com', 'admin', 'admin'),
    ('Smith', 'John', 'password1', 'john.smith@example.com', 'Manager', 'Ressources Humaines'),
    ('Johnson', 'Mary', 'password2', 'mary.johnson@example.com', 'Directrice', 'Marketing'),
    ('Brown', 'David', 'password3', 'david.brown@example.com', 'Développeur', 'TI'),
    ('Miller', 'Jennifer', 'password4', 'jennifer.miller@example.com', 'Analyste', 'Finance'),
    ('Davis', 'Lisa', 'password5', 'lisa.davis@example.com', 'Chef de Projet', 'R&D'),
    ('Wilson', 'Michael', 'password6', 'michael.wilson@example.com', 'Designer', 'Marketing'),
    ('Martinez', 'Sarah', 'password7', 'sarah.martinez@example.com', 'Ingénieur', 'TI'),
    ('Jones', 'Jessica', 'password8', 'jessica.jones@example.com', 'Spécialiste des Ventes', 'Ventes'),
    ('Anderson', 'William', 'password9', 'william.anderson@example.com', 'Comptable', 'Finance'),
    ('Taylor', 'Emily', 'password10', 'emily.taylor@example.com', 'Assistant RH', 'Ressources Humaines');

-- vérifier les données
SELECT * FROM Utilisateurs;

-- Insérer 10 projets en ingénierie sans spécifier le code du projet
INSERT INTO Projets (Nom, Description, DateDebut, DateFin, DateTermine, Client, Statut, CodeUtilisateur)
VALUES
    ('Projet Électrique 1', 'Description du projet électrique 1', '2023-10-01', '2023-11-30', '2023-12-15', 'Client 1', 'Non Assigné',1),
    ('Projet Mécanique 1', 'Description du projet mécanique 1', '2023-11-01', '2023-12-31', '2024-01-15', 'Client 2', 'À faire', 2),
    ('Projet Procédé 1', 'Description du projet procédé 1', '2024-01-15', '2024-03-15', '2024-03-30', 'Client 3', 'En cours', 8),
    ('Projet Électrique 2', 'Description du projet électrique 2', '2024-02-01', '2024-04-30', '2024-05-15', 'Client 4', 'En pause', 2),
    ('Projet Mécanique 2', 'Description du projet mécanique 2', '2024-03-01', '2024-05-31', '2024-06-15', 'Client 5', 'En retard', 9),
    ('Projet Procédé 2', 'Description du projet procédé 2', '2024-04-15', '2024-06-30', '2024-07-15', 'Client 6', 'Archivé', 3),
    ('Projet Électrique 3', 'Description du projet électrique 3', '2024-05-01', '2024-07-31', '2024-08-15', 'Client 7', 'Terminé', 7),
    ('Projet Mécanique 3', 'Description du projet mécanique 3', '2024-06-01', '2024-08-31', '2024-09-15', 'Client 8', 'Non Assigné', 1),
    ('Projet Procédé 3', 'Description du projet procédé 3', '2024-07-15', '2024-09-30', '2024-10-15', 'Client 9', 'À faire', 5),
    ('Projet Électrique 4', 'Description du projet électrique 4', '2024-08-01', '2024-10-31', '2024-11-15', 'Client 10', 'En cours', 6);

-- vérifier les données
SELECT * FROM Projets;

-- Insérez 5 tâches par projet
INSERT INTO Taches (Nom, Description, DateDebut, DateFin, DateTermine, Statut, IdProjet)
SELECT 'Tâche 1', 'Description de la tâche 1', '2023-10-01', '2023-11-30', '2023-12-15', 'À faire', IdProjet FROM Projets;
INSERT INTO Taches (Nom, Description, DateDebut, DateFin, DateTermine, Statut, IdProjet)
SELECT 'Tâche 2', 'Description de la tâche 2', '2023-11-01', '2023-12-31', '2024-01-15', 'À faire', IdProjet FROM Projets;
INSERT INTO Taches (Nom, Description, DateDebut, DateFin, DateTermine, Statut, IdProjet)
SELECT 'Tâche 3', 'Description de la tâche 3', '2024-01-15', '2024-03-15', '2024-03-30', 'À faire', IdProjet FROM Projets;
INSERT INTO Taches (Nom, Description, DateDebut, DateFin, DateTermine, Statut, IdProjet)
SELECT 'Tâche 4', 'Description de la tâche 4', '2024-02-01', '2024-04-30', '2024-05-15', 'À faire', IdProjet FROM Projets;
INSERT INTO Taches (Nom, Description, DateDebut, DateFin, DateTermine, Statut, IdProjet)
SELECT 'Tâche 5', 'Description de la tâche 5', '2024-03-01', '2024-05-31', '2024-06-15', 'À faire', IdProjet FROM Projets;


-- vérifier les données
SELECT * FROM Taches;