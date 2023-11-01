/**
 * récupère l'élément HTML du champ nom puis vérifie si la
 * longueur de la valeur saisie dans le champ nom est
 * supérieure à tailleChamp caractères
 * @param element élement du dom à vérifier
 * @param tailleChamp taille maximal pour le champ
 * @returns {boolean} Vrai si la taille de la valeur
 * de l'élement correspont, sinon faux
 */

export function match_longeur_champ(element, tailleChamp) {
    if (element.value.length > tailleChamp) {
        alert(`Le champ ne doit pas dépasser ${tailleChamp} caractères.`);
        return false
    }

    return true

}
