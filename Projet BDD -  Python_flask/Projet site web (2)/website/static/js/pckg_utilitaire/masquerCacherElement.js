/**
 * Masquer un élement
 * @param strIdElement id de l'élement à  masquer
 * @param bEstMasque true: masquer, false non masquer
 * @returns {boolean} si l'élément identifié par strIdElement est masqué ou non
 */
function masque_element(strIdElement, bEstMasque) {
    let objMasque = document.getElementById(strIdElement);
    if (objMasque==null) {
        alert('Attention... balise "' + strIdElement + '" inexistante !');
    }
    else {
        if (bEstMasque != null) {
            objMasque.style.display = bEstMasque ? 'none' : 'block';
        }
        else {
            return objMasque.style.display == 'none';
        }
    }
}

/**
 * Affecte ou retourne si l'élément XHTML
 * identifié par strID est caché ou non
 * @param strIdElement id de l'élement à  caché
 * @param estCache true: caché, false non caché
 * @returns {boolean} si l'élément identifié par strIdElement est caché ou non*/

function cache_element(strIdElement, estCache) {
    let objCacher = document.getElementById(strIdElement);
    if (objCacher==null) {
        alert('Attention... balise "' + strIdElement + '" inexistante !');
    }
    else {
        if (estCache != null) {
            objCacher.style.visibility = estCache ? 'hidden' : 'visible';
        }
        else {
            return objCacher.style.visibility == 'hidden';
        }
    }
}