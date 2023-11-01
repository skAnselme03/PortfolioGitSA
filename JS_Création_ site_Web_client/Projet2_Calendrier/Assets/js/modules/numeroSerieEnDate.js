/**
 * Fonction qui retourne la date selon le numero de série passée en paramètre
 * @param {int} intNoSerie 
 * @returns la date sous forme de tableau [jour, mois, annee]
 */
function noSerieEnDate(intNoSerie) {
    //Initialisation du nombre de journée en vigeur
    let joursEnCours = intNoSerie - 1;
    //Initialisation de l'année
    let anneeEnCours = 1900;
    //Initialisation du numéro du mois
    let moisEnCours = 0;
    // traitement de la journée en cours dans l'année
    while (joursEnCours >= nombreJoursAnnee(anneeEnCours)) {
        // Décrementer le nombre de jours en cours avec le nombre de jours dans chaque année
        joursEnCours -= nombreJoursAnnee(anneeEnCours);
        anneeEnCours++;
    }
    // traitement de la journée en cours dans le mois
    while (joursEnCours >= nombreJoursMois(moisEnCours, anneeEnCours)) {
        // Décrementer le nombre de jours en cours avec le nombre de jours dans chaque mois
        joursEnCours -= nombreJoursMois(moisEnCours, anneeEnCours);
        moisEnCours++
    }
    // ajouter la journée en cours au numéro de jour + 1
    return [joursEnCours + 1, moisEnCours, anneeEnCours];
}