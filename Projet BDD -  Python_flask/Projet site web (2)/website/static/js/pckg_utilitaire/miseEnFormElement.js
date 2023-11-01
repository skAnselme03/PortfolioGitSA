/**
 * Affecte ou ajoute ou retourne les sélecteurs de mise en forme
 *  de l'élément XHTML identifié par strIdElement
 * @param strIdElement id de l'élement
 * @param strSelecteurs selecteur de mise en forme existant en css fournis
 * @param aUneClasse ajouter la nouvelle class si un classe est déja affectée sinon, le crée
 * @returns {string} selecteur de mise en forme
 */
function miseEnForme(strIdElement, strSelecteurs, aUneClasse) {
    let objElement = document.getElementById(strIdElement);
    if (objElement == null) {
        alert('Attention... balise "' + strIdElement + '" inexistante !');
    } else {
        if (strSelecteurs != null) {
            if (!aUneClasse) {
                objElement.className = strSelecteurs;
            } else {
                objElement.classList.add(strSelecteurs);
            }
        } else {
            return objElement.className;
        }
    }
}