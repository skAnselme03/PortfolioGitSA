
DELIMITER //

CREATE TRIGGER calculer_points BEFORE INSERT ON produits

FOR EACH ROW

BEGIN

    DECLARE points INT;

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