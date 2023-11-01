/**
 * Fonction pour valider l'identifiant et le mot de passe.
 * @Return : vrai si l'identifiant et le mot de passe sont valide. Faux sinon
 * 
 */
function identifiantValide() {
    let patternMatricule = /^\d{7}$/;
    let patternMdp = /^\w{1,6}\d{5}$/;
    let valtbMatricule = b('tbMatricule');
    let valtbMotDePasse = b('tbMotDePasse');
    if (!expressionValider(valtbMatricule, patternMatricule) &&
        !expressionValider(valtbMotDePasse, patternMdp)) {
        b('lblMessageConnexion', 'Identifiant et mot de passe invalide!');
        return false;
    } else {
        if (!expressionValider(valtbMatricule, patternMatricule)) {
            b('lblMessageConnexion', 'Identifiant invalide!');
            return false;

        } else {
            if (!expressionValider(valtbMotDePasse, patternMdp)) {
                b('lblMessageConnexion', 'Mot de passe invalide!');
                return false;
            } else {
                b('lblMessageConnexion', '');
                changerTypeHtml('tbMotDePasse', "password")
                return true;
            }
        }
    }

}
/**
 * Valider la ville et le budget a ajouter ou a enlever
 * @returns 
 */
function villeBudgetValide() {
    let patternVille = /^[a-zA-Z]+(?:-[a-zA-Z]+)*$/;
    let patternBudget = /^\d{3,7}$/;

    if (!expressionValider(b('tbVilleAjout'), patternVille) &&
        !expressionValider(b('tbBudgetAjout'), patternBudget)) {
        b('lblMessageAjout', 'Ville et budget invalides!');
        return false;
    } else {
        if (!expressionValider(b('tbVilleAjout'), patternVille)) {
            b('lblMessageAjout', 'Ville invalide!');
            return false;

        } else {
            if (!expressionValider(b('tbBudgetAjout'), patternBudget)) {
                b('lblMessageAjout', 'Budget invalide!');
                return false;
            } else {
                b('lblMessageAjout', '');
                //changerTypeHtml('tbMotDePasse', "password")
                return true;
            }
        }
    }
}
/**
 * Valider la ville a retirer
 * @returns 
 */
function villeValide(strIDElement, lblAffichage) {
    let patternVille = /^[a-zA-Z]+(?:-[a-zA-Z]+)*$/;
    if (!expressionValider(b(strIDElement), patternVille)) {
        b(lblAffichage, 'Ville invalide');
        return false;
    } else {
        b(lblAffichage, '');
        return true;
    }
}


/**
 * Valider une chaine correspond à l'expression régulière fournis
 * @param {string} strChaine 
 * @param {*} pattern 
 * @returns : true si valider, false sinon
 */
function expressionValider(strChaine, pattern) {
    return pattern.test(strChaine);

}