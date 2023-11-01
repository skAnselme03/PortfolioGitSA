/**
 * Récupérer la date du jour et l'affecter aux élements date du Dom associés.
 * @param {object} objJour Élements le jour du Dom
 * @param {object} objMois Élements mois du Dom
 * @param {object} objSiecle Élements siècle du Dom
 * @param {object} objAnnee Élements année du Dom
 * @param {int} intJourActuel jour actuel récuperer
 * @param {int} intMoisActuel mois actuel récuperer
 * @param {int} intAnneActuel année actuel récuperer
 */
function dateInitiale(objJour, objMois, objSiecle, objAnnee, intJourActuel, intMoisActuel, intAnneActuel) {

    //affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee la date du jour récuperer.
    indexOptionsSelect(objJour, intJourActuel - 1);
    indexOptionsSelect(objMois, intMoisActuel - 1);
    for (let i = 0; i < ddlSiecle.length; i++) {
        if (objSiecle.options[i].text == intAnneActuel.toString().substring(0, 2)) {
            indexOptionsSelect(objSiecle, i);
        }
    }

    for (let i = 0; i < ddlAnnee.length; i++) {
        if (ddlAnnee.options[i].text == intAnneActuel.toString().substring(2, 4)) {
            indexOptionsSelect(objAnnee, i);
        }
    }
}