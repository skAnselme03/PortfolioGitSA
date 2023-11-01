'use Strict';
/**
 * Mettre en majuscule la première lettre d'une chaine de caractère
 * @param {String} strChaine : chaine pour transformer le premier caractère.
 * @returns : La chaine transformé
 */
function majuscule1Lettre(strChaine) {
    return strChaine.charAt(0).toUpperCase() + strChaine.substring(1);
}