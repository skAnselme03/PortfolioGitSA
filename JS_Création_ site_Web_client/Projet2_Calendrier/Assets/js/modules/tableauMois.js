/**
 * Retourner le tableau littéral des mois de l'année
 * @returns {Array<String>} Tableau littéral des mois
 */
function tblJourSemaineEnLitteral() {
    let tabJoursSemaine = [];
    // Remplir le tableau jour de la semaine avec les jours de la semaine
    for (let i = 0; i < 7; i++) {
        tabJoursSemaine[i] = jourSemaineEnLitteral(i + 1, true);
    }
    return tabJoursSemaine;
}