/**
 * Fonction qui retourner la date sous forme d'un tableau
 * @returns [numéro du jours, numéro du mois, l'année complète de la date]
 */
function getNumDateActuelle() {
    // Instanciation de la date du jour et l'heure 
    let objMaintenant = new Date();
    // objMaintenant.getMonth() + 1 car 0=janvier
    return [objMaintenant.getDate(), objMaintenant.getMonth() + 1, objMaintenant.getFullYear()]
}