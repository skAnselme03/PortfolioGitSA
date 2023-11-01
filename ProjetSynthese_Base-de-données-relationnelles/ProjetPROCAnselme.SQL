/*********************************************************************/
/***             Étape # 3: Création de quatre procédures          ***/
/*********************************************************************/
/*-------------------------------------------------------------------*/
/*---					PROCÉDURE #1 : ListeGlobale				  ---*/
/*-------------------------------------------------------------------*/
DELIMITER //
DROP PROCEDURE IF EXISTS ListeGlobale;

CREATE PROCEDURE ListeGlobale()
BEGIN
	SELECT No, CONCAT(Rue_1, ' / ', Rue_2) AS "Coin", 
		   Id, LaDate, 
		   Lheure, Piet_N, 
		   Piet_E, Piet_S, 
		   Piet_O, Cy_N, 
		   Cy_E, Cy_S, 
		   Cy_O 
	FROM Comptage;		
END //
DELIMITER ;

/*-------------------------------------------------------------------*/
/*---					PROCÉDURE #2 : ListeSelect				  ---*/
/*-------------------------------------------------------------------*/
DELIMITER //
DROP PROCEDURE IF EXISTS ListeSelect;

CREATE PROCEDURE ListeSelect(IN nomRue_1 VARCHAR(48), 
							 IN nomRue_2 VARCHAR(48))
BEGIN
	SET @nRue1 = nomRue_1;
	SET @nRue2 = nomRue_2;
	SELECT * FROM comptage WHERE (Rue_1 = @nRue1 AND Rue_2 = @nRue2);
END //
DELIMITER ;

/*-------------------------------------------------------------------*/
/*---					PROCÉDURE #3 : CompteDetail				  ---*/
/*-------------------------------------------------------------------*/
DELIMITER //
DROP PROCEDURE IF EXISTS CompteDetail;

CREATE PROCEDURE CompteDetail()
BEGIN	
	SELECT CONCAT(Rue_1," / ", Rue_2) AS "Coin", 
		   SUM(Piet_N) AS "P.Nord",
		   SUM(Piet_E) AS "P.Est", 
		   SUM(Piet_S) AS "P.Sud", 
		   SUM(Piet_O) AS "P.Ouest",
		   (SUM(Piet_N) + SUM(Piet_E) + SUM(Piet_S) + SUM(Piet_O)) AS "Piétons",
		   SUM(Cy_N) AS "C.Nord", 
		   SUM(Cy_E) AS "C.Est", 
		   SUM(Cy_S) AS "C.Sud", 
		   SUM(Cy_O) AS "C.Ouest",
		   (SUM(Cy_N) + SUM(Cy_E) + SUM(Cy_S) + SUM(Cy_O)) AS "Cyclistes"
	FROM comptage
	GROUP by CONCAT(Rue_1," / ", Rue_2);
END //
DELIMITER ;

/*-------------------------------------------------------------------*/
/*---					PROCÉDURE #4 : CompteGlobal				  ---*/
/*-------------------------------------------------------------------*/
DELIMITER //
DROP PROCEDURE IF EXISTS CompteGlobal;

CREATE PROCEDURE CompteGlobal()
BEGIN	
	SELECT CONCAT(Rue_1," / ", Rue_2) AS "Coin", 
		   SUM(Piet_N + Piet_E + Piet_S + Piet_O) AS "Piétons",
		   (SUM(Cy_N) + SUM(Cy_E) + SUM(Cy_S) + SUM(Cy_O)) AS "Cyclistes"
	FROM comptage
	GROUP by CONCAT(Rue_1," / ", Rue_2);
END //
DELIMITER ;
