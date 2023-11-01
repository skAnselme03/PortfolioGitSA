
/**
 * Fonction prend une expression régulière sous forme
 * de chaîne de caractères et une entrée quelconque à tester,
 * renvoie true si l'entrée correspond à l'expression régulière,
 * et false sinon.
 *
 * @param patterne - L'expression régulière à tester,
 *                      sous forme de chaîne de caractères
 * @param entree - entrée à tester
 * @returns {boolean} - true l'entrée correspond à l'expression régulière,
 *                      false sinon
 */
export function match_patterne(patterne, entree) {
    // Crée une instance d'expression régulière à
    // partir de la chaîne de caractères du patterne
    let regex = new RegExp(patterne)
        // Utilise la méthode test() de l'objet RegExp
        // pour tester si la chaîne de caractères entree
        // correspond à l'expression régulière
    return regex.test(entree)
}