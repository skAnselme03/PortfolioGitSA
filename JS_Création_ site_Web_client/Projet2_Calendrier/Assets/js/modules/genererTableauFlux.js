/**
 * 
 * @param {*} tabValsEntete 
 * @param {*} intJour 
 * @param {*} intMois 
 * @param {*} intAnnee 
 * @param {*} strNomPointAttache 
 */
function genererTabFluxCalendrier(tabValsEntete, intJour, intMois, intAnnee, strNomPointAttache) {
    let strFlux = '\n<table class="sCellEnteteColonne sTexteCentre sLargeurDiv sCellDate ">\n';

    // Création de l'entête

    strFlux += '<thead>\n' + '<tr>\n';
    for (let valEntete of tabValsEntete) {
        strFlux += '<th class="sCellDate sTexteCentre"> ' + valEntete + '</th>\n';
    }
    strFlux += '</tr>\n' + '</thead>\n';


    //Création du corps du tableau
    strFlux += '<tbody class="corpsCalendrier">\n' + '<tr>\n';
    // L'index du premier jour du mois selectionner
    /* let intIndexPremierJoursMois = intJour; */
    //Pour passer à la ligne
    let compteur = 0;

    // Remplir les cellules vide avec les dates du mois précedent
    for (let i = intIndexPremierJoursMois(intMois, intAnnee) - 1; i > 0; i--) {
        (intAnnee == 1900) ? strFlux += '<td class="sCellAutreJour">' + 0 + '</td>\n':
            strFlux += '<td class="sCellDate sCellAutreJour sCorps sTexteCentre">' +
            (dernierJourDuMoisPrecedent(intMois, intAnnee) - i + 1) + '</td>\n';
        compteur++;
    }


    //Remplir les dates dans le calendrier
    for (let i = 1; i <= dernierJourDuMoisCourant(intMois, intAnnee); i++) {
        if (compteur % 7 == 0) strFlux += '</tr>\n' + '<tr>\n';

        // mise en forme de la date courante ou selectionné
        if (i === recupereJourActuel() && intMois === recupereMoisActuel() && intAnnee === recupereAnneeActuelle()) {
            strFlux += '<td class="sCellDate sCellJourCourant  sCorps sTexteCentre">' + i + '</td>\n';
        } else {
            if (i == intJour) {
                strFlux += '<td class="sCellDate sDateSelectionnee  sCorps sTexteCentre">' + i + '</td>\n';
            } else strFlux += '<td  class="sCellDate  sCorps sTexteCentre">' + i + '</td>\n';
        }

        compteur++;
    }

    // ajouter la du mois suivant pour remplir les autrtes cellule vide
    let intJourMoisSuivant = retourneLesJoursDuMoisSuivant(intMois, intAnnee);

    for (let i = 1; i <= intJourMoisSuivant; i++) {
        // mise en forme de la date selectionner
        strFlux += '<td class="sCellDate sCellAutreJour  sCorps">' + i + '</td>\n';
    }
    strFlux += '</tr>\n';

    strFlux += '</tbody>\n' + '</table>';
    document.getElementById(strNomPointAttache).innerHTML = strFlux

}
/**
 * Retourner l'index du premier jour du mois. 
 * 
 * @param {int} intMois le mois selectionné
 * @param {int} intAnnee l'année sélectionnée
 * @returns {int} 0 si l'index est 1 == Dimanche sinon, index entre [2,7]
 */
function intIndexPremierJoursMois(intMois, intAnnee) {
    let indexPremierJourMois = dateEnJourSemaine(1, intMois, intAnnee);
    return (indexPremierJourMois == 1) ? 0 : indexPremierJourMois;
}

/**
 * Retourne le dernier jours du mois selectionner
 * 
 * @param {int} intMois le mois selectionné
 * @param {int} intAnnee l'année sélectionnée
 * @returns (int) le dernier jour du mois retourner
 */
function dernierJourDuMoisCourant(intMois, intAnnee) {
    return nombreJoursMois(intMois, intAnnee)
}

/**
 * Retourne le dernier jours du mois précedent du mois selectionner
 * 
 * @param {int} intMois le mois selectionné
 * @param {int} intAnnee l'année sélectionnée
 * @returns (int) le dernier jour du mois retourner précedent du mois selectionner
 */
function dernierJourDuMoisPrecedent(intMois, intAnnee) {
    let moisPrecedent = (intMois == 1) ? intMois = 12 : intMois = intMois - 1;
    return nombreJoursMois(moisPrecedent, intAnnee);
}

/**
 * Retourne l'index du mois courant
 * 
 * @param {int} intMois 
 * @returns {int} l'index du mois Suivant entre [1,12];
 */
function intIndexDernierJour(intMois, intAnnee) {
    let intNumeroDuJour = nombreJoursMois(intMois);
    //return intMois + 1;
    return dateEnJourSemaine(intNumeroDuJour, intMois, intAnnee);

}

/**
 * Calculer les dates du prochain mois pour remplir les cellules vides d'un calendrier
 * 
 * @param {int} intMoisCourant l'index du derniers jour du mois courant
 * @returns {int} le nombre de jours du mois suivant 
 */
function retourneLesJoursDuMoisSuivant(intMoisCourant, intAnnee) {
    return (7 - intIndexDernierJour(intMoisCourant, intAnnee));
}