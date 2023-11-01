/*********************************************************************/
/***            Étape # 2:  FORME NORMALE 1 (1NF)                  ***/
/*********************************************************************/

/*
	Séparez la colonne Coin en deux colonnes Rue_1 et Rue_2.
	En prenant bien soin de séparer les données vers les deux 
	champs Rue_. Procédez comme pour la démo de la 1FN
	- Supprimez les colonnes et les données) suivantes :
		ND, NTD, NG, ED, ETD, EG,
		SD, STD, SG, OD, OTD, OG
*/
-- Création d'une colonne aditionnelle [Rue_2] apres coin
ALTER TABLE comptage
ADD Rue_2 VARCHAR(48) NOT NULL AFTER coin;

-- Renommer la colonne coin en Rue_1
ALTER TABLE comptage
RENAME COLUMN coin TO Rue_1;

-- Vérification des modifications dans le tableau
SELECT * FROM comptage LIMIT 5;

/*
  Division des données de la seconde colonne et placera les deux parties 
  respectives des données dans la premières et la seconde colonne.
*/
-- Extrait de la donnée qui va aller dans la première colonne. 
UPDATE Comptage
SET Rue_2 = TRIM(SUBSTR(Rue_1, POSITION("/" IN Rue_1)+2));

-- Vérification des modifications dans le tableau
SELECT * FROM comptage LIMIT 5;


-- Extrait le data qui va aller dans la première colonne. Copie tout ce qui vient avant la virgule
UPDATE comptage
SET Rue_1 = TRIM(SUBSTR(Rue_1, 1, POSITION("/" IN Rue_1)-1));

-- Vérification des modifications dans le tableau
SELECT * FROM comptage LIMIT 5;
DESC comptage;

/*
	Supprimez les colonnes et les données) suivantes :
		ND, NTD, NG, ED, ETD, EG,
		SD, STD, SG, OD, OTD, OG
*/
ALTER TABLE comptage
	DROP COLUMN ND,
	DROP COLUMN NTD,
	DROP COLUMN NG,
	DROP COLUMN ED,
	DROP COLUMN ETD,
	DROP COLUMN EG,
	DROP COLUMN SD,
	DROP COLUMN STD,
	DROP COLUMN SG,
	DROP COLUMN OD,
	DROP COLUMN OTD,
	DROP COLUMN OG;

-- Vérification des modifications dans le tableau
SELECT * FROM comptage LIMIT 5;
DESC comptage;
