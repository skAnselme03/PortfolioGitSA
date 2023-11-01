function dateEnLitteral(intJour, intMois, intAnnee, binMoisMajuscule) {
    // Ajouter le jour au format exposant s'il est 1
    if (intJour == 1) {
        intJour = intJour + er(intJour, true);
    }

    // Ajouter le mois sous forme de littérale 
    intMois = moisEnLitteral(intMois, binMoisMajuscule);

    // retour de la date
    return (intJour + ' ' + intMois + ' ' + intAnnee);

}