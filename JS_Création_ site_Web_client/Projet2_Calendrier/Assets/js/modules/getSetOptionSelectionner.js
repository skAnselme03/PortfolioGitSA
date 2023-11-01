/**
 * Retourner la valeur selectionner dans une liste déroulante
 * @param {String} strIdElement: l'id de la liste déroulante
 * @returns (String) l'option selectionner dans la liste déroulante
 */
function strReturnValueSelection(strIdElement) {
    let selection = document.getElementById(strIdElement);
    return selection.options[selection.selectedIndex].value;
}

/**
 * Forcer une nouvelle sélections 
 * @param {String} strIdElement l'id de la liste déroulante
 * @param {String} strValue valleur d'affectation de la liste déroulante
 */
function setValueSelection(objElement, strIdElement, strValue) {
    if (objElement != null && strIdElement == null) {
        for (let i = 0; i < objElement.length; i++) {
            if (objElement[i].value === strValue) {
                // objElement.selectedIndex = strValue;
                objElement.selectedIndex = i;
                break;
            }
        }
    } else {
        let selection = document.getElementById(strIdElement);
        if (selection == null) {
            alert('Attention... balise "' + strIdElement + '" inexistante !');
        } else {

            for (let i = 0; i < selection.length; i++) {
                if (selection[i].value === strValue) {
                    selection.selectedIndex = i;
                    break;
                }
            }
        }
    }
}