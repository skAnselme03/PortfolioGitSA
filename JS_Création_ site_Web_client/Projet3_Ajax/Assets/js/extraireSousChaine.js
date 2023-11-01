/**
 * Function pour extrayer une sou-chaine dans une chaine de caractère
 * @param {string} strChaine : chaine d'extraction
 * @param {int} intBeginIndex : index de départ
 * @param {int} intEndIndex : index de fin
 * 
 * @retun {string} chaine extraite
 */
function strExtraireSousChaine(strChaine, intBeginIndex, intEndIndex) {
    if (strChaine !== null) {
        if (intBeginIndex == null) {
            return strChaine.substring(intEndIndex);
        }
        if (intEndIndex == null) {
            return strChaine.substring(intBeginIndex);
        }
        return strChaine.substring(intBeginIndex, intEndIndex);
    }
}